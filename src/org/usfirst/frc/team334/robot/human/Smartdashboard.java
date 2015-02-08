package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team334.robot.Robot;

public class Smartdashboard {

    Robot robot;
    
    Preferences pref = Preferences.getInstance();
    
    public Smartdashboard(Robot robot) {
        this.robot = robot;
    }

    // Places PID objects onto SmartDashboard
    public void displayPIDs() {
        //SmartDashboard.putData("Ramping PID", robot.ramp.rampPID);
        SmartDashboard.putData("Straight PID", robot.straight.straightPID);
        //SmartDashboard.putData("Turn PID", robot.turn.turnPID);      
        //SmartDashboard.putData("Elevate PID", robot.elevate.elevatorPID);
    }

    // Places sensor values onto SmartDashboard
    public void displaySensors() {
        SmartDashboard.putNumber("Left Encoder Distance", robot.encode.leftEnc.getDistance());
        SmartDashboard.putNumber("Right Encoder Distance", robot.encode.rightEnc.getDistance());
        SmartDashboard.putNumber("Average Encoder Distance", robot.encode.averageDist());

        SmartDashboard.putNumber("Raw elevator level", robot.pot.elevatorPot.get());
        SmartDashboard.putNumber("Elevator level", robot.pot.getLevel());

        SmartDashboard.putNumber("Gyroscope", robot.auto.gyro.getAngle());
        
        SmartDashboard.putBoolean("At full pressure?", robot.air.compress.getPressureSwitchValue());
        SmartDashboard.putBoolean("Elevator brake engaged?", robot.elevate.locked);
    }
    
    public void getPrefs() {
    	//pref.getDouble(key, backup);
    }
}
