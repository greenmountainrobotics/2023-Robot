// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.AutoConstants.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class PneumaticsDriverControlCommand extends Command {

    private PneumaticsSubsystem pneumaticsSubsystem;
    private CommandXboxController controller;

    public PneumaticsDriverControlCommand(PneumaticsSubsystem pneumaticsSubsystem, CommandXboxController controller) {
        this.pneumaticsSubsystem = pneumaticsSubsystem;
        this.controller = controller;
        addRequirements(pneumaticsSubsystem);
    }

    @Override
    public void initialize() {
    }


    @Override
    public void execute() {
        if (controller.a().getAsBoolean()) {
            this.pneumaticsSubsystem.setSolenoidState(DoubleSolenoid.Value.kForward);
        } else if (controller.b().getAsBoolean()) {
            this.pneumaticsSubsystem.setSolenoidState(DoubleSolenoid.Value.kReverse);
        } else {
            pneumaticsSubsystem.setSolenoidState(DoubleSolenoid.Value.kOff);
        }
    }

    @Override
    public void end(boolean interrupted) {
        pneumaticsSubsystem.setSolenoidState(DoubleSolenoid.Value.kOff);
    }
}
