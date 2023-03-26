package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

public class AutoCommandGroup extends SequentialCommandGroup {
    public AutoCommandGroup(IMUSubsystem imu, TankDriveSubsystem tank, IntakeSubsystem intake, double startpoint) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new OpenClawCommand(), new MoveArmCommand());
        super(new AutoScoreCommand(intake, tank), new AutoMoveToChargeCommand(imu, tank), new BalanceCommand(imu, tank, startpoint));
    }
}
