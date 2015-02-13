package org.usfirst.frc.team334.robot.autoscenarios;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.Robot;

/**
 *
 */
public class BenTest extends Command {

	int step = 1;
	Robot robot;
	boolean back = false;

	public BenTest() {
		this.robot = robot;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		step = 1;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (step) {
		case 1:
			back = robot.straightDist.driveDistance(-10);
			nextStep(back);
			break;

		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	private void nextStep(boolean action) {
		if (action) {
			step++;
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
