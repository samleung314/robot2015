package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;

public class DistancePID implements PIDOutput {

    Robot robot;

    public PIDController distancePID;
    double rampKp = 0.018, rampKi = 0, rampKd = 0.028, rampSpeed;

    Timer rampUpTime, rampDownTime;

    public DistancePID(Robot robot) {
        this.robot = robot;

        rampUpTime = new Timer();
        rampDownTime = new Timer();

        distancePID = new PIDController(rampKp, rampKi, rampKd, robot.encode, this);
        distancePID.setOutputRange(-1, 1); //Limits the speed to 60% on the victors
        distancePID.setAbsoluteTolerance(Constants.distancePIDTolerance);
    }

    @Override
    public void pidWrite(double output) {
        this.rampSpeed = output;
    }

    //Ramps robot up to specified speed within specified seconds
    public void manualRampUp(double seconds, double speed) {

        double currTime = rampUpTime.get();
        double rampedSpeed = currTime * (speed / seconds);

        if (currTime < seconds) {
            robot.drive.victorDrive(rampedSpeed, rampedSpeed);
        } else if (currTime >= seconds) {
            robot.drive.victorDrive(speed, speed);
        }
    }

    //Ramps robot down to specified speed within specified seconds
    public void manualRampDown(double seconds, double currentSpeed) {

        double currTime = rampDownTime.get();
        double rampedSpeed = currentSpeed - (currTime * (currentSpeed / seconds));

        if (currTime < seconds) {
            robot.drive.victorDrive(rampedSpeed, rampedSpeed);
        } else if (currTime >= seconds) {
            robot.drive.victorDrive(0, 0);
        }
    }
}
