

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

/**
 * This test class focuses on the static `SwerveModuleState.optimize` method,
 * which contains the most critical logic for ensuring the module turns efficiently.
 * By testing this, we can have high confidence in our module's behavior without
 * needing to mock complex hardware.
 *
 * To run these tests, ensure you have JUnit in your build.gradle:
 * `testImplementation 'org.junit.jupiter:junit-jupiter:5.10.1'`
 */
public class SwerveModuleTest {

    private static final double DELTA = 1e-3; // A small tolerance for floating point comparisons

    @Test
    void testOptimizeState_ShortestPathForward() {
        // Current angle is 10 degrees
        Rotation2d currentAngle = Rotation2d.fromDegrees(10);
        // Desired state is 20 degrees at 1 m/s (a simple 10-degree turn)
        SwerveModuleState desiredState = new SwerveModuleState(1.0, Rotation2d.fromDegrees(20));
        // Optimize the state
        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, currentAngle);

        // The angle should remain 20 degrees (shortest path)
        assertEquals(20.0, optimizedState.angle.getDegrees(), DELTA);
        // The speed should remain positive
        assertEquals(1.0, optimizedState.speedMetersPerSecond, DELTA);
    }

    @Test
    void testOptimizeState_ShortestPathReversed() {
        // Current angle is 10 degrees
        Rotation2d currentAngle = Rotation2d.fromDegrees(10);
        // Desired state is 170 degrees (a 160-degree turn)
        SwerveModuleState desiredState = new SwerveModuleState(1.0, Rotation2d.fromDegrees(170));
        // Optimize desired state
        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, currentAngle);

        // The angle should be -10 degrees (a 20-degree turn in the other direction)
        assertEquals(-10.0, optimizedState.angle.getDegrees(), DELTA);
        // The speed should be reversed because we are pointing the other way
        assertEquals(-1.0, optimizedState.speedMetersPerSecond, DELTA);
    }

    @Test
    void testOptimizeState_HandleWrapAround() {
        // Current angle is 350 degrees (-10)
        Rotation2d currentAngle = Rotation2d.fromDegrees(350);
        // Desired state is 10 degrees (a 20-degree turn across the 0/360 boundary)
        SwerveModuleState desiredState = new SwerveModuleState(1.0, Rotation2d.fromDegrees(10));
        // Optimize the state
        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, currentAngle);

        // The angle should be 10 degrees
        assertEquals(10.0, optimizedState.angle.getDegrees(), DELTA);
        // The speed should remain positive
        assertEquals(1.0, optimizedState.speedMetersPerSecond, DELTA);
    }
}
