// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.DriveConstants.*;

public class SixWheelDriveSubsystem extends TankDriveSubsystem {
    private final MotorControllerGroup m_leftMotors =
            new MotorControllerGroup(
                    new PWMSparkMax(kLeftMotor1Port),
                    new PWMSparkMax(kLeftMotor2Port));

    private final MotorControllerGroup m_rightMotors =
            new MotorControllerGroup(
                    new PWMSparkMax(kRightMotor1Port),
                    new PWMSparkMax(kRightMotor2Port));

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);


    public SixWheelDriveSubsystem() {
        // TODO: check if this should be left or right motors
        m_rightMotors.setInverted(true);
    }

    @Override
    public void setSpeed(double right, double left) {
        m_rightMotors.set(right);
        m_leftMotors.set(left);
        m_drive.feed();
    }
}