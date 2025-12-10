package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import org.photonvision.targeting.PhotonPipelineResult;

//apologies for any errors or way wonky code </3 i only halfway knew what i was doing in any of this
public class AprilTagSubsystem{
    PhotonCamera camera; 

    public AprilTagSubsystem() {
        camera = new PhotonCamera("Arducam_1");
    }

    void printLatestResult() {
        PhotonTrackedTarget targetTag = camera.getLatestResult().getBestTarget();
        System.out.println(targetTag.yaw);
        System.out.println(targetTag.pitch);
        System.out.println(targetTag.area);
        System.out.println(targetTag.skew);
        System.out.println();
    }

    public void printTargetInformation() {
        //getting pipeline result, checking for targets
        PhotonPipelineResult targets = camera.getLatestResult();
        boolean presence = targets.hasTargets();
    

        //verifying and executing printing function
          if (presence == true){
            printLatestResult();
          }

    }
}