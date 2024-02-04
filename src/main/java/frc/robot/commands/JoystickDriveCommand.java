// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveSubsystem;
import static frc.robot.Constants.DriveConstants.*;

public class JoystickDriveCommand extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final TankDriveSubsystem robotDrive;
    private final CommandJoystick joystick;

    public JoystickDriveCommand(TankDriveSubsystem robotDrive, CommandJoystick commandJoystick) {
        this.robotDrive = robotDrive;
        joystick = commandJoystick;
        addRequirements(robotDrive);
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("Max Speed (dashboard)", 1.0);
    }

    private double getMaxSpeed() {
        /*
         * return ((-joystick.getRawAxis(3) + 1) / 2)
         */ return MAX_SPEED
                * SmartDashboard.getNumber("Max Speed (dashboard)", 1.0);
    }

    private double powerCurve(double input) {
        return (input > 0 ? 1 : -1) *
                input * input
                * getMaxSpeed();
    }

    @Override
    public void execute() {
        var speed = DifferentialDrive.arcadeDriveIK(
                powerCurve(
                        Math.abs(joystick.getRawAxis(0)) > 0.1 ? joystick.getRawAxis(0) : 0),
                // powerCurve(
                // Math.abs(joystick.getRawAxis(2)) > 0.2 ? joystick.getRawAxis(2) : 0),
                powerCurve(
                        Math.abs(joystick.getRawAxis(1)) > 0.1 ? joystick.getRawAxis(1) : 0),
                false);

        boolean forward = true; // joystick.getRawAxis(3) < 0;

        if (forward) {
            robotDrive.setSpeed(
                    speed.right,
                    speed.left);
        } else {
            robotDrive.setSpeed(
                    -speed.left,
                    -speed.right);
        }

        SmartDashboard.putBoolean("Forward", forward);
        SmartDashboard.putNumber("Max Speed (slider)", (-joystick.getRawAxis(3) + 1) / 2);
        SmartDashboard.putNumber("X axis", Math.abs(joystick.getRawAxis(1)) > 0.1 ? joystick.getRawAxis(1) : 0);
        SmartDashboard.putNumber("Y axis", Math.abs(joystick.getRawAxis(0)) > 0.1 ? joystick.getRawAxis(0) : 0);
    }
}
