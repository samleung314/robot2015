package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team334.robot.Robot;

import org.usfirst.frc.team334.robot.physicals.*;

public class Controllers {

    Robot robot;

    final Joystick xBox = new Joystick(Constants.xbox);
    final Joystick leftJoy = new Joystick(Constants.leftJoystick);
    final Joystick rightJoy = new Joystick(Constants.rightJoystick);

    /* xBox Controller Inputs */
    double xBoxLeftY, xBoxRightY;
    boolean xBoxA, xBoxB, xBoxX, xBoxY, xBoxLeftBump, xBoxRightBump;

    /* Joystick Controllers Input */
    double leftJoyY, rightJoyY;

    public Controllers(Robot robot) {
        this.robot = robot;
    }
    
    public void getControllers() {
    	/* xBox Controller Inputs */
        xBoxLeftY = xBox.getRawAxis(1);
        xBoxRightY = xBox.getRawAxis(5);

        xBoxA = xBox.getRawButton(1);
        xBoxB = xBox.getRawButton(2);
        xBoxX = xBox.getRawButton(3);
        xBoxY = xBox.getRawButton(4);
        xBoxLeftBump = xBox.getRawButton(5);
        xBoxRightBump = xBox.getRawButton(6);

        /* Joystick Controllers Input */
        leftJoyY = leftJoy.getY();
        rightJoyY = rightJoy.getY();
    }

    public void xBoxDrive() {
    	getControllers();
    	//robot.drive.manualVicsDrive(-xBoxLeftY, -xBoxRightY);
    	robot.drive.chasisDrive.tankDrive(-xBoxLeftY, -xBoxRightY);
    }

    //Negative input makes left victors drive forwards
    public void joystickDrive() {
    	getControllers();
        //robot.drive.manualVicsDrive(-leftJoyY, -rightJoyY);
        robot.drive.chasisDrive.tankDrive(-leftJoyY, -rightJoyY);
    }
    
    public void controlPist() {
        getControllers();
        
        if (xBoxA) {
            robot.air.pistForward();
        }
        else if (xBoxB) {
            robot.air.pistReverse();
        }
        else if (xBoxX) {
            robot.air.pistOff();
        }
    }
}

/* XBOX KEY MAPPINGS
1: A
2: B
3: X
4: Y
5: Left Bumper
6: Right Bumper
7: Back
8: Start
9: Left Joystick
10: Right Joystick

The axis on the controller follow this mapping
(all output is between -1 and 1)
0: Left Stick X Axis
-Left:Negative ; Right: Positive
1: Left Stick Y Axis
-Up: Negative ; Down: Positive
2: Triggers
-Left: Positive ; Right: Negative
3: Right Stick X Axis
-Left: Negative ; Right: Positive
4: Right Stick Y Axis
-Up: Negative ; Down: Positive
*/
