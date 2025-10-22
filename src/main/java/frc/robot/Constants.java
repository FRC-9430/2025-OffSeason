package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig;

import edu.wpi.first.wpilibj.DigitalSource;

public class Constants {

    public class DriveConstants {

        public static final int kFrontLeftDriveMotorCanID = 1;
        public static final int kFrontLeftTurningMotorCanID = 2;
        public static final int kFrontRightDriveMotorCanID = 3;
        public static final int kFrontRightTurningMotorCanID = 4;
        public static final int kBackLeftDriveMotorCanID = 5;
        public static final int kBackLeftTurningMotorCanID = 6;
        public static final int kBackRightDriveMotorCanID = 7;
        public static final int kBackRightTurningMotorCanID = 8;

        public static final int pigeon2CanID = 0;

    }

    
    public static final class Configs {

        public static final class X2TSwerveModule {
            public static final SparkBaseConfig drivingConfig = null;
            public static final SparkBaseConfig turningConfig = null;
        };


    }
        
}
