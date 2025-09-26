// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  SparkMax intakeMotor = new SparkMax(MotorConstants.IntakeMotorCanID, SparkMax.MotorType.kBrushless);

  public IntakeSubsystem() {}

  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }
  
  public void stopIntake() {
    intakeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
