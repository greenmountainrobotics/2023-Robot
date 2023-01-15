package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TankDriveSubsystem extends SubsystemBase {
    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param right right power: [0, 1]
     * @param left  left power: [0, 1]
     */
    public abstract void setSpeed(double right, double left);
}
