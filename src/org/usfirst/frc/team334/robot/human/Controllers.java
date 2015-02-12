package org.usfirst.frc.team334.robot.human;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controllers {

	Robot robot;

	// Creation of controller objects
	final Joystick xBox = new Joystick(Constants.xbox);
	final Joystick leftJoy = new Joystick(Constants.leftJoystick);
	final Joystick rightJoy = new Joystick(Constants.rightJoystick);

	/* xBox Controller Inputs */
	// Pushing up returns negative values, pulling returns positive values
	public double xBoxLeftY, xBoxRightY;
	boolean xBoxA, xBoxB, xBoxX, xBoxY, xBoxLeftBump, xBoxRightBump;

	public int elevatorLevel = 0;

	/* Joystick Controllers Input */
	// Pushing up returns negative values, pulling returns positive values
	double leftJoyY, rightJoyY;
	boolean leftTrigger, rightTrigger, leftTriggerClicked = false,
			rightTriggerClicked = false;

	public Controllers(Robot robot) {
		this.robot = robot;
	}

	// Updates variables with controller inputs. Needs to be run periodically
	// for any controller code to work
	public void getControllers() {
		// xBox Controller
		xBoxLeftY = -xBox.getRawAxis(1);
		xBoxRightY = xBox.getRawAxis(5);
		xBoxA = xBox.getRawButton(1);
		xBoxB = xBox.getRawButton(2);
		xBoxX = xBox.getRawButton(3);
		xBoxY = xBox.getRawButton(4);
		xBoxLeftBump = xBox.getRawButton(5);
		xBoxRightBump = xBox.getRawButton(6);

		// Joysticks
		leftJoyY = -Constants.driveMuliplier * leftJoy.getY();
		rightJoyY = -Constants.driveMuliplier * rightJoy.getY();
		leftTrigger = leftJoy.getTrigger();
		rightTrigger = rightJoy.getTrigger();
	}

	// Driving with the xBox controller
	public void xBoxDrive() {
		robot.drive.chasisDrive.tankDrive(-xBoxLeftY, -xBoxRightY);
	}

	// Driving with the joystick controllers
	public void joystickDrive() {
		robot.drive.chasisDrive.tankDrive(leftJoyY, rightJoyY);
	}

	public void forestDrive() {
		getControllers();
		if (leftTrigger && rightTrigger) {
			robot.drive.chasisDrive.tankDrive(Constants.highGearSpeed
					* leftJoyY, Constants.highGearSpeed * rightJoyY);
		} else if (!leftTrigger && !rightTrigger) {
			robot.drive.chasisDrive.tankDrive(
					Constants.lowGearSpeed * leftJoyY, Constants.lowGearSpeed
							* rightJoyY);
		}
	}

	// Used for testing solenoids
	public void testSolenoids() {
		if (xBoxA) {
			robot.air.dogLock();
		} else if (xBoxB) {
			robot.air.dogRelease();
		} else if (xBoxX) {
			robot.air.testForward();
		} else if (xBoxY) {
			robot.air.testReverse();
		} else if (xBoxLeftBump) {
			robot.air.chargeAir();
		} else if (xBoxRightBump) {
			robot.air.compress.stop();
		}
	}

	// Mapping elevator functionality to xBox
	public void controlElevator() {
		robot.elevate.doubleVicsElevator(-xBoxLeftY);

		// Controlling dog gear with xBox buttons
		if (xBoxX) {
			robot.elevate.elevatorRelease();
			System.out.println("X Button");
		}
		if (xBoxY) {
			robot.elevate.elevatorBreak();
			System.out.println("Y Button");
		}
	}

	public double deadZone(double input) {
		if (input < 0.1 || input > -0.1) {
			return 0;
		} else {
			return input;
		}
	}

	public void setElevatorLevel() {
		if (leftTrigger) {
			if (!leftTriggerClicked) {
				leftTriggerClicked = true;
				elevatorLevel--;
				if (elevatorLevel < 1) {
					elevatorLevel = 1;
				}
			}
		} else {
			leftTriggerClicked = false;
		}

		if (rightTrigger) {
			if (!rightTriggerClicked) {
				rightTriggerClicked = true;
				elevatorLevel++;
				if (elevatorLevel > 6) {
					elevatorLevel = 6;
				}
			}
		} else {
			rightTriggerClicked = false;
		}

		robot.pot.setElevatorLevel(elevatorLevel);
	}

}

/*
 * XBOX KEY MAPPINGS 1: A 2: B 3: X 4: Y 5: Left Bumper 6: Right Bumper 7: Back
 * 8: Start 9: Left Joystick 10: Right Joystick
 * 
 * The axis on the controller follow this mapping (all output is between -1 and
 * 1) 0: Left Stick X Axis -Left:Negative ; Right: Positive 1: Left Stick Y Axis
 * -Up: Negative ; Down: Positive 2: Triggers -Left: Positive ; Right: Negative
 * 3: Right Stick X Axis -Left: Negative ; Right: Positive 4: Right Stick Y Axis
 * -Up: Negative ; Down: Positive
 */
