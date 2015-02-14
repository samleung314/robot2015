package org.usfirst.frc.team334.robot.autoscenarios;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.Robot;

/**
 *
 */
public class BenTest extends Command {

	int step = 1;
	Robot robot;
	boolean back, turn, forward, autonDone;
	double moveBack = -10, angle = 90, moveForward = 150;

	public BenTest() {
		this.robot = robot;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		step = 1;
		back = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (step) {
		case 1:
			back = robot.straightDist.driveDistance(moveBack);
			nextStep(back);
			break;
		case 2:
			turn = robot.turn.PIDturnDegrees(angle);
			nextStep(turn);
			break;
		case 3:
			forward = robot.straightDist.driveDistance(moveForward);
			nextStep(forward);
			break;
		case 4:
			
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return autonDone;
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
