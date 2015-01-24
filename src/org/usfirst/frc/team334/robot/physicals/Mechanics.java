package org.usfirst.frc.team334.robot.physicals;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class Mechanics {
	
	VictorSP leftVicA;
	VictorSP leftVicB;
	
	VictorSP rightVicA;
	VictorSP rightVicB;
	
	VictorSP elevatorVicA;
	VictorSP elevatorVicB;
	
	public DoubleVics leftVics;
	public DoubleVics rightVics;
	
	public RobotDrive tank;
	
	public Mechanics(){
		leftVicA = new VictorSP(Constants.leftVictorA);
		leftVicB = new VictorSP(Constants.leftVictorB);
		
		rightVicA = new VictorSP(Constants.rightVictorA);
		rightVicB = new VictorSP(Constants.rightVictorB);
		
		leftVics = new DoubleVics(leftVicA, leftVicB);
		rightVics = new DoubleVics(rightVicA, rightVicB);
		
		elevatorVicA = new VictorSP(Constants.elevatorVictorA);
		elevatorVicB = new VictorSP(Constants.elevatorVictorB);
		
		tank = new RobotDrive(leftVics, rightVics);
	}
	
	public void manualVicsDrive(double leftSpeed, double rightSpeed){
		leftVicA.set(leftSpeed);
		leftVicB.set(leftSpeed);
		
		rightVicA.set(rightSpeed);
		rightVicB.set(rightSpeed);
	}
	
	public void manualVicsElevator(double Speed)
	{
		elevatorVicA.set(Speed);
		elevatorVicB.set(Speed);
	}
	

}
