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
    public static CommandXboxController c_operatorController = new CommandXboxController(OIConstants.k_operatorControllerPort);
    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {

    c_operatorController.a()
    .whileTrue(new InstantCommand(() -> { 
      shooterSubsystem.runIntake(.1);
    })) .onFalse(new InstantCommand(() -> { 
        shooterSubsystem.stopIntake();
      }));

      c_operatorController.b()
    .whileTrue(new InstantCommand(() -> { 
      shooterSubsystem.runIntake(-.1);
    })) .onFalse(new InstantCommand(() -> { 
        shooterSubsystem.stopIntake();
        
      }));

    c_operatorController.x()
    .whileTrue(new InstantCommand(() -> { 
      shooterSubsystem.setShooterSpeed(.1);
    })) .onFalse(new InstantCommand(() -> { 
        shooterSubsystem.setShooterSpeed(0);
          
      }));




    }
    

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
