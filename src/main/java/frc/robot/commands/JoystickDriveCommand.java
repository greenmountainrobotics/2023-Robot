// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TankDriveSubsystem;
import static frc.robot.Constants.DriveConstants.*;

import static frc.robot.Constants.OIConstants.kDriverControllerPort;

/** An example command that uses an example subsystem. */
public class JoystickDriveCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final TankDriveSubsystem m_robotDrive;
    private final CommandJoystick m_joystick;

    public JoystickDriveCommand(TankDriveSubsystem subsystem, CommandJoystick commandJoystick) {
        m_robotDrive = subsystem;
        m_joystick = commandJoystick;
        addRequirements(subsystem);
    }

    private double powerCurve(double input) {
        return (input > 0 ? 1 : -1) *
                input * input
               // * (m_joystick.getRawAxis(3) < 0 ? 1 : 0.5)
                * ((-m_joystick.getRawAxis(3) + 1) / 2)
                * kMaxSpeed;
    }

    @Override
    public void execute() {
        var speed = DifferentialDrive.arcadeDriveIK(
                powerCurve(
                        Math.abs(m_joystick.getRawAxis(1)) > 0.1 ? m_joystick.getRawAxis(1) : 0),
                //powerCurve(
                 //       Math.abs(m_joystick.getRawAxis(2)) > 0.2 ? m_joystick.getRawAxis(2) : 0),
                powerCurve(
                        Math.abs(m_joystick.getRawAxis(0)) > 0.1 ? m_joystick.getRawAxis(0) : 0),
                false);
        m_robotDrive.setSpeed(
                speed.right,
                speed.left
        );
    }
}
