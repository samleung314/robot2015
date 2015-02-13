package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonTest extends Command {

	Robot robot;
	
	boolean truth = true, autonDone;
	
	int step;
	
    public AutonTest(Robot robot) {
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	autonDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }
    
    private void nextStep(boolean action) {
    	if(action) {
    		step++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return autonDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
