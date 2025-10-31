package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

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

        public static final double kMaxSpeedMetersPerSecond = 4.8;
        public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

        // Chassis configuration
        public static final double kTrackWidth = Units.inchesToMeters(22.5);

        // Distance between centers of right and left wheels on robot
        public static final double kWheelBase = Units.inchesToMeters(22.5);

        // Distance between front and back wheels on robot
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

        // Angular offsets of the modules relative to the chassis in radians
        public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
        public static final double kFrontRightChassisAngularOffset = 0;
        public static final double kBackLeftChassisAngularOffset = Math.PI;
        public static final double kBackRightChassisAngularOffset = Math.PI / 2;
    }

    public class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
        public static final double kDriveDeadband = 0.08;
        public static final double kTriggerThreshold = 0.1;
    }

    public static final class Configs {

        public static final class X2TSwerveModule {
            public static final SparkMaxConfig drivingConfig = new SparkMaxConfig();
            public static final SparkMaxConfig turningConfig = new SparkMaxConfig();
        };

    }

}
