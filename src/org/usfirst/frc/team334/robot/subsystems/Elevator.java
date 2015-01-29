package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.*;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.AnalogPotentiometer;


public class Elevator {

	Robot robot;
	
	private final VictorSP elevatorVicA;
    private final VictorSP elevatorVicB;
    
    public DoubleVics elevatorVics;
    
    public AnalogPotentiometer elevatorPot;
    
	boolean lock = false;
	boolean moving = false;
	double desiredpot = 1;
	double maxpot = 2;
	double minpot = 0;

	public Elevator(Robot robot) {
		this.robot = robot;
		
		elevatorVicA = new VictorSP(Constants.elevatorVictorA);
        elevatorVicB = new VictorSP(Constants.elevatorVictorB);
        
        elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);
        
        elevatorPot = new AnalogPotentiometer(Constants.elevatorPot);
        //10 Turns max on the pot, 9.6 turns max on the elevator 
	}

	public void elevatorStop() // Makes Elevator stay still
	{
		elevatorVics.set(0);
	}

	public void elevatorBreak() // Engages the Dog Break in elevator
	{
		if (!moving) {
			lock = true;
		}
	}

	public void elevatorRelease() // Disengages the Dog Break in elevator
	{
		if (!moving) {
			lock = false;
		}
	}

	public void elevatorUp() {
		if (elevatorPot.get() < desiredpot 
		        && elevatorPot.get() < maxpot 
		        && !lock) {
			manualVicsElevator(-0.35);
		}
	}

	public void elevatorDown() // Makes Elevator go down
	{
		if (elevatorPot.get() > desiredpot 
		        && elevatorPot.get() > minpot
		        && !lock) {
			manualVicsElevator(0.35);
		}
	}

	public void manualVicsElevator(double speed) {
		if (speed != 0 && !lock) {
			elevatorVicA.set(speed);
			elevatorVicB.set(speed);
			moving = true;
		} else {
			moving = false;
		}
	}	
}
