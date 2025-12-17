package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ElevatorSubsystem;

public class transitposition {

    public void goToTransitPostion() {
        ElevatorMotor.setPosition(0.0);
        getAbsoluteEncoder();
    }

}