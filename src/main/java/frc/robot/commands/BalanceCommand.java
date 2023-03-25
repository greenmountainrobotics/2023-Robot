// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.AutoConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.IMUSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BalanceCommand extends CommandBase {

    PIDController pid;
    IMUSubsystem imu;
    TankDriveSubsystem motor;
    double setpoint;

    public BalanceCommand(IMUSubsystem imusb, TankDriveSubsystem tank) {
        pid = new PIDController(kP, kI, kD);
        imu = imusb;
        motor = tank;
        addRequirements(imusb, tank);
    }

    @Override
    public void initialize() {
        setpoint = imu.getYaw();
        pid.setPID(SmartDashboard.getNumber("Kp", kP), kI, SmartDashboard.getNumber("Kd", kD));
    }


    @Override
    public void execute() {
        double num = MathUtil.clamp(pid.calculate(imu.getYaw(), setpoint), -0.5, 0.5);
        motor.setSpeed(num, num);
        SmartDashboard.putNumber("PID Balance Move val:", num);
    }
}