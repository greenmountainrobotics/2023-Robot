package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveSubsystem;

import java.time.Clock;


public class WeekZeroAutoCommand extends Command {
    private final TankDriveSubsystem sixWheelDriveSubsystem;

    private long startTime = 0;

    public WeekZeroAutoCommand(TankDriveSubsystem sixWheelDriveSubsystem) {
        this.sixWheelDriveSubsystem = sixWheelDriveSubsystem;
        addRequirements(this.sixWheelDriveSubsystem);
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        sixWheelDriveSubsystem.setSpeed(-0.5, -0.5);
        startTime = Clock.systemUTC().instant().toEpochMilli();
    }

    @Override
    public void execute() {
        sixWheelDriveSubsystem.setSpeed(-0.5, -0.5);
    }

    @Override
    public boolean isFinished() {
        return Clock.systemUTC().instant().toEpochMilli() - 2000 > startTime;
    }

    @Override
    public void end(boolean interrupted) {
        sixWheelDriveSubsystem.setSpeed(0, 0);
    }
}
