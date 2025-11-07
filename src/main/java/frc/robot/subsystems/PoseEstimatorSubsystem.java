// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Pose estimation subsyestem designed to overwrite the default swerve odometry
 * with vision and gyroscope data.
 */
public class PoseEstimatorSubsystem extends SubsystemBase {

  private SwerveDriveOdometry m_odometry;

  /** Creates a new PoseEstimatorSubsystem. */
  public PoseEstimatorSubsystem() {
    m_odometry = new SwerveDriveOdometry(
        Constants.DriveConstants.kDriveKinematics,
        new Rotation2d(),
        new SwerveModulePosition[] {
            new SwerveModulePosition(),
            new SwerveModulePosition(),
            new SwerveModulePosition(),
            new SwerveModulePosition()
        });
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetPosition(Pose2d pose) {
    m_odometry.resetPose(pose);
  }

  /**
   * Update the odometry with the latest swerve module positions.
   * 
   * @param swervePositions
   */
  public void update(Rotation2d rotation, SwerveModulePosition[] swervePositions) {
    m_odometry.update(rotation, swervePositions);
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
   * Returns the odometry estimated pose of the robot.
   * 
   * @return
   */
  private Pose2d getOdometryEstimatedPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the camera estimated pose of the robot.
   * 
   * @return
   */
  private Pose2d getCameraEstimatedPose() {
    return new Pose2d();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private class CameraPoseEstimation {
    /**
     * Class for handling camera detections.
     */
    private static class CameraDetection {
      int tagId;
      double detectionTimestamp;
      Rotation2d detectionHeading;
      double distanceToTag;
      double bearingToTagDeg;
      double lateralOffsetToTag;
      double xOffsetToTag;
      double yOffsetToTag;
      double tagOrientationErrorDeg;
      PhotonCamera camera;

      public CameraDetection(int tagId, double detectionTimestamp, Rotation2d detectionHeading,
              double distanceToTag, double bearingToTagDeg, double lateralOffsetToTag,
              double xOffsetToTag, double yOffsetToTag, double tagOrientationErrorDeg,
              PhotonCamera camera) {
          this.tagId = tagId;
          this.detectionTimestamp = detectionTimestamp;
          this.detectionHeading = detectionHeading;
          this.distanceToTag = distanceToTag;
          this.bearingToTagDeg = bearingToTagDeg;
          this.lateralOffsetToTag = lateralOffsetToTag;
          this.xOffsetToTag = xOffsetToTag;
          this.yOffsetToTag = yOffsetToTag;
          this.tagOrientationErrorDeg = tagOrientationErrorDeg;
          this.camera = camera;
      }
    }

  }
}