package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.physicals.*;
import org.usfirst.frc.team334.robot.Robot;

public class Elevator {

	Robot robot;
	boolean lock = false;
	boolean moving = false;

	public Elevator(Robot robot) {
		this.robot = robot;
	}

	public void elevatorUp() // Makes Elevator go up
	{
		if (!lock) {
			robot.mech.manualVicsElevator(1);
			moving = true;
		}
	}

	public void elevatorDown() // Makes Elevator go down
	{
		if (!lock) {
			robot.mech.manualVicsElevator(-1);
			moving = true;
		}
	}

	public void elevatorStop() // Makes Elevator stay still
	{
		robot.mech.manualVicsElevator(0);
		moving = false;
	}

	public void elevatorBreak() // Engages the Dog Break in elevator
	{
		if (moving) {
			lock = true;
		}
	}

	public void elevatorRelease() // Disengages the Dog Break in elevator
	{
		if (moving) {
			lock = false;
		}
	}

}
