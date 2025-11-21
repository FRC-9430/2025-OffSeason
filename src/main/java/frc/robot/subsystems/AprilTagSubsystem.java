package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;

import org.photonvision.targeting.PhotonPipelineResult;

//apologies for any errors or way wonky code </3 i only halfway knew what i was doing in any of this
public class AprilTagSubsystem{
    PhotonCamera camera; 
    
    public AprilTagSubsystem() {
        camera = new PhotonCamera("Arducam_1");
    }

    void printLatestResult() {
        PhotonTrackedTarget targetTag = camera.getLatestResult().getBestTarget();
       int targetTagID = targetTag.getDetectedObjectClassID();
        System.out.println(targetTag.yaw);
        System.out.println(targetTag.pitch);
        System.out.println(targetTag.area);
        System.out.println(targetTag.skew);
        System.out.println();
    }

    public void printTargetInformation() {
        if 


    }
}
