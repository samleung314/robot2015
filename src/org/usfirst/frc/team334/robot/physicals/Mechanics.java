package org.usfirst.frc.team334.robot.physicals;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class Mechanics {
	
	VictorSP leftVicA;
	VictorSP leftVicB;
	
	VictorSP rightVicA;
	VictorSP rightVicB;
	
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
		
		tank = new RobotDrive(leftVics, rightVics);
	}
	
	public void manualVics(double leftSpeed, double rightSpeed){
		leftVicA.set(leftSpeed);
		leftVicA.set(leftSpeed);
		
		rightVicA.set(rightSpeed);
		rightVicA.set(rightSpeed);
	}
	

}
