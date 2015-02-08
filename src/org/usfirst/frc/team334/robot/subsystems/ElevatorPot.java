package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Robot;
import org.usfirst.frc.team334.robot.Constants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;

public class ElevatorPot implements PIDSource{
	
	Robot robot;
	
	//10 Turns max on the pot. Raw values range from 0 to 1.
	public AnalogPotentiometer elevatorPot;
	public PIDController elevatorPID;
	
	double p, i, d;
	
	public ElevatorPot(Robot robot) {
		this.robot = robot;
		elevatorPot = new AnalogPotentiometer(Constants.elevatorPot);
		
		elevatorPID = new PIDController(p, i, d, this, robot.elevate.elevatorVics);
		elevatorPID.setOutputRange(-0.5, 0.5); //Limits the elevator speed so it doesn't go too fast
	}
	
	public double getLevel() {	//Converts raw potentiometer units to inches
		double zeroedPot = Constants.elevatorPotLOWLimit - elevatorPot.get(); //Maps the low limit to 0 and has a range from 0 to 0.984
		return zeroedPot * (Constants.elevatorMovementLength/Constants.elevatorRawLength);
	}
	
	public void setElevatorLevel(int level) { //Used for moving elevator to 6 predetermined levels
		switch (level) {
		case 1:
			elevatorPID.setSetpoint(0);
			break;
		case 2:
			elevatorPID.setSetpoint(0);
			break;
		case 3:
			elevatorPID.setSetpoint(0);
			break;
		case 4:
			elevatorPID.setSetpoint(0);
			break;
		case 5:
			elevatorPID.setSetpoint(0);
			break;
		case 6:
			elevatorPID.setSetpoint(0);
			break;
		}
	}

	@Override
	public double pidGet() {
		return getLevel();
	}
}
