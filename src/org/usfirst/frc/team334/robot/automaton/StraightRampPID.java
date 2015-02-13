package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/*This class was created to combine the outputs of two PID controllers. The sum of 
 * both the keepStraightPID and rampPID will allow the robot to slow down as it 
 * approaches a set distance all while maintaining a straight line of movement.
*/
public class StraightRampPID implements PIDOutput{

	Robot robot;
	
	public PIDController keepStraightPID;
	
	double straightKp, straightKi, straightKd, straightSpeed;
	
	public StraightRampPID(Robot robot) {
		this.robot = robot;
		
		keepStraightPID = new PIDController(straightKp, straightKi, straightKd, robot.turn.gyro, this);
		keepStraightPID.setSetpoint(0);
		keepStraightPID.setContinuous();
		keepStraightPID.setOutputRange(-1, 1);
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.straightSpeed = output;
	}
	
	public boolean rampStraight(double distance) {
		robot.distance.rampPID.setSetpoint(distance);
		keepStraightPID.enable();
		robot.distance.rampPID.enable();
		
		double leftOutput = robot.distance.rampSpeed + straightSpeed;
		double rightOutput = robot.distance.rampSpeed - straightSpeed;
		
		robot.drive.doubleVicsDrive(leftOutput, rightOutput);
		
		if(robot.distance.rampPID.onTarget()){ //Returns true when robot is within tolerance
			robot.drive.doubleVicsDrive(0, 0);
			return true;
		} 
		else {
			return false;
		}
	}
}
