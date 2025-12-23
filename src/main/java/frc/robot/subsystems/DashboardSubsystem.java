// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DashboardSubsystem extends SubsystemBase {

  private static final SendableChooser<String> posePicker = new SendableChooser<>();

  /** Creates a new DashboardSubsystem. */
  public DashboardSubsystem() {
    posePicker.setDefaultOption("Blue Left", "Blue Left");
    posePicker.addOption("Blue Center", "Blue Center");
    posePicker.addOption("Blue Right", "Blue Right");
    posePicker.addOption("Red Left", "Red Left");
    posePicker.addOption("Red Center", "Red Center");
    posePicker.addOption("Red Right", "Red Right");

    SmartDashboard.putData("Initial Pose", posePicker);

  }

  public Pose3d getInitialPoseFromChooser() {
    String selectedPose = posePicker.getSelected();
    switch (selectedPose) {
      case "Blue Left":
        return new Pose3d(new Translation3d(7,6,0), new Rotation3d(0,0,0));
      case "Blue Center":
        return new Pose3d(new Translation3d(7,4,0), new Rotation3d(0,0,0));
      case "Blue Right":
        return new Pose3d(new Translation3d(7,2,0), new Rotation3d(0,0,0));
      case "Red Left":
        return new Pose3d(new Translation3d(10.5,2,0), new Rotation3d(0,0,Math.toRadians(180)));
      case "Red Center":
        return new Pose3d(new Translation3d(10.5,4,0), new Rotation3d(0,0,Math.toRadians(180)));
      case "Red Right":
        return new Pose3d(new Translation3d(10.5,6,0), new Rotation3d(0,0,Math.toRadians(180)));
      default:
        return new Pose3d(); 
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
