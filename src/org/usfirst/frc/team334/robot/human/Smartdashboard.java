package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.Robot;

public class Smartdashboard {

    Robot robot;
    
    public Smartdashboard(Robot robot) {
        this.robot = robot;
    }

    // Places PID objects onto SmartDashboard
    public void displayPIDs() {
        SmartDashboard.putData("Ramping PID", robot.ramp.rampPID);
        SmartDashboard.putData("Straight PID", robot.straight.straightPID);
        SmartDashboard.putData("Turn PID", robot.turn.turnPID);
        
        //SmartDashboard.putData("Elevate PID", robot.elevate. );
    }
    
    // Places sensor values onto SmartDashboard
    public void displaySensors() {
        SmartDashboard.putNumber("Left Encoder", robot.encode.leftEnc.getDistance());
        SmartDashboard.putNumber("Right Encoder", robot.encode.rightEnc.getDistance());
        SmartDashboard.putNumber("Average Encoder", robot.encode.averageDist());
        
        SmartDashboard.putNumber("Elevtaor Pot", robot.elevate.elevatorPot.get());
    }
    
}
