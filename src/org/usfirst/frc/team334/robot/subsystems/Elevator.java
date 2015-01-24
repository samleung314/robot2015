package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Robot;

public class Elevator {

	Robot robot;
	boolean lock = false;
	boolean moving = false;
	double desiredpot = 1;

	public Elevator(Robot robot) {
		this.robot = robot;
	}

	public void elevatorUp() // Makes Elevator go up
	{
		if (!lock) {
			manualVicsElevator(1);
			moving = true;
		}
	}

	public void elevatorDown() // Makes Elevator go down
	{
		if (!lock) {
			manualVicsElevator(-1);
			moving = true;
		}
	}

	public void elevatorStop() // Makes Elevator stay still
	{
		manualVicsElevator(0);
		moving = false;
	}

	public void elevatorBreak() // Engages the Dog Break in elevator
	{
		if (!moving) {
			lock = true;
		}
	}

	public void elevatorRelease() // Disengages the Dog Break in elevator
	{
		if (!moving) {
			lock = false;
		}
	}
	
	public void elevaterUp()
	{
		if(robot.sensors.elevatorPot.get()<desiredpot)
		{
			
		}
		
	}
	
	public void manualVicsElevator(double Speed)
	{
		robot.mech.elevatorVicA.set(Speed);
		robot.mech.elevatorVicB.set(Speed);
	}

}
