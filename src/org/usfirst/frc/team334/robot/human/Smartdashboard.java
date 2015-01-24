package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.Robot;

public class Smartdashboard {

    Robot robot;

    public double chooseStick;

    public Smartdashboard(Robot robot) {
        this.robot = robot;
    }

    // Lets you choose between xBox and joystick drive by entering 0 or 1 in
    // SmartDashboard field
    public void testCommands() {
        chooseStick = SmartDashboard.getNumber("Choose Joystick", 0);
    }

    // Places PID object onto SmartDashboard
    public void displayPIDs() {
        SmartDashboard.putData("Drive Straight PID", robot.drive.straightPID);
    }
}
