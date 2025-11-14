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
        public static final int ElevatorMotorCanID = 27;
        public static final double kElevatorMaxHeight = 0; //TODO set value    
        public static final double kElevatorMinHeight = 0; //TODO set value
        public static final double kShooterPivotMaxHeight = .48;
        public static final double kShooterPivotMinHeight = .090;

    }
    public static final class PIDConstants{
        public static final double kShooterkp = 0.1; //TO DO: set shooter PID values
        public static final double kShooterki = 0;
        public static final double kShooterkd = 0;
        public static final double kElevatorkp = 0.1; //TO DO: set elevator PID values
        public static final double kElevatorki = 0;
        public static final double kElevatorkd = 0;
        public static final double kIntakeSetpoint = .2;
        public static final double kScoreSetpoint = .1;
    }
}
