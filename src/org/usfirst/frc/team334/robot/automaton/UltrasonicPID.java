package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicPID implements PIDOutput {

	Robot robot;

	public PIDController ultrasonicPID;
	public Ultrasonic ultrasonic;
	public DigitalInput ultraInput;
	public DigitalOutput ultraOutput;
    
	double p, i, d, ultraSpeed;
    
	private double rampVal,rawDist;
	private boolean inPosition;
	
	public UltrasonicPID(Robot robot) {
		this.robot = robot;

		ultraInput = new DigitalInput(Constants.ultrasonicInput);
		ultraOutput = new DigitalOutput(Constants.ultrasonicOutput);

		ultrasonic = new Ultrasonic(ultraOutput, ultraInput, Ultrasonic.Unit.kInches);
		ultrasonic.setAutomaticMode(true);
		ultrasonic.setEnabled(true);
		
		ultrasonicPID = new PIDController(p, i, d, ultrasonic, this);
		ultrasonicPID.setAbsoluteTolerance(Constants.ultraSonicPIDTolerance);
	}
	
	@Override
	public void pidWrite(double output) {
		this.ultraSpeed = output;
	}

	public boolean driveDistance(int distance) {
		ultrasonicPID.setSetpoint(distance);
		ultrasonicPID.enable();
		robot.drive.victorDrive(ultraSpeed, ultraSpeed);

		if (hitDistance()) {
			ultrasonicPID.disable();
			robot.drive.victorDrive(0, 0);
			return true;
		}
		return false;
	}

	public boolean hitDistance() {
		return ultrasonicPID.onTarget();
	}
	
	public double ultraRampTele(double tolerance) {

		rawDist = rawDist();

		if (rawDist < Constants.ultraZeroPoint) {
			rawDist = Constants.ultraZeroPoint;
		}

		if (rawDist < Constants.rampDist) {
			// $\frac{\left(\cos \left(x+\pi \right)+1\right)}{2}$ for DESMOS
			rampVal = ((Math
					.cos((((rawDist - Constants.ultraZeroPoint) / (Constants.rampDist - Constants.ultraZeroPoint)) * Math.PI)
							+ Math.PI) + 1) / 2);
		} else {
			rampVal = 1;
		}

		if (rampVal < Constants.cutoffMult) {
			rampVal = Constants.cutoffMult;
		}
        
		if(Constants.ultraZeroPoint + tolerance > rawDist()){
			rampVal = 0;
		}
		return rampVal;
	}

	public boolean ultraRampAutoOLD(double tolerance) {
		
		rawDist = rawDist();

		if (rawDist < Constants.ultraZeroPoint) {
			rawDist = Constants.ultraZeroPoint;
		}

		if (rawDist < Constants.rampDist) {
			// $\frac{\left(\cos \left(x+\pi \right)+1\right)}{2}$ for DESMOS
			rampVal = ((Math.cos((((rawDist - Constants.ultraZeroPoint) / 
						(Constants.rampDist - Constants.ultraZeroPoint)) * Math.PI)
							+ Math.PI) + 1) / 2);
		} else {
			rampVal = 1;
		}

		if (rampVal < Constants.cutoffMult) {
			rampVal = Constants.cutoffMult;
		}
        
		rampVal = rampVal * Constants.ultraAutonMaxSpeed;
		robot.drive.victorDrive(rampVal, rampVal);  //PIDs ????? drive straight
		
		inPosition = (Constants.ultraZeroPoint + tolerance >= rawDist());

		if (inPosition) {
			robot.drive.victorDrive(0, 0);	
			return inPosition;
		}
		return inPosition;
	}
	
	public boolean ultraRampAuto(double tolerance) {
		robot.straight.keepStraightPID.enable();
		rawDist = rawDist();

		if (rawDist < Constants.ultraZeroPoint) {
			rawDist = Constants.ultraZeroPoint;
		}

		if (rawDist < Constants.rampDist) {
			// $\frac{\left(\cos \left(x+\pi \right)+1\right)}{2}$ for DESMOS
			rampVal = ((Math.cos((((rawDist - Constants.ultraZeroPoint) / 
						(Constants.rampDist - Constants.ultraZeroPoint)) * Math.PI)
							+ Math.PI) + 1) / 2);
		} else {
			rampVal = 1;
		}

		if (rampVal < Constants.cutoffMult) {
			rampVal = Constants.cutoffMult;
		}
        
		rampVal = rampVal * Constants.ultraAutonMaxSpeed;
		
		double leftOutput = rampVal + robot.straight.straightSpeed;
		double rightOutput = rampVal - robot.straight.straightSpeed;
		
		robot.drive.victorDrive(leftOutput, rightOutput);
		
		inPosition = (Constants.ultraZeroPoint + tolerance >= rawDist());
		

		if (inPosition) {
			robot.straight.keepStraightPID.disable();
			robot.drive.victorDrive(0, 0);
			robot.encode.resetEncoders();
			robot.turn.gyro.reset();
			return inPosition;
		}
		return inPosition;
	}

	public double rawDist() {
		return ultrasonic.getRangeInches();

	}
}