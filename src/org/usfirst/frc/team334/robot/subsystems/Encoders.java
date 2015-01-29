package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Robot;
import org.usfirst.frc.team334.robot.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

public class Encoders implements PIDSource{
    
    Robot robot; 
    
    public Encoder leftEnc, rightEnc;
    
    public Encoders(Robot robot) {
        this.robot = robot;
        
        leftEnc = new Encoder(Constants.leftEncoderX, Constants.leftEncoderY);
        rightEnc = new Encoder(Constants.rightEncoderX, Constants.rightEncoderY);
        
        leftEnc.setDistancePerPulse(Constants.encoderDistancePerPulse);
        rightEnc.setDistancePerPulse(Constants.encoderDistancePerPulse);
    }

    
    @Override //Returns average distance for PID input
    public double pidGet() {
        return averageDist();
    }

    //Average distance traveled by both encoders
    public double averageDist() {
        return ((leftEnc.getDistance() + rightEnc.getDistance()) / 2);
    }
    
    public void resetEncoders() {
        leftEnc.reset();
        rightEnc.reset();
    }
}
