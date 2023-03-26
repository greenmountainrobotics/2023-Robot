package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

import static frc.robot.Constants.AutoConstants.*;
import static frc.robot.Constants.OIConstants.*;

public class RobotContainer {
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private final TankDriveSubsystem robotDrive = new TankDriveSubsystem();
  private final IntakeSubsystem intaksubsystem = new IntakeSubsystem();
  private final CommandJoystick joystick = new CommandJoystick(DRIVER_CONTROLLER_PORT);
  private final CommandXboxController controller = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
  private final IMUSubsystem imu = new IMUSubsystem();

  private double roll;

  public RobotContainer() {
      SmartDashboard.putNumber("Kp", kP);
      SmartDashboard.putNumber("Kd", kD);
      SmartDashboard.putNumber("kI", kI);
      SmartDashboard.putNumber("AutoSpeedBalance", AUTOROBOTSPEED);
      SmartDashboard.putBoolean("Auto", true);
      roll = imu.getRoll();

    robotDrive.setDefaultCommand(new ParallelCommandGroup(new JoystickDriveCommand(robotDrive, joystick), new IntakeCommand(intaksubsystem, controller)));
/*            new RunCommand(() -> {
              if (joystick.button(1).getAsBoolean()) {
                intaksubsystem.setSpeed(-0.5);
              } else if (joystick.button(2).getAsBoolean()) {
                intaksubsystem.setSpeed(0.3);
              } else {
                intaksubsystem.setSpeed(0.0);
              }
            }, intaksubsystem))*/

  }

  public Command getAutonomousCommand() {
    // return new WeekZeroAutoCommand(robotDrive);
      if (SmartDashboard.getBoolean("Auto", true))
          return new AutoCommandGroup(imu, robotDrive, intaksubsystem, imu.getRoll());
      else
      //    return new AutoScoreCommand(intaksubsystem, robotDrive);
      //return new NewBalanceCommand(imu, robotDrive, roll);
      return new SequentialCommandGroup(new AutoScoreCommand(intaksubsystem, robotDrive), new RunCommand(() -> robotDrive.setSpeed(0.3, 0.3), robotDrive).withTimeout(4));
  }
}
