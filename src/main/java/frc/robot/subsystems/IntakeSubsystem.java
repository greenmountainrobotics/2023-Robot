package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    public IntakeSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    }

    /**
     * set speed of intake motors
     *
     * @param speed
     * value between -1.0 (in) and 1.0 (out)
     */
    public void setMotors(double speed) {
        // TODO

    }

    public double getSpeed() {
        // TODO
        return 0.0;
    }
}

