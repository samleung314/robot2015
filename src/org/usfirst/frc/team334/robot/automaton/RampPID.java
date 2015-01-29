package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;

public class RampPID implements PIDOutput{

    Robot robot;
    
    public PIDController rampPID;
    double dP, dI, dD, rampSpeed;
    
    Timer rampUpTime, rampDownTime;
    
    public RampPID(Robot robot) {
        this.robot = robot;
        
        rampUpTime = new Timer();
        rampDownTime = new Timer();
        
        rampPID = new PIDController(dP, dI, dD, robot.encode, this);
    }
    
    @Override
    public void pidWrite(double output) {
        this.rampSpeed = output;
        
    }
    
    //Ramps robot up to specified speed within specified seconds
    public void manualRampUp(double seconds, double speed) {
        
        double currTime = rampUpTime.get();
        double rampedSpeed = currTime * (speed/seconds);
        
        if (currTime < seconds)
        {
            robot.drive.doubleVicsDrive(rampedSpeed, rampedSpeed);
        }
        else if (currTime >= seconds)
        {
            robot.drive.doubleVicsDrive(speed, speed);
        }
    }
    
    //Ramps robot down to specified speed within specified seconds
    public void manualRampDown(double seconds, double currentSpeed) {
        
        double currTime = rampDownTime.get();
        double rampedSpeed = currentSpeed - (currTime * (currentSpeed/seconds) );
        
        if (currTime < seconds)
        {
            robot.drive.doubleVicsDrive(rampedSpeed, rampedSpeed);
        }
        else if (currTime >= seconds)
        {
            robot.drive.doubleVicsDrive(0, 0);
        }
    }
}
