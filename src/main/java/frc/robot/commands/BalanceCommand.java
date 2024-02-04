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
import edu.wpi.first.wpilibj2.command.Command;

public class BalanceCommand extends Command {

    PIDController pid;
    IMUSubsystem imu;
    TankDriveSubsystem motor;
    double setpoint;

    public BalanceCommand(IMUSubsystem imusb, TankDriveSubsystem tank, double startpoint) {
        pid = new PIDController(kP, kI, kD);
        imu = imusb;
        motor = tank;
        addRequirements(imusb, tank);
        setpoint = 0.0; //TODO: AAAAAAAAAAAAAAAAAA
    }

    @Override
    public void initialize() {
        pid.reset();
        pid.setSetpoint(setpoint);
        pid.setPID(SmartDashboard.getNumber("Kp", kP), SmartDashboard.getNumber("kI", kI), SmartDashboard.getNumber("Kd", kD));
        pid.enableContinuousInput(-45.0, 45.0);
    }


    @Override
    public void execute() {
        double num = MathUtil.clamp(pid.calculate(imu.getRoll()), -0.35, 0.35);
        motor.setSpeed(num, num);
        SmartDashboard.putNumber("PID Balance Move val:", num);
    }

    @Override
    public void end(boolean interrupted) {
        motor.setSpeed(0.0, 0.0);
    }
}
