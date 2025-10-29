package frc.robot.subsystems;

//apologies for any errors or way wonky code </3 i only halfway knew what i was doing in any of this

//initial camera setup
        PhotonCamera camera = new PhotonCamera("photoncamera");


//tags, tracking, and targets

    //query the latest result from photonvision
        var result = camera.getLatestResult();

    //check latest result for targets
        boolean hasTargets = result.hasTargets();

    //retrieve list of recently tracked targets
        List<PhotonTrackedTarget> targets = result.getTargets();

    //get the current best target
        PhotonTrackedTarget target = result.getBestTarget();

    //get target information
        double yaw = target.getYaw();
        double pitch = target.getPitch();
        double area = target.getArea();
        double skew = target.getSkew();
        Transform2d pose = target.getCameraToTarget();
        List<TargetCorner> corners = target.getCorners();

    //get MORE info from a target (apriltags)
        int targetID = target.getFiducialId();
        double poseAmbiguity = target.getPoseAmbiguity();
        Transform3d bestCameraToTarget = target.getBestCameraToTarget();
        Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();


//relative positions, tracking poses, calculating distance
    
    //calculate robot's relative field position
         Pose2D robotPose = PhotonUtils.estimateFieldToRobot(
  kCameraHeight, kTargetHeight, kCameraPitch, kTargetPitch, Rotation2d.fromDegrees(-target.getYaw()), gyro.getRotation2d(), targetPose, cameraToRobot);
      
    //calculating distance between two poses
         double distanceToTarget = PhotonUtils.getDistanceToPose(robotPose, targetPose);