package frc.robot;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;

public class Constants {

        public class DriveConstants {

                public static final int kBRDriveMotorCanID = 11;
                public static final int kBRTurningMotorCanID = 12;

                public static final int kBLDriveMotorCanID = 13;
                public static final int kBLTurningMotorCanID = 14;

                public static final int kFRDriveMotorCanID = 15;
                public static final int kFRTurningMotorCanID = 16;

                public static final int kFLDriveMotorCanID = 17;
                public static final int kFLTurningMotorCanID = 18;

                public static final int kFREncoderID = 21;
                public static final int kBREncoderID = 22;
                public static final int kBLEncoderID = 23;
                public static final int kFLEncoderID = 24;

                public static final int pigeon2CanID = 1;

                public static final Translation3d pigeon_translation = new Translation3d(-0.2032, 0.1016, 0.0);

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
                public static final double kFrontRightChassisAngularOffset = Math.PI;
                public static final double kBackLeftChassisAngularOffset = 0;
                public static final double kBackRightChassisAngularOffset = Math.PI / 2;
        }

        public class OIConstants {
                public static final int kDriverControllerPort = 0;
                public static final int kOperatorControllerPort = 1;
                public static final double kDriveDeadband = 0.08;
                public static final double kTriggerThreshold = 0.1;
        }

        public static final class VisionConstants {
                public static final int[] kAlignApriltagIDs = new int[] { 6 };

                public static final String FL_CAMERA_NAME = "Arducam_FL";
                public static final String FR_CAMERA_NAME = "Arducam_FR";

                // The layout of the AprilTags on the field
                public static final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout
                                .loadField(AprilTagFields.kDefaultField);
                                
                // The standard deviations of our vision estimated poses, which affect
                // correction rate
                // Calculated usign mean deviation of pixels of 0.23, doubled for z, 1/8 for multiple tags
                public static final Matrix<N3, N1> kSingleTagStdDevs = VecBuilder.fill(0.25, 0.25, 0.5);
                public static final Matrix<N3, N1> kMultiTagStdDevs = VecBuilder.fill(0.03125, 0.03125, 0.0625);

                public static final Transform3d FRONT_LEFT_CAMERA_LOCATION = new Transform3d(
                                new Translation3d(0.284, 0.284, 0.209),
                                new Rotation3d(0, -Math.toRadians(15), Math.toRadians(45)));

                public static final Transform3d FRONT_RIGHT_CAMERA_LOCATION = new Transform3d(
                                new Translation3d(0.284, -0.284, 0.209),
                                new Rotation3d(0, -Math.toRadians(15), -Math.toRadians(45)));

                public static final Transform3d getTransformOf(PhotonCamera camera) {
                        switch (camera.getName()) {
                                case FL_CAMERA_NAME:
                                        return FRONT_LEFT_CAMERA_LOCATION;

                                case FR_CAMERA_NAME:
                                        return FRONT_RIGHT_CAMERA_LOCATION;

                                default:
                                        return null;
                        }
                }

        }

        public static final class AutoConstants {
                public static final double kMaxSpeedMetersPerSecond = 3;
                public static final double kMaxAccelerationMetersPerSecondSquared = 3;
                public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
                public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

                public static final double kPXController = 1;
                public static final double kPYController = 1;
                public static final double kPThetaController = 1;

                // Constraint for the motion profiled robot angle controller
                public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
                                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
        }

}
