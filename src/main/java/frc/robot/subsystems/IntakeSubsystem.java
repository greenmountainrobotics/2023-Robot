package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class IntakeSubsystem extends SubsystemBase {

    private final VictorSPX rightMotor = new VictorSPX(RIGHT_INTAKE_ID);
    private final VictorSPX leftMotor = new VictorSPX(LEFT_INTAKE_ID);

    private double speed = 0.0;

    public IntakeSubsystem() {
        // TODO: check if we need to switch this
        rightMotor.setInverted(true);
    }

    /**
     * set speed of intake motors
     *
     * @param speed
     * value between -1.0 (in) and 1.0 (out)
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void periodic() {
        rightMotor.set(VictorSPXControlMode.PercentOutput, speed);
        leftMotor.set(VictorSPXControlMode.PercentOutput, speed);

        SmartDashboard.putNumber("Intake speed", speed);
        speed = SmartDashboard.getNumber("Intake speed", 0.0);
    }
}

