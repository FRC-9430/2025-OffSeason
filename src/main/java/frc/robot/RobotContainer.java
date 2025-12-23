// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.config.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.DashboardSubsystem;
import frc.robot.subsystems.PoseEstimatorSubsystem;

public class RobotContainer {
    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second
                                                                                      // max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.06).withRotationalDeadband(MaxAngularRate * 0.08) // Add deadbands
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController c_DriverController = new CommandXboxController(0);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    public final PoseEstimatorSubsystem vision = new PoseEstimatorSubsystem(drivetrain::addVisionMeasurement);

    public final DashboardSubsystem dash = new DashboardSubsystem();

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
                // Drivetrain will execute this command periodically
                drivetrain
                        .applyRequest(() -> drive.withVelocityX(-Math.pow(c_DriverController.getLeftY(), 5) * MaxSpeed) // Drive
                                                                                                                        // forward
                                                                                                                        // with
                                                                                                                        // negative
                                                                                                                        // Y
                                                                                                                        // (forward)
                                .withVelocityY(-Math.pow(c_DriverController.getLeftX(), 5) * MaxSpeed) // Drive left
                                                                                                       // with negative
                                                                                                       // X (left)
                                .withRotationalRate(-c_DriverController.getRightX() * MaxAngularRate) // Drive
                                                                                                      // counterclockwise
                                                                                                      // with negative X
                                                                                                      // (left)
                        ));

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.
        final var idle = new SwerveRequest.Idle();
        RobotModeTriggers.disabled().whileTrue(
                drivetrain.applyRequest(() -> idle).ignoringDisable(true));

        c_DriverController.a().whileTrue(drivetrain.applyRequest(() -> brake));
        c_DriverController.b().whileTrue(drivetrain.applyRequest(() -> point
                .withModuleDirection(new Rotation2d(-c_DriverController.getLeftY(), -c_DriverController.getLeftX()))));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        c_DriverController.back().and(c_DriverController.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        c_DriverController.back().and(c_DriverController.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        c_DriverController.start().and(c_DriverController.y())
                .whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        c_DriverController.start().and(c_DriverController.x())
                .whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }

    public void setInitalPose() {
        drivetrain.resetPose(dash.getInitialPoseFromChooser().toPose2d());
    }
}
