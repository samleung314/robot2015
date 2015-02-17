package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;
import org.usfirst.frc.team334.robot.Constants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorPID implements PIDSource{
	
	Robot robot;
	
	//10 Turns max on the pot. Raw values range from 0 to 1.
	public AnalogPotentiometer elevatorPot;
	public PIDController elevatorPID;
	
	double p = 0.2, i = 0.045, d = 0.001;
	
	public ElevatorPID(Robot robot) {
		this.robot = robot;
		elevatorPot = new AnalogPotentiometer(Constants.elevatorPot);
		
		elevatorPID = new PIDController(p, i, d, this, robot.elevator.elevatorVics);
		elevatorPID.setAbsoluteTolerance(Constants.elevatorPIDTolerance);
	}
	
	public double getLevel() {	//Converts raw potentiometer units to inches
		double zeroedPot = Constants.elevatorPotLOWLimit - elevatorPot.get(); //Maps the low limit to 0 and has a range from 0 to 0.984
		return zeroedPot * ((double)(Constants.elevatorHeight/Constants.elevatorRawLength));
	}
	
	public boolean elevatePID(double height) {
		elevatorPID.setOutputRange(-1, 1);
		elevatorPID.setSetpoint(height);
		elevatorPID.enable();
		
		return elevatorPID.onTarget();
	}
	
	public boolean elevatePIDLock(double height) {
		if(robot.elevator.locked){
			robot.elevator.elevatorAutoRelease();
		}
		elevatorPID.setOutputRange(-1, 1);
		elevatorPID.setSetpoint(height);
		elevatorPID.enable();
		
		if(elevatorPID.onTarget()) {
			elevatorPID.disable();
			robot.elevator.elevatorVics.set(0);
			
			if(height > 0 ) {
				robot.elevator.elevatorLock();
			}
		}
		return elevatorPID.onTarget();
	}
	
	//Not Done
	public boolean elevatePIDLockFix(double height) {
		if(robot.elevator.locked){
			if(robot.elevator.elevatorAutoRelease()) {
				upLittle();
			}
		}
		
		elevatorPID.setOutputRange(-1, 1);
		elevatorPID.setSetpoint(height);
		elevatorPID.enable();
		
		if(elevatorPID.onTarget()) {
			elevatorPID.disable();
			robot.elevator.elevatorVics.set(0);
			
			if(height > 0 ) {
				robot.elevator.elevatorLock();
			}
		}
		return elevatorPID.onTarget();
	}
	
	public void upLittle() {
		robot.elevator.elevatorVics.set(1);
		Timer.delay(0.2);
		robot.elevator.elevatorVics.set(0);
	}
	
	public boolean elevatePIDTuning(double height, double speed) {
		elevatorPID.setOutputRange(-0.5, speed);
		elevatorPID.setSetpoint(height);
		elevatorPID.enable();
		
		if(elevatorPID.onTarget()) {
			elevatorPID.disable();
			robot.elevator.elevatorVics.set(0);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean elevatePIDPower(double height) {
		elevatorPID.setOutputRange(-1, 1);
		elevatorPID.setSetpoint(height);
		elevatorPID.enable();
		
		if(elevatorPID.onTarget()) {
			elevatorPID.disable();
			robot.elevator.elevatorVics.set(0);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setElevatorLevel(int level) { // Used for moving elevator to 6
												// predetermined levels
		switch (level) {
		case 1:
			elevatePID(Constants.elevatorLevelOne);
			break;
		case 2:
			elevatePID(Constants.elevatorLevelTwo);
			break;
		case 3:
			elevatePID(Constants.elevatorLevelThree);
			break;
		case 4:
			elevatePID(Constants.elevatorLevelFour);
			break;
		case 5:
			elevatePID(Constants.elevatorLevelFive);
			break;
		case 6:
			elevatePID(Constants.elevatorLevelSix);
			break;
		}
	}

	@Override
	public double pidGet() {
		return getLevel();
	}
}
