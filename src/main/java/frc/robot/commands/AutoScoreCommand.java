package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import java.time.Clock;
import static frc.robot.Constants.AutoConstants.*;

public class AutoScoreCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final TankDriveSubsystem tankDriveSubsystem;
    private long startTime;

    public AutoScoreCommand(IntakeSubsystem intakeSubsystem, TankDriveSubsystem tankDriveSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.tankDriveSubsystem = tankDriveSubsystem;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(this.intakeSubsystem, this.tankDriveSubsystem);
    }

    @Override
    public void initialize() {
        startTime = Clock.systemUTC().instant().toEpochMilli();
    }

    @Override
    public void execute() {
            intakeSubsystem.setSpeed(-0.4);
    }

    @Override
    public boolean isFinished() {
        return Clock.systemUTC().instant().toEpochMilli() - (SHOOTING_TIME) > startTime;
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setSpeed(0.0);
    }
}
