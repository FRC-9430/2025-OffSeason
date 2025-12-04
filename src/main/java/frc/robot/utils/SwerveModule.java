// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.Configs;

/** Add your docs here. */
public class SwerveModule {
    private SparkFlex driveMotor;
    private SparkFlex turningMotor;

    private final RelativeEncoder m_drivingEncoder;
    private final AbsoluteEncoder m_turningEncoder;

    private final SparkClosedLoopController m_drivingClosedLoopController;
    private final SparkClosedLoopController m_turningClosedLoopController;

    private double m_chassisAngularOffset = 0;
    private SwerveModuleState m_desiredState = new SwerveModuleState(0.0, new Rotation2d());

    /**
     * Constructs a X2TSwerveModule and configures the driving and turning motor,
     * encoder, and PID controller. This configuration is specific to the REV
     * MAXSwerve Module built with NEOs, SPARKS MAX, and a Through Bore
     * Encoder.
     */
    public SwerveModule(int drivingCANId, int turningCANId, double chassisAngularOffset) {
        driveMotor = new SparkFlex(drivingCANId, MotorType.kBrushless);
        turningMotor = new SparkFlex(turningCANId, MotorType.kBrushless);

        m_drivingEncoder = driveMotor.getEncoder();
        m_turningEncoder = turningMotor.getAbsoluteEncoder();

        m_drivingClosedLoopController = driveMotor.getClosedLoopController();
        m_turningClosedLoopController = turningMotor.getClosedLoopController();

        // Apply the respective configurations to the SPARKS. Reset parameters before
        // applying the configuration to bring the SPARK to a known good state. Persist
        // the settings to the SPARK to avoid losing them on a power cycle.
        driveMotor.configure(Configs.X2TSwerveModule.drivingConfig, ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
        turningMotor.configure(Configs.X2TSwerveModule.turningConfig, ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);

        m_chassisAngularOffset = chassisAngularOffset;
        m_desiredState.angle = new Rotation2d(m_turningEncoder.getPosition());
        m_drivingEncoder.setPosition(0);
    }

    /**
     * Returns the current state of the module.
     *
     * @return The current state of the module.
     */
    public SwerveModuleState getState() {
        // Apply chassis angular offset to the encoder position to get the position
        // relative to the chassis.
        return new SwerveModuleState(m_drivingEncoder.getVelocity(),
                new Rotation2d(m_turningEncoder.getPosition() - m_chassisAngularOffset));
    }

    /**
     * Returns the current position of the module.
     *
     * @return The current position of the module.
     */
    public SwerveModulePosition getPosition() {
        // Apply chassis angular offset to the encoder position to get the position
        // relative to the chassis.
        return new SwerveModulePosition(
                m_drivingEncoder.getPosition(),
                new Rotation2d(m_turningEncoder.getPosition() - m_chassisAngularOffset));
    }

    /**
     * Sets the desired state for the module.
     *
     * @param desiredState Desired state with speed and angle.
     */
    public void setDesiredState(SwerveModuleState desiredState) {

        // Optimize the reference state to avoid spinning further than 90 degrees.

        // The optimize method returns a new state with the optimized angle and
        // potentially reversed velocity.
        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, desiredState.angle);

        // The optimized state is chassis-relative. We need to convert the angle
        // to the motor's frame of reference for the PID controller.
        double motorSetpointRadians = optimizedState.angle.getRadians() + m_chassisAngularOffset;

        // Command driving and turning SPARKS towards their respective setpoints.
        m_drivingClosedLoopController.setReference(optimizedState.speedMetersPerSecond, ControlType.kVelocity);
        m_turningClosedLoopController.setReference(motorSetpointRadians, ControlType.kPosition);

        m_desiredState = desiredState;

    }

    /** Zeroes all the SwerveModule encoders. */
    public void resetEncoders() {
        m_drivingEncoder.setPosition(0);
    }
}
