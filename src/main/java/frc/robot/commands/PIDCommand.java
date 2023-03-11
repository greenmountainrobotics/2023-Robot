// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.AutoConstants.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PIDCommand extends CommandBase {

    PIDController pid;

    public PIDCommand() {
        pid = new PIDController(kP, kI, kD);
    }

    @Override
    public void initialize() {
    }


    @Override
    public void execute() {

    }
}