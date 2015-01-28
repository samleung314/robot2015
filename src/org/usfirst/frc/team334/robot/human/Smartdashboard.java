package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.Robot;

public class Smartdashboard {

    Robot robot;
    
    public Smartdashboard(Robot robot) {
        this.robot = robot;
    }

    // Places PID object onto SmartDashboard
    public void displayPIDs() {
        SmartDashboard.putData("Straight PID", robot.drive.straightPID);
        SmartDashboard.putData("Turn PID", robot.drive.turnPID);
    }
}
