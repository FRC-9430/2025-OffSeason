// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.PIDConstants;

public class ElevatorSubsystem extends SubsystemBase {

  // Declare motors for shooter
  private SparkMax ElevatorMotor;
  private AbsoluteEncoder elevatorEncoder;

// new PIDcontroller for elevator system
  private PIDController elevatorController = new PIDController (PIDConstants.kElevatorkp, PIDConstants.kElevatorki, PIDConstants.kElevatorkd);

  /** Creates a new ElevatorSubsystem
   *. */
  public ElevatorSubsystem() {
  // Need to retrive the Can ID from the elevator motor and then put it in constants
    ElevatorMotor = new SparkMax(MotorConstants.ElevatorMotorCanID, SparkMax.MotorType.kBrushless);
    elevatorEncoder = ElevatorMotor.getAbsoluteEncoder();
  }

  public void moveElevator(double speed) {
    ElevatorMotor.set(speed);
  }

  public void stopElevator() {
    ElevatorMotor.stopMotor();
  }
}