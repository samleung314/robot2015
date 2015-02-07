package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.DoubleVics;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class Elevator {

	Robot robot;

	private final VictorSP elevatorVicA;
	private final VictorSP elevatorVicB;

	public DoubleVics elevatorVics;

	private final DigitalInput highSwitch, lowSwitch;

	public boolean locked, moving = false, highLimit, lowLimit;
	public double minpot, maxpot, desiredpot;

	public Elevator(Robot robot) {
		this.robot = robot;

		elevatorVicA = new VictorSP(Constants.elevatorVictorA);
		elevatorVicB = new VictorSP(Constants.elevatorVictorB);

		elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);

		highSwitch = new DigitalInput(Constants.highSwitch);
		lowSwitch = new DigitalInput(Constants.lowSwitch);
	}
	
	public boolean freeToMove() {
		highLimit = !highSwitch.get();
		lowLimit = !lowSwitch.get();

		return (highLimit || lowLimit || locked);
	}

	public void elevatorRelease() // Disengages the Dog Break in elevator
	{
		locked = false;
		robot.air.releaseDog();
	}

	public void elevatorBreak() // Engages the Dog Break in elevator
	{
		if (!moving) { // Only engage if elevator is not being moved
			locked = true;
			robot.air.lockDog();
		}
	}

	public void elevatorUp() {
		if (robot.pot.elevatorPot.get() < desiredpot && robot.pot.elevatorPot.get() < maxpot
				&& !locked) {
			manualVicsElevator(0.35);
		} else {
			manualVicsElevator(0);
		}
	}

	public void elevatorDown() { // Makes Elevator go down
		if (robot.pot.elevatorPot.get() > desiredpot && robot.pot.elevatorPot.get() > minpot
				&& !locked) {
			manualVicsElevator(-0.35);
		} else {
			manualVicsElevator(0);
		}
	}

	public void goPot(double dPot, double tol) {

		if (robot.pot.elevatorPot.get() < dPot - tol) {
			doubleVicsElevator(.35);
		} else if (robot.pot.elevatorPot.get() > dPot + tol) {
			doubleVicsElevator(-.35);
		} else {
			doubleVicsElevator(0);
		}

	}

	public void manualVicsElevator(double speed) {
		// True when not pressed
		highLimit = highSwitch.get();
		lowLimit = lowSwitch.get();

		if (!locked) {
			if (highLimit && speed >= 0) {
				elevatorVicA.set(speed);
				elevatorVicB.set(speed);
			} else if (lowLimit && speed <= 0) {
				elevatorVicA.set(speed);
				elevatorVicB.set(speed);
			} else {
				elevatorVicA.set(0);
				elevatorVicB.set(0);
				// moving = false;
			}
		} else {
			elevatorVics.set(0);
			// moving = false;
		}

		/*
		 * if (speed == 0) { 
		 *   moving = false; 
		 * } else { 
		 * 	  moving = true; 
		 * }
		 */
	}

	public void doubleVicsElevator(double speed) {
		//True when not pressed
		highLimit = highSwitch.get();
		lowLimit = lowSwitch.get();

		if (!locked) {
			if (highLimit && speed >= 0) {
				elevatorVics.set(speed);
			} else if (lowLimit && speed <= 0) {
				elevatorVics.set(speed);
			} else {
				elevatorVics.set(0);
				//moving = false;
			}
		} else {
			elevatorVics.set(0);
			//moving = false;
		}

		/*if (speed == 0) {
			moving = false;
		} else {
			moving = true;
		}*/
	}
}
