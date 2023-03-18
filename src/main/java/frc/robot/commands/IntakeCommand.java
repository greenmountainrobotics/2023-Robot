
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.DriveConstants.*;

public class IntakeCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final IntakeSubsystem intake;
    private final CommandXboxController controller;

    public IntakeCommand(IntakeSubsystem intake, CommandXboxController controller) {
        this.intake = intake;
        this.controller = controller;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("Max Speed Intake (dashboard)", 1.0);
    }


    private double getMaxSpeed() {
        /*
         * return ((-joystick.getRawAxis(3) + 1) / 2)
         */ return MAX_SPEED_INTAKE
                * SmartDashboard.getNumber("Max Speed Intake (dashboard)", 1.0);
    }

    private double powerCurve(double input) {
        return (input > 0 ? 1 : -1) *
                input * input
                * getMaxSpeed();
    }

    @Override
    public void execute() {

        var speed = powerCurve(Math.abs(controller.getLeftY()) > 0.1 ? controller.getLeftY() : 0);
        intake.setSpeed(speed);

        SmartDashboard.putNumber("Y axis", Math.abs(controller.getLeftY()) > 0.1 ? controller.getLeftY() : 0);
    }
}
