// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.PIDConstants;

public class ShooterSubsystem extends SubsystemBase {
  //PID controller
  private PIDController pidController;

  // Declare motors for shooter
  private SparkMax intakeMotor;
  private SparkMax shooterMotor;
  private AbsoluteEncoder shooterEncoder;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    intakeMotor = new SparkMax(MotorConstants.IntakeMotorCanID, SparkMax.MotorType.kBrushless);
    shooterMotor = new SparkMax(MotorConstants.ShooterMotorCanID, SparkMax.MotorType.kBrushless);
    shooterEncoder = shooterMotor.getAbsoluteEncoder();
    pidController = new PIDController(PIDConstants.kShooterkp, PIDConstants.kShooterki, PIDConstants.kShooterkd);
    setShooterPosition(PIDConstants.kTransitSetpoint);
  }

  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }

  public void stopIntake() {
    intakeMotor.stopMotor();
  }

  public void setShooterSpeed(double speed) {
    if (shooterEncoder.getPosition() > MotorConstants.kShooterPivotMinHeight 
        && shooterEncoder.getPosition() < MotorConstants.kShooterPivotMaxHeight ) {
          shooterMotor.set(speed);
    }else{
      stopShooter();
    }
    // shooterMotor.stopMotor();
  }

  public void setShooterPosition(double position){
    pidController.reset();
    pidController.setSetpoint(position);
  }

  public void stopShooter() {
    shooterMotor.stopMotor();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setShooterSpeed(pidController.calculate(shooterEncoder.getPosition()));
  }
}
