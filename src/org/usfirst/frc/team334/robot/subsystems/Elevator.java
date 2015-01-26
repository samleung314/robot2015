package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Robot;

public class Elevator {

	Robot robot;
//	boolean lock = false;
//	boolean moving = false;
//	double desiredpot = 1;
//	double maxpot = 2;
//	double minpot = 0;
//
	public Elevator(Robot robot) {
		this.robot = robot;
	}
	
	public void manualVicsElevator(double Speed) {
		robot.mech.elevatorVicA.set(Speed);
		robot.mech.elevatorVicB.set(Speed);
	}
//
//	public void elevatorStop() // Makes Elevator stay still
//	{
//		manualVicsElevator(0);
//	}
//
//	public void elevatorBreak() // Engages the Dog Break in elevator
//	{
//		if (!moving) {
//			lock = true;
//		}
//	}
//
//	public void elevatorRelease() // Disengages the Dog Break in elevator
//	{
//		if (!moving) {
//			lock = false;
//		}
//	}
//
//	public void elevatorUp() {
//		if (robot.sensors.elevatorPot.get() < desiredpot
//				&& robot.sensors.elevatorPot.get() < maxpot && !lock) {
//			manualVicsElevator(-1);
//		}
//	}
//
//	public void elevatorDown() // Makes Elevator go down
//	{
//		if (robot.sensors.elevatorPot.get() > desiredpot
//				&& robot.sensors.elevatorPot.get() > maxpot && !lock) {
//			manualVicsElevator(1);
//		}
//	}
//
//	public void manualVicsElevator(double Speed) {
//		if (Speed != 0) {
//			moving = true;
//		} else if (Speed == 0) {
//			moving = false;
//		}
//		robot.mech.elevatorVicA.set(Speed);
//		robot.mech.elevatorVicB.set(Speed);
//	}

}
