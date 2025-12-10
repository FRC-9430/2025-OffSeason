package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;

//apologies for any errors or way wonky code </3 i only halfway knew what i was doing in any of this
public class AprilTagSubsystem {
    PhotonCamera camera = new PhotonCamera("Arducam_1");
    PhotonPipelineResult latestResult;

    public AprilTagSubsystem() {

    }

    public void getLatestResults() {
        PhotonPipelineResult res = camera.getLatestResult();

        if (res.hasTargets()) {
            latestResult = res;
        }

    }

    void printLatestResult() {
        System.out.println(latestResult.getBestTarget().yaw);
        System.out.println(latestResult.getBestTarget().pitch);
        System.out.println(latestResult.getBestTarget().area);
        System.out.println(latestResult.getBestTarget().skew);

    }

}
