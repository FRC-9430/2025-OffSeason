package frc.robot;

import org.photonvision.PhotonCamera;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

public class Constants {

        public class DriveConstants {

                public static final int kFrontLeftDriveMotorCanID = 11;
                public static final int kFrontLeftTurningMotorCanID = 10;
                public static final int kFrontRightDriveMotorCanID = 13;
                public static final int kFrontRightTurningMotorCanID = 12;
                public static final int kBackLeftDriveMotorCanID = 15;
                public static final int kBackLeftTurningMotorCanID = 14;
                public static final int kBackRightDriveMotorCanID = 17;
                public static final int kBackRightTurningMotorCanID = 16;

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

        public static final class VisionConstants {
                public static final int[] kAlignApriltagIDs = new int[] { 6 };

                public static final String FL_CAMERA_NAME = "Arducam_FL";
                public static final String FR_CAMERA_NAME = "Arducam_FR";
                public static final String BL_CAMERA_NAME = "Arducam_BL";
                public static final String BR_CAMERA_NAME = "Arducam_BR";

                public static final PhotonCamera FRONT_LEFT_CAMERA = new PhotonCamera(FL_CAMERA_NAME);
                public static final PhotonCamera FRONT_RIGHT_CAMERA = new PhotonCamera(FR_CAMERA_NAME);
                public static final PhotonCamera BACK_LEFT_CAMERA = new PhotonCamera(BL_CAMERA_NAME);
                public static final PhotonCamera BACK_RIGHT_CAMERA = new PhotonCamera(BR_CAMERA_NAME);

                public static final Transform3d FRONT_LEFT_CAMERA_LOCATION = new Transform3d(
                                new Translation3d(0.284, 0.284, 0.209),
                                new Rotation3d(0, -Math.toRadians(15), Math.toRadians(45)));

                public static final Transform3d FRONT_RIGHT_CAMERA_LOCATION = new Transform3d(
                                new Translation3d(0.284, -0.284, 0.209),
                                new Rotation3d(0, -Math.toRadians(15), -Math.toRadians(45)));

                public static final Transform3d BACK_LEFT_CAMERA_LOCATION = new Transform3d(
                                new Translation3d(-0.284, 0.284, 0.209),
                                new Rotation3d(0, -Math.toRadians(15), Math.toRadians(45)));

                public static final Transform3d BACK_RIGHT_CAMERA_LOCATION = new Transform3d(
                                new Translation3d(-0.284, -0.284, 0.209),
                                new Rotation3d(0, -Math.toRadians(15), -Math.toRadians(45)));
        }

        public static final class AprilTagConstants {
                // TODO set values based on current field layout
                public static final Translation3d tag1_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag2_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag3_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag4_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag5_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag6_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag7_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag8_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag9_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag10_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag11_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag12_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag13_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag14_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag15_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag16_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag17_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
                public static final Translation3d tag18_translation3d = new Translation3d(16.4592, 1.524, 0.3302);
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

        public static final class Configs {

                public static final class X2TSwerveModule {
                        public static final SparkMaxConfig drivingConfig = new SparkMaxConfig();
                        public static final SparkMaxConfig turningConfig = new SparkMaxConfig();

                        static {
                                // Use module constants to calculate conversion factors and feed forward gain.
                                double drivingFactor = ModuleConstants.kWheelDiameterMeters * Math.PI
                                                / ModuleConstants.kDrivingMotorReduction;
                                double turningFactor = 2 * Math.PI;
                                double drivingVelocityFeedForward = 1 / ModuleConstants.kDriveWheelFreeSpeedRps;

                                drivingConfig
                                                .idleMode(IdleMode.kBrake)
                                                .smartCurrentLimit(50);
                                drivingConfig.encoder
                                                .positionConversionFactor(drivingFactor) // meters
                                                .velocityConversionFactor(drivingFactor / 60.0); // meters per second
                                drivingConfig.closedLoop
                                                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                                                // These are example gains you may need to them for your own robot!
                                                .pid(0.04, 0, 0)
                                                .velocityFF(drivingVelocityFeedForward)
                                                .outputRange(-1, 1);

                                turningConfig
                                                .idleMode(IdleMode.kBrake)
                                                .smartCurrentLimit(20);
                                turningConfig.absoluteEncoder
                                                // Invert the turning encoder, since the output shaft rotates in the
                                                // opposite
                                                // direction of the steering motor in the MAXSwerve Module.
                                                .inverted(true)
                                                .positionConversionFactor(turningFactor) // radians
                                                .velocityConversionFactor(turningFactor / 60.0); // radians per second
                                turningConfig.closedLoop
                                                .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                                                // These are example gains you may need to them for your own robot!
                                                .pid(1, 0, 0)
                                                .outputRange(-1, 1)
                                                // Enable PID wrap around for the turning motor. This will allow the PID
                                                // controller to go through 0 to get to the setpoint i.e. going from 350
                                                // degrees
                                                // to 10 degrees will go through 0 rather than the other direction which
                                                // is a
                                                // longer route.
                                                .positionWrappingEnabled(true)
                                                .positionWrappingInputRange(0, turningFactor);
                        }
                };

                public static final class ModuleConstants {
                        // The MAXSwerve module can be configured with one of three pinion gears: 12T,
                        // 13T, or 14T. This changes the drive speed of the module (a pinion gear with
                        // more teeth will result in a robot that drives faster).
                        public static final int kDrivingMotorPinionTeeth = 14;

                        // Calculations required for driving motor conversion factors and feed forward
                        public static final double kDrivingMotorFreeSpeedRps = 5676 / 60;
                        public static final double kWheelDiameterMeters = 0.0729;
                        public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
                        // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15
                        // teeth on the bevel pinion
                        // TODO bbontrager89 20241107.1742: Need to update values for
                        // kDrivingMotorReduction
                        public static final double kDrivingMotorReduction = (45.0 * 22)
                                        / (kDrivingMotorPinionTeeth * 15);
                        public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps
                                        * kWheelCircumferenceMeters)
                                        / kDrivingMotorReduction;
                }
        }

}
