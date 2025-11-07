// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.Meter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
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

    private CameraDetection FRONT_LEFT_DETECTION;
    private CameraDetection FRONT_RIGHT_DETECTION;
    private CameraDetection BACK_LEFT_DETECTION;
    private CameraDetection BACK_RIGHT_DETECTION;

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

    public void updateDetections(Rotation2d gyroRotation) {
      /*
       * FRONT LEFT CAMERA
       */

      PhotonPipelineResult FL_result = VisionConstants.FRONT_LEFT_CAMERA.getLatestResult();
      PhotonTrackedTarget FL_bestTarget = FL_result.getBestTarget();
      Transform3d FL_cameraToTarget = (FL_bestTarget != null) ? FL_bestTarget.getBestCameraToTarget()
          : new Transform3d();
      Transform3d FL_robotToTarget = VisionConstants.FRONT_LEFT_CAMERA_LOCATION.plus(FL_cameraToTarget);
      Translation3d FL_translation = FL_robotToTarget.getTranslation();
      Rotation3d FL_rotation = FL_robotToTarget.getRotation();

      // Create and store detection info for this camera.
      CameraDetection FL_detection = new CameraDetection(
          (FL_bestTarget != null) ? FL_bestTarget.getFiducialId() : -1,
          FL_result.getTimestampSeconds(),
          gyroRotation,
          FL_translation.getMeasureX().in(Meter),
          FL_rotation.getMeasureZ().in(Degree),
          FL_translation.getMeasureY().in(Meter),
          FL_cameraToTarget.getTranslation().getX(),
          FL_cameraToTarget.getTranslation().getY(),
          FL_rotation.getMeasureZ().in(Degree),
          VisionConstants.FRONT_LEFT_CAMERA);

      FRONT_LEFT_DETECTION = FL_detection;
      logCameraDetection(FRONT_LEFT_DETECTION);

      /*
       * FRONT RIGHT CAMERA
       */

      PhotonPipelineResult FR_result = VisionConstants.FRONT_RIGHT_CAMERA.getLatestResult();
      PhotonTrackedTarget FR_bestTarget = FR_result.getBestTarget();
      Transform3d FR_cameraToTarget = (FR_bestTarget != null) ? FR_bestTarget.getBestCameraToTarget()
          : new Transform3d();
      Transform3d FR_robotToTarget = VisionConstants.FRONT_RIGHT_CAMERA_LOCATION.plus(FR_cameraToTarget);
      Translation3d FR_translation = FR_robotToTarget.getTranslation();
      Rotation3d FR_rotation = FR_robotToTarget.getRotation();

      // Create and store detection info for this camera.
      CameraDetection FR_detection = new CameraDetection(
          (FR_bestTarget != null) ? FR_bestTarget.getFiducialId() : -1,
          FR_result.getTimestampSeconds(),
          gyroRotation,
          FR_translation.getMeasureX().in(Meter),
          FR_rotation.getMeasureZ().in(Degree),
          FR_translation.getMeasureY().in(Meter),
          FR_cameraToTarget.getTranslation().getX(),
          FR_cameraToTarget.getTranslation().getY(),
          FR_rotation.getMeasureZ().in(Degree),
          VisionConstants.FRONT_RIGHT_CAMERA);

      FRONT_RIGHT_DETECTION = FR_detection;
      logCameraDetection(FRONT_RIGHT_DETECTION);

      /*
       * BACK LEFT CAMERA
       */

      PhotonPipelineResult BL_result = VisionConstants.BACK_LEFT_CAMERA.getLatestResult();
      PhotonTrackedTarget BL_bestTarget = BL_result.getBestTarget();
      Transform3d BL_cameraToTarget = (BL_bestTarget != null) ? BL_bestTarget.getBestCameraToTarget()
          : new Transform3d();
      Transform3d BL_robotToTarget = VisionConstants.BACK_LEFT_CAMERA_LOCATION.plus(BL_cameraToTarget);
      Translation3d BL_translation = BL_robotToTarget.getTranslation();
      Rotation3d BL_rotation = BL_robotToTarget.getRotation();

      // Create and store detection info for this camera.
      CameraDetection BL_detection = new CameraDetection(
          (BL_bestTarget != null) ? BL_bestTarget.getFiducialId() : -1,
          BL_result.getTimestampSeconds(),
          gyroRotation,
          BL_translation.getMeasureX().in(Meter),
          BL_rotation.getMeasureZ().in(Degree),
          BL_translation.getMeasureY().in(Meter),
          BL_cameraToTarget.getTranslation().getX(),
          BL_cameraToTarget.getTranslation().getY(),
          BL_rotation.getMeasureZ().in(Degree),
          VisionConstants.BACK_LEFT_CAMERA);

      BACK_LEFT_DETECTION = BL_detection;
      logCameraDetection(BACK_LEFT_DETECTION);

      /*
       * BACK RIGHT CAMERA
       */

      PhotonPipelineResult BR_result = VisionConstants.BACK_RIGHT_CAMERA.getLatestResult();
      PhotonTrackedTarget BR_bestTarget = BR_result.getBestTarget();
      Transform3d BR_cameraToTarget = (BR_bestTarget != null) ? BR_bestTarget.getBestCameraToTarget()
          : new Transform3d();
      Transform3d BR_robotToTarget = VisionConstants.BACK_RIGHT_CAMERA_LOCATION.plus(BR_cameraToTarget);
      Translation3d BR_translation = BR_robotToTarget.getTranslation();
      Rotation3d BR_rotation = BR_robotToTarget.getRotation();

      // Create and store detection info for this camera.
      CameraDetection BR_detection = new CameraDetection(
          (BR_bestTarget != null) ? BR_bestTarget.getFiducialId() : -1,
          BR_result.getTimestampSeconds(),
          gyroRotation,
          BR_translation.getMeasureX().in(Meter),
          BR_rotation.getMeasureZ().in(Degree),
          BR_translation.getMeasureY().in(Meter),
          BR_cameraToTarget.getTranslation().getX(),
          BR_cameraToTarget.getTranslation().getY(),
          BR_rotation.getMeasureZ().in(Degree),
          VisionConstants.BACK_RIGHT_CAMERA);

      BACK_RIGHT_DETECTION = BR_detection;
      logCameraDetection(BACK_RIGHT_DETECTION);
    }

    // Helper method to get the most recent detection across all cameras.
    private CameraDetection getMostRecentDetection() {
      CameraDetection[] detections = {
          FRONT_LEFT_DETECTION,
          FRONT_RIGHT_DETECTION,
          BACK_LEFT_DETECTION,
          BACK_RIGHT_DETECTION
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

    private CameraDetection getDetectionOf(PhotonCamera camera) {
      switch (camera.getName()) {
        case VisionConstants.FL_CAMERA_NAME:
          return FRONT_LEFT_DETECTION;

        case VisionConstants.FR_CAMERA_NAME:
          return FRONT_RIGHT_DETECTION;

        case VisionConstants.BL_CAMERA_NAME:
          return BACK_LEFT_DETECTION;

        case VisionConstants.BR_CAMERA_NAME:
          return BACK_RIGHT_DETECTION;

        default:
          return null;
      }
    }

    public int getLastDetectedTagId() {
      CameraDetection detection = getMostRecentDetection();
      return detection != null ? detection.tagId : -1;
    }

    public PhotonCamera getLastDetectionCamera() {
      CameraDetection detection = getMostRecentDetection();
      return detection.camera;
    }

    public double getLastDetectionTimestamp() {
      CameraDetection detection = getMostRecentDetection();
      return detection != null ? detection.detectionTimestamp : -1.0;
    }

    /**
     * Gets the distance to the tag for a specified camera index.
     * 
     * @param cameraIndex The index of the camera.
     * @return The distance to the tag in meters, or NaN if no detection exists.
     */
    public double getDistanceToTag(PhotonCamera camera) {
      CameraDetection detection = getDetectionOf(camera);
      return detection != null ? detection.distanceToTag : Double.NaN;
    }

    /**
     * Gets the lateral offset to the tag for a specified camera index.
     * 
     * @param cameraIndex The index of the camera.
     * @return The lateral offset in meters, or NaN if no detection exists.
     */
    public double getLateralOffsetToTag(PhotonCamera camera) {
      CameraDetection detection = getDetectionOf(camera);
      return detection != null ? detection.lateralOffsetToTag : Double.NaN;
    }

    /**
     * Angle from robot's forward axis to the tag's position. Positive means tag is
     * to the left.
     */
    public double getBearingToTagDeg() {
      CameraDetection detection = getMostRecentDetection();
      return detection != null ? detection.bearingToTagDeg : Double.NaN;
    }

    public double getBearingToTagDeg(PhotonCamera camera) {
      CameraDetection detection = getDetectionOf(camera);
      return detection != null ? detection.bearingToTagDeg : Double.NaN;
    }

    /**
     * The tag's orientation relative to the robot. 0° means tag and robot are
     * parallel.
     */
    public double getTagOrientationErrorDeg() {
      CameraDetection detection = getMostRecentDetection();
      return detection != null ? detection.tagOrientationErrorDeg : Double.NaN;
    }

    public double getTagOrientationErrorDeg(PhotonCamera camera) {
      CameraDetection detection = getDetectionOf(camera);
      return detection != null ? detection.tagOrientationErrorDeg : Double.NaN;
    }

    /**
     * Gets the X offset to the tag from the most recent detection.
     * 
     * @return The X offset in meters.
     */
    public double getXOffsetToTag() {
      CameraDetection detection = getMostRecentDetection();
      return detection != null ? detection.xOffsetToTag : Double.NaN;
    }

    public double getXOffsetToTag(PhotonCamera camera) {
      CameraDetection detection = getDetectionOf(camera);
      return detection != null ? detection.xOffsetToTag : Double.NaN;
    }

    /**
     * Gets the X offset to the tag from the most recent detection.
     * 
     * @return The X offset in meters.
     */
    public double getYOffsetToTag() {
      CameraDetection detection = getMostRecentDetection();
      return detection != null ? detection.yOffsetToTag : Double.NaN;
    }

    public double getYOffsetToTag(PhotonCamera camera) {
      CameraDetection detection = getDetectionOf(camera);
      return detection != null ? detection.yOffsetToTag : Double.NaN;
    }

    public Pose2d getEstimatedPose() {
      return poseEstimator.getPoseMeters();
    }

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