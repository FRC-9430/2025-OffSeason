// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class ShooterSubsystem extends SubsystemBase {
  //declares motors for shooter
  SparkMax intakeMotor;
  SparkMax shooterMotor;
  private AbsoluteEncoder shooterEncoder;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    intakeMotor = new SparkMax(MotorConstants.IntakeMotorCanID,SparkMax.MotorType.kBrushless);
    shooterMotor = new SparkMax(MotorConstants.ShooterMotorCanID,SparkMax.MotorType.kBrushless);
    shooterEncoder = shooterMotor.getAbsoluteEncoder();
  }
  public void runIntake(double speed){
    intakeMotor.set(speed);
  }
  public void stopIntake(){
    intakeMotor.stopMotor();
  }
  public void setShooterSpeed (double speed){
    boolean isMotorSafe = shooterEncoder.getPosition() > MotorConstants.kShooterPivotMinHeight && shooterEncoder.getPosition() < MotorConstants.kShooterPivotMaxHeight;
    while(isMotorSafe){
      shooterMotor.set(speed);
    }
    shooterMotor.stopMotor();
    
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
