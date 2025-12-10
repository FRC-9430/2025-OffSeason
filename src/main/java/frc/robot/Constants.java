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
        public static final double kShooterPivotMaxHeight = .48;
        public static final double kShooterPivotMinHeight = .090;

    }
    public static final class TagConstants{
    //Coral Station (Tag IDs, height, angle)
        public static final int[] coralStationTagIds = {1, 2, 12, 13};
        public static final double coralStationTagHeight = 58.50;
        public static final double coralStationTagAngle = 0;
    // Barge (Tag IDs, height, angle)
        public static final int [] bargeTagIds = {14, 4, 15, 5};
        public static final double bargeTagHeight = 73.54;
        public static final double bargeTagAngle = 30;
    // Reef (IDs, height, angle)
        public static final int [] reefTagIds = {6, 7, 8, 9, 10, 11, 17, 18, 19, 20, 21 ,22};
        public static final double reefTagHeight = 12.13;
        public static final double reefTagAngle = 0;
    }
}
