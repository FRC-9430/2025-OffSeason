// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.MotorConstants;

public class ElevatorSubsystem extends SubsystemBase {

  // Declare motors for shooter
  private SparkMax ElevatorMotor;
  private AbsoluteEncoder elevatorEncoder;

// new PIDcontroller for elevator system
  private PIDController elevatorController;

  /** Creates a new ElevatorSubsystem
   *. */
  public ElevatorSubsystem
() {
  // Need to retrive the Can ID from the elevator motor and then put it in constants
    ElevatorMotor = new SparkMax(MotorConstants.ElevatorMotorCanID, SparkMax.MotorType.kBrushless);
    elevatorEncoder = ElevatorMotor.getAbsoluteEncoder();
  }

  public void runIntake(double speed) {
    ElevatorMotor.set(speed);
  }

  public void stopIntake() {
    ElevatorMotor.stopMotor();
  }

  // need elevator min and max to be defined in constants
  public void setShooterSpeed(double speed) {
    if (elevatorEncoder.getPosition() > MotorConstants.kElevatorMinHeight   
        && elevatorEncoder.getPosition() < MotorConstants.kElevatorMaxHeight ) {
          ElevatorMotor.set(speed);
    }else{
      stopElevator();
    }
    // Elevator.stopMotor();
  }

  public void stopElevator() {
    ElevatorMotor.stopMotor();
  }

// elevator PID
elevatorController = new PIDController (kElevatorkp, kElevatorki, kElevatorkd);











  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
