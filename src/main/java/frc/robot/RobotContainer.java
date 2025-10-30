// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();

    // The driver's controller
    public static CommandXboxController c_driverController = new CommandXboxController(
            OIConstants.kDriverControllerPort);

    // The operator's controller
    public static CommandXboxController c_operatorController = new CommandXboxController(
            OIConstants.kOperatorControllerPort);

    public RobotContainer() {
        configureBindings();

        // Configure default commands
        m_robotDrive.setDefaultCommand(
                // The left stick controls translation of the robot.
                // Turning is controlled by the X axis of the right stick.
                new RunCommand(
                        () -> m_robotDrive.drive(
                                -MathUtil.applyDeadband(c_driverController.getLeftY(),
                                        OIConstants.kDriveDeadband),
                                -MathUtil.applyDeadband(c_driverController.getLeftX(),
                                        OIConstants.kDriveDeadband),
                                -MathUtil.applyDeadband(c_driverController.getRightX(),
                                        OIConstants.kDriveDeadband),
                                true),
                        m_robotDrive));
    }

    private void configureBindings() {
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
