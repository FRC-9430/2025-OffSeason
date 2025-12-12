// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.config.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class RobotContainer {
        // The robot's subsystems
        private CommandSwerveDrivetrain driveTrain = TunerConstants.createDrivetrain();
        private SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
                        .withDeadband(OIConstants.kDriveDeadband)
                        .withRotationalDeadband(OIConstants.kDriveDeadband)
                        .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

        // The driver's controller
        public static CommandXboxController c_driverController = new CommandXboxController(
                        OIConstants.kDriverControllerPort);

        // The operator's controller
        public static CommandXboxController c_operatorController = new CommandXboxController(
                        OIConstants.kOperatorControllerPort);

        // private final SendableChooser<Command> autoChooser;

        public RobotContainer() {
                configureBindings();

                // autoChooser = AutoBuilder.buildAutoChooser();

                // SmartDashboard.putData("Auto Chooser", autoChooser);

                // Configure default commands
                driveTrain.setDefaultCommand(
                                driveTrain.applyRequest(
                                        () -> drive.withVelocityX(-c_driverController.getLeftY() * DriveConstants.kMaxSpeedMetersPerSecond)
                                                        .withVelocityY(-c_driverController.getLeftX() * DriveConstants.kMaxSpeedMetersPerSecond)
                                                        .withRotationalRate(-c_driverController.getRightX() * DriveConstants.kMaxAngularSpeed)));

                CommandScheduler.getInstance().run();

        }

        private void configureBindings() {

                final var idle = new SwerveRequest.Idle();
                RobotModeTriggers.disabled().whileTrue(
                        driveTrain.applyRequest(() -> idle).ignoringDisable(true)
                );
                
                c_driverController.leftBumper().onTrue(driveTrain.runOnce(() -> driveTrain.seedFieldCentric()));
        }

        public Command getAutonomousCommand() {
                // return autoChooser.getSelected();
                return Commands.none();
        }
}
