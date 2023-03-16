package frc.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IMUSubsystem extends SubsystemBase {
    private final AHRS ahrs = new AHRS(SPI.Port.kMXP);
    private long prevTime = 0;

    public IMUSubsystem() {
        prevTime = 0;

        ahrs.resetDisplacement();

        this.setDefaultCommand(run(() -> {

            SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
            SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
            SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
            SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
            SmartDashboard.putNumber(   "IMU_Roll", ahrs.getRoll());
            SmartDashboard.putNumber(   "Time Delta", System.currentTimeMillis() -prevTime);
            SmartDashboard.putNumber("Velocity", ahrs.getVelocityX());
            SmartDashboard.putNumber("Acceleration", ahrs.getWorldLinearAccelX());
            SmartDashboard.putNumber("Displacement", ahrs.getDisplacementX());

            prevTime = System.currentTimeMillis();
        }));
    }

    public double getYaw() {
        return ahrs.getYaw();
    }

    public double getRoll() {
        return ahrs.getRoll();
    }

    public double getPitch() {
        return ahrs.getPitch();
    }
}

