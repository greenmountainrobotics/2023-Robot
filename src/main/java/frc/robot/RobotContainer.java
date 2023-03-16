package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.commands.BalanceCommand;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import frc.robot.subsystems.WeekZeroGrabberSubsystem;

import static frc.robot.Constants.OIConstants.*;

public class RobotContainer {
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private final TankDriveSubsystem robotDrive = new TankDriveSubsystem();
  private final WeekZeroGrabberSubsystem weekZeroGrabber = new WeekZeroGrabberSubsystem();
  private final CommandJoystick joystick = new CommandJoystick(DRIVER_CONTROLLER_PORT);
  private final CommandXboxController controller = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
  private final IMUSubsystem imu = new IMUSubsystem();

  public RobotContainer() {
    robotDrive.setDefaultCommand(new JoystickDriveCommand(robotDrive, joystick));

    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    controller.x().onTrue(weekZeroGrabber.startInCommand());
    controller.y().onTrue(weekZeroGrabber.startOutCommand());
    controller.a().onTrue(weekZeroGrabber.stopCommand());
  }
  public Command getAutonomousCommand() {
    // return new WeekZeroAutoCommand(robotDrive);
    return new BalanceCommand(imu, robotDrive);
  }
}
