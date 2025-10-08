// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
    public RobotContainer() {
        configureBindings();
        Shooter m_Shooter = new Shooter();
    }

    private void configureBindings() {
        // Shooter operation binds
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
