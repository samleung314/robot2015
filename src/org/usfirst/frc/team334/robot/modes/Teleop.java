package org.usfirst.frc.team334.robot.modes;

import org.usfirst.frc.team334.robot.Robot;

public class Teleop {

    Robot robot;

    public Teleop(Robot robot) {
        this.robot = robot;
    }

    public void teleInit() {
        // drive.straightPID.enable();
    }

    public void teleopPeri() {
        robot.control.joystickDrive();
        robot.smart.displayPIDs();
    }

    // Choose between xBox drive and joystick drive
    public void chooseController() {
        if (robot.smart.chooseStick == 0) {
            robot.control.joystickDrive();
        } else if (robot.smart.chooseStick == 1) {
            robot.control.xBoxDrive();
        }
    }

}
