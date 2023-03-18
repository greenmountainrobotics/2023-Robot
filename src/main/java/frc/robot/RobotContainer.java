package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.BalanceCommand;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

import static frc.robot.Constants.OIConstants.*;

public class RobotContainer {
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private final TankDriveSubsystem robotDrive = new TankDriveSubsystem();
  private final IntakeSubsystem intaksubsystem = new IntakeSubsystem();
  private final CommandJoystick joystick = new CommandJoystick(DRIVER_CONTROLLER_PORT);
  private final CommandXboxController controller = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
  private final IMUSubsystem imu = new IMUSubsystem();

  public RobotContainer() {
    robotDrive.setDefaultCommand(new ParallelCommandGroup(new JoystickDriveCommand(robotDrive, joystick), new IntakeCommand(intaksubsystem, controller)));
  }

  public Command getAutonomousCommand() {
    // return new WeekZeroAutoCommand(robotDrive);
    return new BalanceCommand(imu, robotDrive);
  }
}
