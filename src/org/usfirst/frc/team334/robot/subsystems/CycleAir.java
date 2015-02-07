package org.usfirst.frc.team334.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.Robot;

/**
 *
 */
public class CycleAir extends Command {

    Robot robot;

	public CycleAir(Robot robot) {
        // Use requires() here to declare subsystem dependencies
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	robot.air.cycleThrough();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
