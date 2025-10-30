package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.utils.SwerveModule;

public class DriveSubsystem extends SubsystemBase {

  // Each of these is one of our swerve modules (the wheels + motors that can spin
  // and turn).
  // We have four modules: front-left, front-right, back-left, and back-right.
  public final SwerveModule m_frontLeft = new SwerveModule(
      DriveConstants.kFrontLeftDriveMotorCanID,
      DriveConstants.kFrontLeftTurningMotorCanID,
      DriveConstants.kFrontLeftChassisAngularOffset);

  public final SwerveModule m_frontRight = new SwerveModule(
      DriveConstants.kFrontRightDriveMotorCanID,
      DriveConstants.kFrontRightTurningMotorCanID,
      DriveConstants.kFrontRightChassisAngularOffset);

  public final SwerveModule m_rearLeft = new SwerveModule(
      DriveConstants.kBackLeftDriveMotorCanID,
      DriveConstants.kBackLeftTurningMotorCanID,
      DriveConstants.kBackLeftChassisAngularOffset);

  public final SwerveModule m_rearRight = new SwerveModule(
      DriveConstants.kBackRightDriveMotorCanID,
      DriveConstants.kBackRightTurningMotorCanID,
      DriveConstants.kBackRightChassisAngularOffset);

  // The gyro sensor
  private final Pigeon2 m_gyro = new Pigeon2(DriveConstants.pigeon2CanID);

  // Odometry class for tracking robot pose
  private final SwerveDriveOdometry m_odometry;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    

    m_odometry = new SwerveDriveOdometry(null, null, null);
  }

  @Override
  public void periodic() {
    
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    
  }

  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    // Convert the commanded speeds into the correct units for the drivetrain
    double xSpeedDelivered = xSpeed * DriveConstants.kMaxSpeedMetersPerSecond;
    double ySpeedDelivered = ySpeed * DriveConstants.kMaxSpeedMetersPerSecond;
    double rotDelivered = rot * DriveConstants.kMaxAngularSpeed;
    // TODO: FIX TO USE TO ROBOT RELATIVE SPEEDS
    var swerveModuleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(
        fieldRelative
            ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered,
                m_gyro.getRotation2d())
            : new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));
    SwerveDriveKinematics.desaturateWheelSpeeds(
        swerveModuleStates, DriveConstants.kMaxSpeedMetersPerSecond);
    m_frontLeft.setDesiredState(swerveModuleStates[0]);
    m_frontRight.setDesiredState(swerveModuleStates[1]);
    m_rearLeft.setDesiredState(swerveModuleStates[2]);
    m_rearRight.setDesiredState(swerveModuleStates[3]);
  }


  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_gyro.getAngularVelocityZWorld().getValueAsDouble();
  }
}