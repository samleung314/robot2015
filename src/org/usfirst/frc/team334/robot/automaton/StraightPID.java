package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;

public class StraightPID {

    Robot robot;

    public PIDController straightPID;
    double sP, sI, sD;

    public StraightPID(Robot robot) {
        this.robot = robot;

        straightPID = new PIDController(sP, sI, sD, robot.turn.gyro, robot.drive.leftVics);

        straightPID.setContinuous(); //Tells PID that 360 degrees is the same as 0 degrees
        straightPID.setOutputRange(-1, 1); //Limits the victors' speed from a range of -1 to 1
        straightPID.setSetpoint(0); //PID will cause robot to achieve an orientation of 0 degrees making it drive straight
    }

    public boolean travelDistance(double distance, double speed) {
        if (robot.encode.averageDist() < distance) { //If distance not reached, keep moving
            straightPID.enable();
            robot.drive.doubleVicsDrive(speed, speed);
            return false;

        } else if (robot.encode.averageDist() >= distance) { //Stop and disable when distance reached
            robot.drive.doubleVicsDrive(0, 0);
            straightPID.disable();
            robot.encode.resetEncoders();
            return true;
            
        } else return true;
    }
}
