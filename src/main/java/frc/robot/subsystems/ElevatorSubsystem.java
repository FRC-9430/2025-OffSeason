// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private SparkFlex elevatorMotor = new SparkFlex(ElevatorConstants.kElevatorMotorPort, SparkFlex.MotorType.kBrushless);
  private AbsoluteEncoder absoluteEncoder = elevatorMotor.getAbsoluteEncoder();
  private SparkMaxConfig elevatorMotorConfig = new SparkMaxConfig();

  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {

    elevatorMotorConfig.inverted(ElevatorConstants.ElevatorMotorInverted);

    elevatorMotor.configure(elevatorMotorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void setElevatorMotor(double speed) {
    elevatorMotor.set(speed);
  }

  public void stopElevatorMotor() {
    elevatorMotor.stopMotor();
  }

  public double getElevatorPosition() {
    return absoluteEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

class ElevatorConstants {

  public static final boolean ElevatorMotorInverted = false;
  public static final int kElevatorMotorPort = 20;

}