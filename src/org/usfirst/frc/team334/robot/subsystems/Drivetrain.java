package org.usfirst.frc.team334.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team334.robot.Robot;

public class Drivetrain implements PIDOutput {

    Robot robot;

    public PIDController straightPID;
    public PIDController turnPID;
    public PIDController distancePID;
    
    public RobotDrive chasisDrive;

    public double sP, sI, sD, tP, tI, tD, turnSpeed;

    public Drivetrain(Robot robot) {
        this.robot = robot;
        
        chasisDrive = new RobotDrive(robot.mech.leftVics, robot.mech.rightVics);

        straightPID = new PIDController(sP, sI, sD, robot.sensors.gyro, robot.mech.leftVics);
        turnPID = new PIDController(tP, tI, tD, robot.sensors.gyro, this);
    }
    
    public void manualVicsDrive(double leftSpeed, double rightSpeed) {
        robot.mech.leftVicA.set(leftSpeed);
        robot.mech.leftVicB.set(leftSpeed);

        robot.mech.rightVicA.set(-rightSpeed);
        robot.mech.rightVicB.set(-rightSpeed);
    }
    
    public void settingsStraightPID() {
        straightPID.setContinuous();
        straightPID.setOutputRange(-1, 1);
        straightPID.setSetpoint(0);
    }
    
    public void settingsTurnPID() {
        turnPID.setContinuous();
        turnPID.setOutputRange(-1, 1);
    }

    public void pidWrite(double output) {
        this.turnSpeed = output;
    }

}
