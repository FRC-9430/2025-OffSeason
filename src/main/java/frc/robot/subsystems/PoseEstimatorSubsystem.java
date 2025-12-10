// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.Meter;

import java.util.List;
import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.AprilTagConstants;
import frc.robot.Constants.VisionConstants;

/**
 * Pose estimation subsyestem designed to overwrite the default swerve odometry
 * with vision and gyroscope data.
 */
public class PoseEstimatorSubsystem extends SubsystemBase {

  private SwerveDriveOdometry m_odometry;

  private CameraPoseEstimation m_cameraPoseEstimation = new CameraPoseEstimation();

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
    if (m_cameraPoseEstimation.seesTag()) {
      m_odometry.resetPose(getCameraEstimatedPose().toPose2d());
    }

    return getOdometryEstimatedPose();
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
  private Pose3d getCameraEstimatedPose() {
    return m_cameraPoseEstimation.getEstimatedPose();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_cameraPoseEstimation.updateDetections();
  }

  private class CameraPoseEstimation {

    private CameraDetection FRONT_LEFT_DETECTION;
    private CameraDetection FRONT_RIGHT_DETECTION;

    public static void logCameraDetection(CameraDetection detection) {
      String cameraName = detection.camera.getName();
      // Update dashboard with camera-specific info.
      SmartDashboard.putBoolean("Camera " + cameraName + " Has Detection", true);
      SmartDashboard.putNumber("Camera " + cameraName + " Last Detection Time", detection.detectionTimestamp);
      SmartDashboard.putNumber("Camera " + cameraName + " Last Tag ID", detection.tagId);
      SmartDashboard.putNumber("Camera " + cameraName + " DistanceToTag", detection.distanceToTag);
      SmartDashboard.putNumber("Camera " + cameraName + " Tag Bearing (Deg)", detection.bearingToTagDeg);
      SmartDashboard.putNumber("Camera " + cameraName + " Tag Orientation Error (Deg)",
          detection.tagOrientationErrorDeg);
      SmartDashboard.putNumber("Camera " + cameraName + " LateralOffsetToTag", detection.lateralOffsetToTag);
      SmartDashboard.putNumber("Camera " + cameraName + " xOffsetToTag", detection.xOffsetToTag);
      SmartDashboard.putNumber("Camera " + cameraName + " yOffsetToTag", detection.yOffsetToTag);
    }

    public void updateDetections() {
      /*
       * FRONT LEFT CAMERA
       */

      List<PhotonPipelineResult> FL_unreadResults = VisionConstants.FRONT_LEFT_CAMERA.getAllUnreadResults();

      if (FL_unreadResults.size() > 0) {

        PhotonPipelineResult FL_result = FL_unreadResults.size() > 0 ? FL_unreadResults.get(FL_unreadResults.size() - 1)
            : null;
        PhotonTrackedTarget FL_bestTarget = FL_result.getBestTarget();

        if (FL_bestTarget.getFiducialId() != -1) {

          Transform3d FL_cameraToTarget = FL_bestTarget.getBestCameraToTarget();
          Transform3d FL_robotToTarget = VisionConstants.FRONT_LEFT_CAMERA_LOCATION.plus(FL_cameraToTarget);
          Translation3d FL_translation = FL_robotToTarget.getTranslation();
          Rotation3d FL_rotation = FL_robotToTarget.getRotation();

          // Create and store detection info for this camera.
          CameraDetection FL_detection = new CameraDetection(
              FL_bestTarget.getFiducialId(),
              FL_result.getTimestampSeconds(),
              FL_translation.getMeasureX().in(Meter),
              FL_rotation.getMeasureZ().in(Degree),
              FL_translation.getMeasureY().in(Meter),
              FL_cameraToTarget.getTranslation().getX(),
              FL_cameraToTarget.getTranslation().getY(),
              FL_rotation.getMeasureZ().in(Degree),
              VisionConstants.FRONT_LEFT_CAMERA);

          FRONT_LEFT_DETECTION = FL_detection;
          logCameraDetection(FRONT_LEFT_DETECTION);
        }

      }

      /*
       * FRONT RIGHT CAMERA
       */

      List<PhotonPipelineResult> FR_unreadResults = VisionConstants.FRONT_RIGHT_CAMERA.getAllUnreadResults();

      if (FL_unreadResults.size() > 0) {

        PhotonPipelineResult FR_result = FR_unreadResults.size() > 0 ? FL_unreadResults.get(FR_unreadResults.size() - 1)
            : null;
        PhotonTrackedTarget FR_bestTarget = FR_result.getBestTarget();

        if (FR_bestTarget.getFiducialId() != -1) {

          Transform3d FR_cameraToTarget = FR_bestTarget.getBestCameraToTarget();
          Transform3d FR_robotToTarget = VisionConstants.FRONT_RIGHT_CAMERA_LOCATION.plus(FR_cameraToTarget);
          Translation3d FR_translation = FR_robotToTarget.getTranslation();
          Rotation3d FR_rotation = FR_robotToTarget.getRotation();

          // Create and store detection info for this camera.
          CameraDetection FR_detection = new CameraDetection(
              FR_bestTarget.getFiducialId(),
              FR_result.getTimestampSeconds(),
              FR_translation.getMeasureX().in(Meter),
              FR_rotation.getMeasureZ().in(Degree),
              FR_translation.getMeasureY().in(Meter),
              FR_cameraToTarget.getTranslation().getX(),
              FR_cameraToTarget.getTranslation().getY(),
              FR_rotation.getMeasureZ().in(Degree),
              VisionConstants.FRONT_LEFT_CAMERA);

          FRONT_RIGHT_DETECTION = FR_detection;
          logCameraDetection(FRONT_RIGHT_DETECTION);
        }

      }

    }

    public boolean seesTag() {
      if (FRONT_LEFT_DETECTION.tagId != -1)
        return true;

      if (FRONT_RIGHT_DETECTION.tagId != -1)
        return true;

      return false;
    }

    // Helper method to get the most recent detection across all cameras.
    private CameraDetection getMostRecentDetection() {
      CameraDetection[] detections = {
          FRONT_LEFT_DETECTION,
          FRONT_RIGHT_DETECTION
      };
      CameraDetection mostRecent = FRONT_LEFT_DETECTION;
      for (CameraDetection detection : detections) {
        if (detection != null) {
          if (mostRecent == null || detection.detectionTimestamp > mostRecent.detectionTimestamp) {
            mostRecent = detection;
          }
        }
      }
      return mostRecent;
    }

    public CameraDetection getDetectionOf(PhotonCamera camera) {
      switch (camera.getName()) {
        case VisionConstants.FL_CAMERA_NAME:
          return FRONT_LEFT_DETECTION;

        case VisionConstants.FR_CAMERA_NAME:
          return FRONT_RIGHT_DETECTION;

        default:
          return null;
      }
    }

    public Pose3d getEstimatedPose() {
      CameraDetection latestDetection = getMostRecentDetection();
      // Get the pose of latest detected tag from the field layout.
      Optional<Pose3d> latestDetectedTagOptional = AprilTagConstants.kFieldLayout.getTagPose(latestDetection.tagId);
      Pose3d tagPose = latestDetectedTagOptional.get();

      // Calculate robot pose based on the tag pose and the detection offsets.
      double robotX = tagPose.getX() - latestDetection.xOffsetToTag;
      double robotY = tagPose.getY() - latestDetection.yOffsetToTag;
      double robotRotationDeg = Math.toDegrees(tagPose.getRotation().getAngle())
          - latestDetection.tagOrientationErrorDeg;
      Pose3d robotPose = new Pose3d(
          new Translation3d(robotX, robotY, 0.0),
          new Rotation3d(0.0, 0.0, Math.toRadians(robotRotationDeg)));

      SmartDashboard.putNumber("Camera Estimeted Pose X", robotX);
      SmartDashboard.putNumber("Camera Estimeted Pose Y", robotY);

      return robotPose;
    }

    /**
     * Class for handling camera detections.
     */
    private static class CameraDetection {
      /** ID of detected April Tag, -1 if no tag is seen */
      int tagId;
      /** Timestamp of detection in seconds */
      double detectionTimestamp;
      /** Camera's distance to tag */
      double distanceToTag;
      /** Camera's bearing to tag in degrees */
      double bearingToTagDeg;
      /** Camera's lateral offset to tag */
      double lateralOffsetToTag;
      /** Robot horizontal offset */
      double xOffsetToTag;
      /** Robot vertical offset */
      double yOffsetToTag;
      /** Robot rotational error */
      double tagOrientationErrorDeg;
      /** Camera that detected */
      PhotonCamera camera;

      public CameraDetection(int tagId, double detectionTimestamp,
          double distanceToTag, double bearingToTagDeg, double lateralOffsetToTag,
          double xOffsetToTag, double yOffsetToTag, double tagOrientationErrorDeg,
          PhotonCamera camera) {
        this.tagId = tagId;
        this.detectionTimestamp = detectionTimestamp;
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
