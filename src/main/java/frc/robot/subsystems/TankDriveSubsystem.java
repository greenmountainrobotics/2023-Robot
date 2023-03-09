// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class TankDriveSubsystem extends SubsystemBase {
    private final MotorControllerGroup leftMotors =
            new MotorControllerGroup(
                    new CANSparkMax(LEFT_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushed),
                    new CANSparkMax(LEFT_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushed)
            );

    private final MotorControllerGroup rightMotors =
            new MotorControllerGroup(
                    new CANSparkMax(RIGHT_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushed),
                    new CANSparkMax(RIGHT_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushed));

    private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);


    public TankDriveSubsystem() {
        rightMotors.setInverted(true);
    }

    public void setSpeed(double right, double left) {
        rightMotors.set(right);
        leftMotors.set(left);
        drive.feed();
    }

    public void periodic() {
        SmartDashboard.putNumber("Right Motors", rightMotors.get());
        SmartDashboard.putNumber("Left Motors", leftMotors.get());
    }
}