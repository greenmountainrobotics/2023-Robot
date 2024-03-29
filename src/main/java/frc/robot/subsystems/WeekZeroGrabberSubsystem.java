package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class WeekZeroGrabberSubsystem extends SubsystemBase {
    private final Servo leftServo = new Servo(LEFT_SERVO_PORT);
    private final Servo rightServo = new Servo(RIGHT_SERVO_PORT);


    public WeekZeroGrabberSubsystem() {

    }

    public void setLeftServo(double amt) {
        leftServo.set(amt);
    }

    public void setRightServo(double amt) {
        rightServo.set(amt);
    }

    public Command stopCommand() {
        return this.runOnce(() -> {
            setLeftServo(0.5);
            setRightServo(0.5);
        });
    }

    public Command startInCommand() {
        return runOnce(() -> {
            setRightServo(0);
            setLeftServo(1);
        });
    }

    public Command startOutCommand() {
        return runOnce(() -> {
            setRightServo(1);
            setLeftServo(0);
        });
    }
}

