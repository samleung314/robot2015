package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team334.robot.Robot;

public class Smartdashboard {

	Robot robot;
    
    Preferences pref = Preferences.getInstance();
    
    public double autoDist, autoSpeed, autoDegrees, autoHeight;
    
    public Smartdashboard(Robot robot) {
        this.robot = robot;
    }

    // Places PID objects onto SmartDashboard
    public void displayPIDs() {
        //SmartDashboard.putData("Ramping PID", robot.ramp.rampPID);
        //SmartDashboard.putData("Straight PID", robot.straight.straightPID);
        //SmartDashboard.putData("Turn PID", robot.turn.turnPID);      
        //SmartDashboard.putData("Elevate PID", robot.elevate.elevatorPID);
    }

    // Places sensor values onto SmartDashboard
    public void displaySensors() {
    	SmartDashboard.putNumber("Average Encoder Distance", robot.encode.averageDist());
        SmartDashboard.putNumber("Left Encoder Distance", robot.encode.leftEnc.getDistance());
        SmartDashboard.putNumber("Right Encoder Distance", robot.encode.rightEnc.getDistance());

        SmartDashboard.putNumber("Raw elevator level", robot.pot.elevatorPot.get());
        SmartDashboard.putNumber("Scaled Elevator level", robot.pot.getLevel());

        SmartDashboard.putNumber("Gyroscope", robot.turn.gyro.getAngle());
        
        SmartDashboard.putBoolean("At full pressure?", robot.air.compress.getPressureSwitchValue());
        SmartDashboard.putBoolean("Elevator brake engaged?", robot.elevate.locked);
    }
    
    public void displayAuton() {
    	SmartDashboard.putString("Robot State", robot.auto.currentState);
    }
    
    public void getPrefs() {
    	autoDist = pref.getDouble("Distance", 0);
    	autoSpeed = pref.getDouble("Speed", 0);
    	autoDegrees = pref.getDouble("Degrees", 0);
    	autoHeight = pref.getDouble("Height", 30);
    	pref.save();
    }
}
