package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team334.robot.Robot;

import org.usfirst.frc.team334.robot.physicals.*;

public class Controllers {
	
	 Robot robot;
	
	 final Joystick xBox = new Joystick(Constants.xbox);
	 final Joystick leftJoy = new Joystick(Constants.leftJoystick);
	 final Joystick rightJoy = new Joystick(Constants.rightJoystick);
	
	/*xBox Controller Inputs*/
	 double xBoxLeftY = xBox.getRawAxis(2);
	 double xBoxRightY = xBox.getRawAxis(5);
	
	 boolean xBoxA = xBox.getRawButton(1);
	 boolean xBoxB = xBox.getRawButton(2);
	 boolean xBoxX = xBox.getRawButton(3);
	 boolean xBoxY = xBox.getRawButton(4);
	 boolean xBoxLeftBump = xBox.getRawButton(5);
	 boolean xBoxRightBump = xBox.getRawButton(6);
	
	/*Joystick Controllers Input*/
	 double leftJoyY = leftJoy.getY();
     double rightJoyY = rightJoy.getY();
	
	public Controllers(Robot robot){
		this.robot = robot;
	}
	
	public void xBoxDrive() {
		robot.mech.manualVicsDrive(xBoxLeftY, xBoxRightY);
		//mech.tank.tankDrive(xBoxLeftY, xBoxRightY);
	}
	
	public void joystickDrive() {
		robot.mech.manualVicsDrive(leftJoyY, rightJoyY);
		//mech.tank.tankDrive(leftJoyY, rightJoyY);
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
    1: Left Stick X Axis
    -Left:Negative ; Right: Positive
    2: Left Stick Y Axis
    -Up: Negative ; Down: Positive
    3: Triggers
    -Left: Positive ; Right: Negative
    4: Right Stick X Axis
    -Left: Negative ; Right: Positive
    5: Right Stick Y Axis
    -Up: Negative ; Down: Positive
    6: Directional Pad (Not recommended, buggy)
*/