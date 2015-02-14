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

	double p, i, d, output;

	public UltrasonicPID(Robot robot) {
		this.robot = robot;

		ultrasonic = new Ultrasonic(new DigitalOutput(Constants.ultrasonicA),
				new DigitalInput(Constants.ultrasonicB));
		ultrasonicPID = new PIDController(p, i, d, ultrasonic, this);
		ultrasonicPID.setContinuous();
		ultrasonicPID.setAbsoluteTolerance(Constants.ultraSonicPIDTolerance);
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

	@Override
	public void pidWrite(double output) {
		this.output = output;
	}

}
