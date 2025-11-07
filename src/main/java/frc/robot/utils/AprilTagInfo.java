package frc.robot.utils;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.Constants.AprilTagConstants;

public class AprilTagInfo {

    public static Transform3d getTransform3d(int id) {
        switch (id) {
            case 1:
                return AprilTagConstants.tag1_transformation3d;
            case 2:
                return AprilTagConstants.tag2_transformation3d;
            case 3:
                return AprilTagConstants.tag3_transformation3d;
            case 4:
                return AprilTagConstants.tag4_transformation3d;
            case 5:
                return AprilTagConstants.tag5_transformation3d;
            case 6:
                return AprilTagConstants.tag6_transformation3d;
            case 7:
                return AprilTagConstants.tag7_transformation3d;
            case 8:
                return AprilTagConstants.tag8_transformation3d;
            case 9:
                return AprilTagConstants.tag9_transformation3d;
            case 10:
                return AprilTagConstants.tag10_transformation3d;
            case 11:
                return AprilTagConstants.tag11_transformation3d;
            case 12:
                return AprilTagConstants.tag12_transformation3d;
            case 13:
                return AprilTagConstants.tag13_transformation3d;
            case 14:
                return AprilTagConstants.tag14_transformation3d;
            case 15:
                return AprilTagConstants.tag15_transformation3d;
            case 16:
                return AprilTagConstants.tag16_transformation3d;
            case 17:
                return AprilTagConstants.tag17_transformation3d;
            case 18:
                return AprilTagConstants.tag18_transformation3d;
            case 19:
                return AprilTagConstants.tag19_transformation3d;
            case 20:
                return AprilTagConstants.tag20_transformation3d;
            case 21:
                return AprilTagConstants.tag21_transformation3d;
            case 22:
                return AprilTagConstants.tag22_transformation3d;
            default:
                return new Transform3d();
        }
    }

    public static Translation3d getTranslation3d(int id) {
        return getTransform3d(id).getTranslation();
    }

    public static Rotation3d getRotation3d(int id) {
        return getTransform3d(id).getRotation();
    }
}
