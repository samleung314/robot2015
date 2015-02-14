package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class KevinAuton extends Command {

	Robot robot;

	boolean turned, drove, autonDone;

	int turnDegrees = 90, driveDistance = 5;

	int step;

	public KevinAuton(Robot robot) {
		this.robot = robot;
	}

	@Override
	protected void initialize() {
		turned = false;
		drove = false;
		step = 1;
	}

	@Override
	protected void execute() {
		switch (step) {

		case 1:
			turned = robot.turn.PIDturnDegrees(turnDegrees);
			nextStep(turned);
			break;

		case 2:
			drove = robot.straightDist.driveDistance(driveDistance);
			nextStep(drove);
			break;

		case 3:
			autonDone = true;
			nextStep(true);
			break;
		}
	}

	public void nextStep(boolean toggle) {
		if (toggle) {
			step++;
		}
	}

	@Override
	protected boolean isFinished() {
		return autonDone;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
