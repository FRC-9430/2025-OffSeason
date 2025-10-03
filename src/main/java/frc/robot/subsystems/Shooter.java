package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Constants.ShooterConstants;;

public class Shooter extends SubsystemBase {
    // motor variables
    private SparkMax intakeMotor;
    private SparkMax pivotMotor;
    private SparkAbsoluteEncoder pivotEncoder;
    private PIDController pivotController;

    // motor
    private double intakeSpeed;
    private double pivotSpeed;
    private boolean isIntakeMotorOn;
    private boolean isPivotMotorOn;

    private enum ShooterMotorType {
        Intake,
        Pivot
    }

    public Shooter() {
        // Build new motors
        intakeMotor = new SparkMax(ShooterConstants.kShooterIntakeMotorCanId, MotorType.kBrushless);
        pivotMotor = new SparkMax(ShooterConstants.kShooterPivotMotorCanId, MotorType.kBrushless);

        // Get motor info
        pivotEncoder = pivotMotor.getAbsoluteEncoder();
        pivotSpeed = pivotEncoder.getVelocity();

        // Ensure motors are stopped when a shooter is created.
        // (these functions set the motor state bools to false)
        stopShooterMotor(intakeMotor);
        stopShooterMotor(pivotMotor);
    }

    public ShooterMotorType getMotorType(SparkMax targetMotor) {
        if (targetMotor.getDeviceId() == ShooterConstants.kShooterPivotMotorCanId) {
            return ShooterMotorType.Pivot;
        }

        else if (targetMotor.getDeviceId() == ShooterConstants.kShooterIntakeMotorCanId) {
            return ShooterMotorType.Intake;
        }

        else {
            throw new IllegalArgumentException("targetMotor must be a Shooter motor");
        }
    }

    public boolean isSpeedValid(double speed) {
        return speed > 1.0 || speed < -1.0;
    }

    public void stopShooterMotor(SparkMax targetMotor) {
        ShooterMotorType t = getMotorType(targetMotor);
        switch (t) {
            case Intake:
                isIntakeMotorOn = false;
                break;
            case Pivot:
                isPivotMotorOn = false;
                break;
        }
        targetMotor.stopMotor();
    }

    public void setShooterMotorSpeed(SparkMax targetMotor, double speed){
        // validate input speed
        if (isSpeedValid(speed) == false) {
            throw new IllegalArgumentException("Speed must be within range -1.0 to 1.0");
        }

        if (speed == 0) {
            stopShooterMotor(targetMotor);
        }

        // set the speed of the target motor
        ShooterMotorType t = getMotorType(targetMotor);
        switch (t) {
            case Intake:
                isIntakeMotorOn = true;
                targetMotor.set(speed);
                break;
            case Pivot:
                isPivotMotorOn = true;
                // robot safety check -- physical constraints
                boolean isPivotSafe = pivotEncoder.getPosition() < ShooterConstants.kShooterPivotMaxHeight
                    && pivotEncoder.getPosition() > ShooterConstants.kShooterPivotMinHeight ;
                while (isPivotSafe) {
                    // safe to move motor in these conditions
                    targetMotor.set(speed);
                }
                break;
        }
    }

}
