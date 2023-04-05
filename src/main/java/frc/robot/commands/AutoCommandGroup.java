package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

public class AutoCommandGroup extends SequentialCommandGroup {

    public AutoCommandGroup(IMUSubsystem imu, TankDriveSubsystem tank, IntakeSubsystem intake, double startpoint) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new OpenClawCommand(), new MoveArmCommand());
        super(
                new InstantCommand(imu::reset, imu),
                new AutoScoreCommand(intake, tank),
                new AutoMoveToChargeCommand(imu, tank),
                new AutoMovePastChargeCommand(imu, tank, startpoint).withTimeout(4),
                new WaitCommand(0.5),
                new InstantCommand(imu::reset, imu),
                new AutoMoveBackToChargeCommand(imu, tank),
                new BalanceCommand(imu, tank, startpoint));
    }
}
