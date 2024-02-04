package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import static frc.robot.Constants.AutoConstants.ANGLESTOP;


public class AutoMoveToChargeCommand extends Command {
    private final IMUSubsystem iMUSubsystem;
    private final TankDriveSubsystem tankDriveSubsystem;
    private double initialAngle;

    public AutoMoveToChargeCommand(IMUSubsystem iMUSubsystem, TankDriveSubsystem tankDriveSubsystem) {
        this.iMUSubsystem = iMUSubsystem;
        this.tankDriveSubsystem = tankDriveSubsystem;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(this.iMUSubsystem, this.tankDriveSubsystem);
    }

    @Override
    public void initialize() {
        initialAngle = iMUSubsystem.getRoll();
        SmartDashboard.putBoolean("AutoMoveToCharge", true);
    }

    @Override
    public void execute() {
        tankDriveSubsystem.setSpeed(0.5, 0.5);
    }

    @Override
    public boolean isFinished() {
        return iMUSubsystem.getRoll() < initialAngle - ANGLESTOP;
    }

    @Override
    public void end(boolean interrupted) {
        tankDriveSubsystem.setSpeed(0.0, 0.0);
        SmartDashboard.putBoolean("AutoMoveToCharge", false);
    }
}