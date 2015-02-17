package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/*This class was created to combine the outputs of two PID controllers. The sum of 
 * both the keepStraightPID and rampPID will allow the robot to slow down as it 
 * approaches a set distance all while maintaining a straight line of movement.
 */
public class StraightDistancePID implements PIDOutput {

	Robot robot;

	public PIDController keepStraightPID;

	double straightKp, straightKi, straightKd, straightSpeed;

	public StraightDistancePID(Robot robot) {
		this.robot = robot;

		keepStraightPID = new PIDController(straightKp, straightKi, straightKd,
				robot.turn.gyro, this);
		keepStraightPID.setSetpoint(0);
		keepStraightPID.setContinuous();
		keepStraightPID.setOutputRange(-1, 1);
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.straightSpeed = output;
	}

	public boolean driveDistance(double distance) {
		robot.distance.distancePID.setSetpoint(distance);
		keepStraightPID.enable();
		robot.distance.distancePID.enable();

		double leftOutput = robot.distance.rampSpeed + straightSpeed;
		double rightOutput = robot.distance.rampSpeed - straightSpeed;

		robot.drive.doubleVicsDrive(leftOutput, rightOutput);

		if (robot.distance.distancePID.onTarget()) { // Returns true when robot is
													// within tolerance
			robot.distance.distancePID.disable();
			keepStraightPID.disable();
			robot.drive.doubleVicsDrive(0, 0);
			robot.encode.resetEncoders();
			robot.turn.gyro.reset();
			
			return true;
		}
		return false;
	}
}
