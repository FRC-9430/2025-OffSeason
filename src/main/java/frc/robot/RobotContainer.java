// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static CommandXboxController k_operatorControllerPort = new CommandXboxController(
      OIConstants.k_operatorControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    k_operatorControllerPort.a()
        .whileTrue(new InstantCommand(() -> {
          shooterSubsystem.runIntake(.3);
        })).onFalse(new InstantCommand(() -> {
          shooterSubsystem.stopIntake();
        }));

    k_operatorControllerPort.b()
        .whileTrue(new InstantCommand(() -> {
          shooterSubsystem.runIntake(-.3);
        })).onFalse(new InstantCommand(() -> {
          shooterSubsystem.stopIntake();
        }));
    k_operatorControllerPort.x()
        .whileTrue(new InstantCommand(() -> {
          shooterSubsystem.setShooterSpeed(-.1);
        })).onFalse(new InstantCommand(()->{
          shooterSubsystem.stopShooter();
        }));
    k_operatorControllerPort.y()
        .whileTrue(new InstantCommand(() -> {
          shooterSubsystem.setShooterSpeed(.1);
        })).onFalse(new InstantCommand(()->{
          shooterSubsystem.stopShooter();
        }));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
