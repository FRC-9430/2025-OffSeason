package frc.robot.utils;

import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.Constants.AprilTagConstants;

public class AprilTagInfo {

    public static Translation3d getTranslation3d(int id) {
        switch (id) {
            case 1:
                return AprilTagConstants.tag1_translation3d;
            case 2:
                return AprilTagConstants.tag2_translation3d;
            case 3:
                return AprilTagConstants.tag3_translation3d;
            case 4:
                return AprilTagConstants.tag4_translation3d;
            case 5:
                return AprilTagConstants.tag5_translation3d;
            case 6:
                return AprilTagConstants.tag6_translation3d;
            case 7:
                return AprilTagConstants.tag7_translation3d;
            case 8:
                return AprilTagConstants.tag8_translation3d;
            case 9:
                return AprilTagConstants.tag9_translation3d;
            case 10:
                return AprilTagConstants.tag10_translation3d;
            case 11:
                return AprilTagConstants.tag11_translation3d;
            case 12:
                return AprilTagConstants.tag12_translation3d;
            case 13:
                return AprilTagConstants.tag13_translation3d;
            case 14:
                return AprilTagConstants.tag14_translation3d;
            case 15:
                return AprilTagConstants.tag15_translation3d;
            case 16:
                return AprilTagConstants.tag16_translation3d;
            case 17:
                return AprilTagConstants.tag17_translation3d;
            case 18:
                return AprilTagConstants.tag18_translation3d;
            default:
                return new Translation3d();
        }
    }
}
