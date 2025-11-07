// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public final class Constants {
    public static final class OIConstants{
        public static final int k_operatorControllerPort = 1;
    }
    public static final class MotorConstants{
        public static final int IntakeMotorCanID = 41;
        public static final int ShooterMotorCanID = 42;
        // ToDo get can ID of elevator motors       public static final int ElevatorMotorCanID = ?;
        // ToDO set elevtor min and max             public static final double kElevatorMaxHeight = ?;     public static final double kElevatorMinHeight = ?;
        public static final double kShooterPivotMaxHeight = .48;
        public static final double kShooterPivotMinHeight = .090;

    }
    public static final class PIDConstants{
        public static final double kShooterkp = 0.1; //TODO: set PID values
        public static final double kShooterki = 0;
        public static final double kShooterkd = 0;
        public static final double kTransitSetpoint = .255;
        public static final double kIntakeSetpoint = .2;
        public static final double kScoreSetpoint = .1;
    }
}
