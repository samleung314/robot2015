package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.DoubleVics;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

public class Elevator {

    Robot robot;

	private final VictorSP elevatorVicA;
	private final VictorSP elevatorVicB;
	public final VictorSP elevatorSingle;

	public DoubleVics elevatorVics;

	private final DigitalInput highSwitches, lowSwitches;

	public boolean locked, highLimit, lowLimit;
	public double minpot, maxpot, desiredpot;

	public Elevator(Robot robot) {
		this.robot = robot;

		elevatorVicA = new VictorSP(4);
		elevatorVicB = new VictorSP(Constants.elevatorVictorB);

		elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);
		
		elevatorSingle = new VictorSP(Constants.elevatorVictorA);
		
		//Limit switches return raw value of false when pressed
		highSwitches = new DigitalInput(Constants.highSwitches);
		lowSwitches= new DigitalInput(Constants.lowSwitches);
	}

	public boolean elevatorRelease() { // Releases the elevator break
		locked = false;
		robot.air.dogRelease();
		return true;
	}
	
	public boolean elevatorAutoRelease() { // Releases the elevator break
		locked = false;
		robot.air.dogRelease();
		Timer.delay(0.3);
		return true;
	}

	public boolean elevatorLock() { // Locks the elevator in place and stops elevator motors
		locked = true;
		elevatorVics.set(0);
		robot.air.dogLock();
		return true;
	}
	
	public boolean topOut() { //Returns true if either top left/right limit switches pressed
		return (!highSwitches.get());
	}
	
	public boolean bottomOut() { //Returns true if either bottom left/right limit switches pressed
		return (!lowSwitches.get());
	}
	
	public boolean zeroElevator() {
		if(!bottomOut()) {
			elevatorSingle.set(1);
			return bottomOut();
		}
		else {
			elevatorSingle.set(0);
			return bottomOut();
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
    
    public void singleVicElevator(double speed) {
    	if (!topOut() && speed <= 0) {
    		elevatorSingle.set(speed); //By default, up is negative and down is positive. 
        } else if (!bottomOut() && speed >= 0) {
            elevatorSingle.set(speed);
        } else {
        	elevatorSingle.set(0);
        }
    }
    
    public void noSafety(double speed) { //USE ONLY FOR TESTING. ENSURE IT'S SAFE
    	elevatorSingle.set(speed);
    }
}
