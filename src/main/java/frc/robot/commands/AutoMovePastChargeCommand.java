package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import static frc.robot.Constants.AutoConstants.ANGLESTOP;


public class AutoMovePastChargeCommand extends CommandBase {
    private final IMUSubsystem iMUSubsystem;
    private final TankDriveSubsystem tankDriveSubsystem;
    private double initialAngle;
    private int count;

    public AutoMovePastChargeCommand(IMUSubsystem iMUSubsystem, TankDriveSubsystem tankDriveSubsystem, double startpoint) {
        this.iMUSubsystem = iMUSubsystem;
        this.tankDriveSubsystem = tankDriveSubsystem;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(this.iMUSubsystem, this.tankDriveSubsystem);
        initialAngle = startpoint;
        count = 0;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        tankDriveSubsystem.setSpeed(0.3, 0.3);
        if (iMUSubsystem.getRoll() < initialAngle + 2 && iMUSubsystem.getRoll() > initialAngle - 2) {
            count++;
        } else {
            count = 0;
        };
        SmartDashboard.putNumber("count", count);
    }

    @Override
    public boolean isFinished() {
        return count > 15;
    }

    @Override
    public void end(boolean interrupted) {
        tankDriveSubsystem.setSpeed(0.0, 0.0);
        SmartDashboard.putBoolean("AutoMoveToCharge", false);
    }
}