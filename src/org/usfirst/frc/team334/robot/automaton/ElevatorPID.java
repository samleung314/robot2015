package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;
import org.usfirst.frc.team334.robot.Constants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;

public class ElevatorPID implements PIDSource{
	
	Robot robot;
	
	//10 Turns max on the pot. Raw values range from 0 to 1.
	public AnalogPotentiometer elevatorPot;
	public PIDController elevatorPID;
	
	double p, i, d;
	
	public ElevatorPID(Robot robot) {
		this.robot = robot;
		elevatorPot = new AnalogPotentiometer(Constants.elevatorPot);
		
		elevatorPID = new PIDController(p, i, d, this, robot.elevator.elevatorVics);
		elevatorPID.setOutputRange(-0.4, 0.4);
		elevatorPID.setAbsoluteTolerance(Constants.elevatorPIDTolerance);
	}
	
	public double getLevel() {	//Converts raw potentiometer units to inches
		double zeroedPot = Constants.elevatorPotLOWLimit - elevatorPot.get(); //Maps the low limit to 0 and has a range from 0 to 0.984
		return zeroedPot * ((double)(Constants.elevatorHeight/Constants.elevatorRawLength));
	}
	
	public boolean elevatePID(double height) {
		elevatorPID.setOutputRange(-0.3, 0.3);
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
	
	public void setElevatorLevel(int level) { //Used for moving elevator to 6 predetermined levels
		switch (level) {
		case 1:
			elevatorPID.setSetpoint(Constants.elevatorLevelOne);
			break;
		case 2:
			elevatorPID.setSetpoint(Constants.elevatorLevelTwo);
			break;
		case 3:
			elevatorPID.setSetpoint(Constants.elevatorLevelThree);
			break;
		case 4:
			elevatorPID.setSetpoint(Constants.elevatorLevelFour);
			break;
		case 5:
			elevatorPID.setSetpoint(Constants.elevatorLevelFive);
			break;
		case 6:
			elevatorPID.setSetpoint(Constants.elevatorLevelSix);
			break;
		}
	}

	@Override
	public double pidGet() {
		return getLevel();
	}
}
