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
    
	double p, i, d, output;
    
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
		this.output = output;
	}

	public boolean driveDistance(int distance) {
		ultrasonicPID.setSetpoint(distance);
		ultrasonicPID.enable();
		robot.drive.doubleVicsDrive(output, output);

		if (hitDistance()) {
			ultrasonicPID.disable();
			robot.drive.doubleVicsDrive(0, 0);
			return true;
		}
		return false;
	}

	public boolean hitDistance() {
		return ultrasonicPID.onTarget();
	}
	
	public double UltraRampTele(double rampDist, double tolerance) {

		rawDist = RawDist();

		if (rawDist < Constants.ultraZeroPoint) {
			rawDist = Constants.ultraZeroPoint;
		}

		if (rawDist < rampDist) {
			// $\frac{\left(\cos \left(x+\pi \right)+1\right)}{2}$ for DESMOS
			rampVal = ((Math
					.cos((((rawDist - Constants.ultraZeroPoint) / (rampDist - Constants.ultraZeroPoint)) * Math.PI)
							+ Math.PI) + 1) / 2);
		} else {
			rampVal = 1;
		}

		if (rampVal < Constants.cutoffMult) {
			rampVal = Constants.cutoffMult;
		}
        
		if(Constants.ultraZeroPoint + tolerance > RawDist())
		{
			rampVal=0;
			
		}
		return rampVal;
	}

	public boolean UltraRampAuto(double rampDist, double tolerance) {

		rawDist = RawDist();

		if (rawDist < Constants.ultraZeroPoint) {
			rawDist = Constants.ultraZeroPoint;
		}

		if (rawDist < rampDist) {
			// $\frac{\left(\cos \left(x+\pi \right)+1\right)}{2}$ for DESMOS
			rampVal = ((Math.cos((((rawDist - Constants.ultraZeroPoint) / 
						(rampDist - Constants.ultraZeroPoint)) * Math.PI)
							+ Math.PI) + 1) / 2);
		} else {
			rampVal = 1;
		}

		if (rampVal < Constants.cutoffMult) {
			rampVal = Constants.cutoffMult;
		}

		robot.drive.doubleVicsDrive(rampVal, rampVal);  //PIDs ????? drive straight
		
		inPosition = (Constants.ultraZeroPoint + tolerance > RawDist());

		if (inPosition) {
			robot.drive.doubleVicsDrive(0, 0);
		}
		return inPosition;
	}

	public double RawDist() {
		return (ultrasonic.getRangeInches());

	}
}