package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;

public class DistancePID implements PIDOutput {

    Robot robot;

    public PIDController rampPID;
    double rampKp, rampKi, rampKd, rampSpeed;

    Timer rampUpTime, rampDownTime;

    public DistancePID(Robot robot) {
        this.robot = robot;

        rampUpTime = new Timer();
        rampDownTime = new Timer();

        rampPID = new PIDController(rampKp, rampKi, rampKd, robot.encode, this);
        rampPID.setOutputRange(-0.5, 0.5); //Limits the speed to 60% on the victors
        rampPID.setAbsoluteTolerance(Constants.distancePIDTolerance);
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
            robot.drive.doubleVicsDrive(rampedSpeed, rampedSpeed);
        } else if (currTime >= seconds) {
            robot.drive.doubleVicsDrive(speed, speed);
        }
    }

    //Ramps robot down to specified speed within specified seconds
    public void manualRampDown(double seconds, double currentSpeed) {

        double currTime = rampDownTime.get();
        double rampedSpeed = currentSpeed - (currTime * (currentSpeed / seconds));

        if (currTime < seconds) {
            robot.drive.doubleVicsDrive(rampedSpeed, rampedSpeed);
        } else if (currTime >= seconds) {
            robot.drive.doubleVicsDrive(0, 0);
        }
    }
}
