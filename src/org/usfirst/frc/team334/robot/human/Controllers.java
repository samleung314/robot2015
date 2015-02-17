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
	public double xBoxLeftY, xBoxRightY, xBoxLeftTrigger, xBoxRightTrigger;
	boolean xBoxA, xBoxB, xBoxX, xBoxY, xBoxLeftBump, xBoxRightBump, rightJoyFour, rightJoyFive,
			press;

	public int elevatorLevel = 0;

	/* Joystick Controllers Input */
	// Pushing up returns negative values, pulling returns positive values
	double leftJoyY, rightJoyY;
	boolean leftTrigger, rightTrigger, leftJoyThree, rightJoyThree, xBoxYClicked = false,
			xBoxAClicked = false;

	private double mult;
	
	public Controllers(Robot robot) {
		this.robot = robot;
		
		press = false;
	}

	// Updates variables with controller inputs. Needs to be run periodically
	// for any controller code to work
	public void getControllers() {
		// xBox Controller
		xBoxLeftY =  xBox.getRawAxis(1);
		xBoxRightY = xBox.getRawAxis(5);
		xBoxA = xBox.getRawButton(1);
		xBoxB = xBox.getRawButton(2);
		xBoxX = xBox.getRawButton(3);
		xBoxY = xBox.getRawButton(4);
		xBoxLeftBump = xBox.getRawButton(5);
		xBoxRightBump = xBox.getRawButton(6);
		xBoxLeftTrigger = xBox.getRawAxis(2);
		xBoxRightTrigger = xBox.getRawAxis(2);
		

		// Joysticks
		leftJoyY = leftJoy.getY();
		leftTrigger = leftJoy.getTrigger();
		leftJoyThree = leftJoy.getRawButton(3);
		
		rightJoyY = rightJoy.getY();
		rightJoyThree = rightJoy.getRawButton(3);
		rightJoyFour = rightJoy.getRawButton(4);
		rightJoyFive = rightJoy.getRawButton(5);
		rightTrigger = rightJoy.getTrigger();
	}
	
	public void positionElevator() {
		
		double shiftValues = xBoxLeftY + 1;
		double mappedHeight = shiftValues * (Constants.elevatorHeight/2);
		
		if(xBoxA) {
			robot.pot.elevatorPID.disable();
			robot.elevator.elevatorVics.set(0);
			
			if(!press)
			{
				System.out.println(robot.pot.getLevel());
				press = true;
			}
		} else {
			robot.pot.elevatorPID.enable();
			robot.pot.elevatorPID.setSetpoint(mappedHeight);
			press = false;
		}
	}

	// Driving with the joystick controllers
	public void joystickDrive() {
		robot.drive.chasisDrive.tankDrive(-Constants.driveMuliplier * leftJoyY, -Constants.driveMuliplier * rightJoyY);
	}
	/*
    public void dynamicDrive()
    {
    	if(rightJoyFour) {
    		ultraSonicDrive(Constants.canTolerance);
    	}else if(rightJoyFive) {
    		ultraSonicDrive(Constants.toteTolerance);
    	}
    	else {
    		forestDrive();
    	}
    }
    */
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
		robot.elevator.doubleVicsElevator(-xBoxLeftY);

		if (xBoxLeftBump) robot.elevator.elevatorRelease();
		else if (xBoxRightBump) robot.elevator.elevatorLock();
		else if(xBoxA) robot.air.flippersGrip();
		else if(xBoxB) robot.air.flippersRelease();
		else if(xBoxX) robot.air.armsRetract();
		else if(xBoxY) robot.air.armsExtend();
	}

	public double deadZone(double input) {
		if (input < 0.15 || input > -0.15) {
			return 0;
		} else {
			return input;
		}
	}
	
	public void forestDrive() {

		if (rightJoyThree) {
			ultraSonicDrive(Constants.canTolerance);
		} else if (leftJoyThree) {
			ultraSonicDrive(Constants.toteTolerance);
		} else {
			if (!leftTrigger && !rightTrigger) {
				robot.drive.chasisDrive.tankDrive(Constants.highGearSpeed
						* leftJoyY, Constants.highGearSpeed * rightJoyY);
			} else {
				robot.drive.chasisDrive.tankDrive(Constants.lowGearSpeed
						* leftJoyY, Constants.lowGearSpeed * rightJoyY);
			}
		}

	}

	public void ultraSonicDrive(double tolerance) {

		mult = robot.ultrasonic.UltraRampTele(tolerance);

		if (!leftTrigger && !rightTrigger) {

			robot.drive.chasisDrive.tankDrive(Constants.highGearSpeed
					* leftJoyY * mult, Constants.highGearSpeed * rightJoyY
					* mult);

		} else {

			robot.drive.chasisDrive.tankDrive(Constants.lowGearSpeed * leftJoyY
					* mult, Constants.lowGearSpeed * rightJoyY * mult);
		}
	}
/*	
public void ultraSonicDrive(double tolerance) {
		
		mult = robot.ultrasonic.UltraRampTele(tolerance);
		
		if (!leftTrigger && !rightTrigger) {
			
			robot.drive.chasisDrive.tankDrive(Constants.highGearSpeed
					* leftJoyY * mult, Constants.highGearSpeed * rightJoyY * mult);
			
		} else {
			
			robot.drive.chasisDrive.tankDrive(
					Constants.lowGearSpeed * leftJoyY * mult, Constants.lowGearSpeed
							* rightJoyY* mult);
		}
	}
*/
	public void riceOperate() {
		if (xBoxLeftBump) {
			robot.air.armsExtend();
		}
		if (xBoxRightBump) {
			robot.air.armsRetract();
		}
		if (xBoxY) {
			if (!xBoxYClicked) {
				xBoxYClicked = true;
				elevatorLevel = elevatorLevel + 1 > 6 ? 6 : elevatorLevel + 1;
				robot.pot.setElevatorLevel(elevatorLevel);
			}
		} else {
			xBoxYClicked = false;
		}
		if (xBoxA) {
			if (!xBoxAClicked) {
				xBoxAClicked = true;
				elevatorLevel = elevatorLevel - 1 < 0 ? 0 : elevatorLevel - 1;
				robot.pot.setElevatorLevel(elevatorLevel);
			}
		} else {
			xBoxAClicked = false;
		}
		
		if (deadZone(xBoxLeftY) != 0 && deadZone(xBoxRightY) == 0) {
			robot.elevator.doubleVicsElevator(xBoxLeftY);
		} else if (deadZone(xBoxRightY) != 0 && deadZone(xBoxLeftY) == 0) {
			robot.elevator.doubleVicsElevator(xBoxRightY * .50);
		} else {
			robot.elevator.doubleVicsElevator(0);
		}
		
		if(xBoxLeftTrigger > 0.9 && xBoxRightTrigger < 0.1) {
			robot.air.flippersGrip();
		}
		else if (xBoxRightTrigger > 0.9 && xBoxLeftTrigger < 0.1) {
			robot.air.flippersRelease();
		}

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
