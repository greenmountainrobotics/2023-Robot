
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.AutoConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class NewBalanceCommand extends CommandBase {

    IMUSubsystem imu;
    TankDriveSubsystem motor;
    double setpoint;

    public NewBalanceCommand(IMUSubsystem imusb, TankDriveSubsystem tank, double startpoint) {
        imu = imusb;
        motor = tank;
        setpoint = startpoint;
        addRequirements(imusb, tank);
    }

    @Override
    public void initialize() {
    }


    @Override
    public void execute() {
        motor.setSpeed(SmartDashboard.getNumber("AutoSpeedBalance", AUTOROBOTSPEED), SmartDashboard.getNumber("AutoSpeedBalance", AUTOROBOTSPEED));
    }

    @Override
    public boolean isFinished() {
        return setpoint == imu.getYaw();
    }

    @Override
    public void end(boolean interrupted) {
        motor.setSpeed(0.0, 0.0);
    }
}
