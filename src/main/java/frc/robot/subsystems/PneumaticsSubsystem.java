package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class PneumaticsSubsystem extends SubsystemBase {

    public static DoubleSolenoid.Value intakeUpState = DoubleSolenoid.Value.kForward;
    public static DoubleSolenoid.Value intakeDownState = DoubleSolenoid.Value.kReverse;


    private DoubleSolenoid doubleSolenoid;

    private DoubleSolenoid.Value solenoidState;

    private Compressor phCompressor;

    private boolean compressorEnabled;
    private boolean pressureSwitch;
    private double compressorCurrent;

    public PneumaticsSubsystem() {
        doubleSolenoid = new DoubleSolenoid(PNEUMATICS_ID, PneumaticsModuleType.REVPH,
                PNEUMATICS_FORWARD_CHANNEL, PNEUMATICS_REVERSE_CHANNEL);

         phCompressor = new Compressor(PNEUMATICS_ID, PneumaticsModuleType.REVPH);

         solenoidState = intakeDownState;
    }

    public void setSolenoidState(DoubleSolenoid.Value solenoidState) {
        this.solenoidState = solenoidState;
    }

    public DoubleSolenoid.Value getSolenoidState() {
        return solenoidState;
    }

    public boolean isCompressorEnabled() {
        return compressorEnabled;
    }

    public boolean isPressureSwitch() {
        return pressureSwitch;
    }

    public double getCompressorCurrent() {
        return compressorCurrent;
    }

    @Override
    public void periodic() {
        doubleSolenoid.set(solenoidState);

        compressorEnabled = phCompressor.isEnabled();
        pressureSwitch = phCompressor.getPressureSwitchValue();
        compressorCurrent = phCompressor.getCurrent();

        SmartDashboard.putBoolean("intakeUp", intakeUpState == this.solenoidState);
    }
}

