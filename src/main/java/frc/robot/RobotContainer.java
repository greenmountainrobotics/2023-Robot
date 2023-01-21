package frc.robot;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.subsystems.SixWheelDriveSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import static frc.robot.Constants.OIConstants.*;

public class RobotContainer {
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private final TankDriveSubsystem m_robotDrive = new SixWheelDriveSubsystem();
  private final CommandJoystick m_joystick = new CommandJoystick(kDriverControllerPort);

  public RobotContainer() {
    m_robotDrive.setDefaultCommand(new JoystickDriveCommand(m_robotDrive, m_joystick));

    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  }
}
