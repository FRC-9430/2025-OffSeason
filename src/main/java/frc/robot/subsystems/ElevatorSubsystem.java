// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private SparkFlex elevatorMotor = new SparkFlex(ElevatorConstants.kElevatorMotorPort, SparkFlex.MotorType.kBrushless);
  private AbsoluteEncoder absoluteEncoder = elevatorMotor.getAbsoluteEncoder();
  private SparkMaxConfig elevatorMotorConfig = new SparkMaxConfig();


  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

class ElevatorConstants {

  public static final int kElevatorMotorPort = 20;

}