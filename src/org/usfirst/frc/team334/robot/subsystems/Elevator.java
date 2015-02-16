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

	private final DigitalInput highSwitchLeft, lowSwitchLeft, highSwitchRight, lowSwitchRight;

	public boolean locked, moving = false, highLimit, lowLimit;
	public double minpot, maxpot, desiredpot;

	public Elevator(Robot robot) {
		this.robot = robot;

		elevatorVicA = new VictorSP(Constants.elevatorVictorA);
		elevatorVicB = new VictorSP(Constants.elevatorVictorB);

		elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);
		
		//Limit switches return raw value of false when pressed
		highSwitchLeft = new DigitalInput(Constants.highSwitchLeft);
		lowSwitchLeft = new DigitalInput(Constants.lowSwitchLeft);
		
		highSwitchRight = new DigitalInput(Constants.highSwitchRight);
	    lowSwitchRight = new DigitalInput(Constants.lowSwitchRight);
	}

	public boolean elevatorRelease() { // Releases the elevator break
		locked = false;
		robot.air.dogRelease();
		return true;
	}

	public boolean elevatorLock() { // Locks the elevator in place and stops elevator motors
		locked = true;
		elevatorVics.set(0);
		robot.air.dogLock();
		return true;
	}
	
	public boolean topOut() { //Returns true if either left/right limit switch pressed
		return (!highSwitchLeft.get() || !highSwitchRight.get());
	}
	
	public boolean bottomOut() { //Returns true if either left/right limit switch pressed
		return (!lowSwitchLeft.get() || !lowSwitchRight.get());
	}

	public boolean elevatorHeight(double height) {

		if (robot.pot.getLevel() < height - 1) {
			doubleVicsElevator(.35);
			return false;
		} else if (robot.pot.getLevel() > height + 1) {
			doubleVicsElevator(-.35);
			return false;
		} else {
			doubleVicsElevator(0);
			return true;
		}
	}

    public void doubleVicsElevator(double speed) {
        if (!locked) {
            if (!topOut() && speed >= 0) {
                elevatorVics.set(speed);
            } else if (!bottomOut() && speed <= 0) {
                elevatorVics.set(speed);
            } else {
                elevatorVics.set(0);
            }
        } else {
            elevatorVics.set(0);
        }
    }
    
    public void noSafety(double speed) { //USE ONLY FOR TESTING. ENSURE IT'S SAFE
    	if (!locked) {
    		elevatorVics.set(speed);
    	}
    	else {
    		elevatorVics.set(0);
    	}
    }
}
