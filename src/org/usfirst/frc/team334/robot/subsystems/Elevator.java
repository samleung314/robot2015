package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.DoubleVics;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;

public class Elevator {

	Robot robot;

	private final VictorSP elevatorVicA;
	private final VictorSP elevatorVicB;

	public DoubleVics elevatorVics;

	public AnalogPotentiometer elevatorPot;

	private final PIDController elevatorPID;

	private final DigitalInput highSwitch, lowSwitch;

	public boolean locked, moving, highLimit, lowLimit;
	public double minpot, maxpot, desiredpot;
	
	double p = 0, i = 0, d = 0;

	public Elevator(Robot robot) {
		this.robot = robot;

		elevatorVicA = new VictorSP(Constants.elevatorVictorA);
		elevatorVicB = new VictorSP(Constants.elevatorVictorB);
		
		elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);

		elevatorPot = new AnalogPotentiometer(Constants.elevatorPot);
		// 10 Turns max on the pot, 9.6 turns max on the elevator

		elevatorPID = new PIDController(p, i, d, elevatorPot, elevatorVics);

		highSwitch = new DigitalInput(Constants.highSwitch);
		lowSwitch = new DigitalInput(Constants.lowSwitch);
	}
	
	public boolean freeToMove() {
		highLimit = highSwitch.get();
		lowLimit = lowSwitch.get();
		
		if (highLimit || lowLimit || locked) { //If any are true, robot is not free to move
			return false;
		}
		else {
			return true;
		}		
	}

	public void setElevator(double setpoint) {
		if (freeToMove()) {
			elevatorPID.setSetpoint(setpoint);
			elevatorPID.enable();
		}
	}

	public void elevatorRelease() // Disengages the Dog Break in elevator
	{
		locked = false;
		robot.air.releaseDog();
	}
	
	public void elevatorBreak() // Engages the Dog Break in elevator
	{
		if (!moving) { //Only engage if elevator is not being moved
			locked = true;
			robot.air.lockDog();
		}
	}

	public void elevatorUp() {
		if (elevatorPot.get() < desiredpot 
				&& elevatorPot.get() < maxpot
				&& !locked) {
			manualVicsElevator(0.35);
		} else {
			manualVicsElevator(0);
		}
	}

	public void elevatorDown() { // Makes Elevator go down 
		if (elevatorPot.get() > desiredpot 
				&& elevatorPot.get() > minpot
				&& !locked) {
			manualVicsElevator(-0.35);
		} else {
			manualVicsElevator(0);
		}
	}

	public void manualVicsElevator(double speed) {
		if (locked) { //Cannot move when Dog Gear is engaged
			elevatorVicA.set(0);
			elevatorVicB.set(0);
			moving = false;
		}
		else if (highLimit) { //Can only move down when activated
			if (speed < 0){
				elevatorVicA.set(speed);
				elevatorVicB.set(speed);
			}
		}
		else if (lowLimit) { //Can only move up when activated
			if (speed > 0){
				elevatorVicA.set(speed);
				elevatorVicB.set(speed);
			}
		}
		else //Can move freely
		{
			elevatorVicA.set(speed);
			elevatorVicB.set(speed);
		}
		
		if (speed == 0) {
			moving = false;
		}
		else {
			moving = true;
		}
	}
	
	public void doubleVicsElevator(double speed) {
		if (locked) { //Cannot move when Dog Gear is engaged
			elevatorVics.set(0);
			moving = false;
		}
		else if (highLimit) { //Can only move down when activated
			if (speed < 0){
				elevatorVics.set(speed);
			}
		}
		else if (lowLimit) { //Can only move up when activated
			if (speed > 0){
				elevatorVics.set(speed);
			}
		}
		else { //Can move freely 
			elevatorVics.set(speed);
		}
		
		if (speed == 0) {
			moving = false;
		}
		else {
			moving = true;
		}
	}

	public void setElevatorLevel(int level) {
		switch (level) {
		case 1:
			elevatorPID.setSetpoint(100);
			break;
		case 2:
			elevatorPID.setSetpoint(100);
			break;
		case 3:
			elevatorPID.setSetpoint(100);
			break;
		case 4:
			elevatorPID.setSetpoint(100);
			break;
		case 5:
			elevatorPID.setSetpoint(100);
			break;
		case 6:
			elevatorPID.setSetpoint(100);
			break;
		}

	}
}
