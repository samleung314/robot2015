package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTest extends Command {

	Robot robot;
	
	boolean distA, turnA, autonDone;
	
	double dist, deg;
	
	int step;
	
    public AutonTest(Robot robot) {
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	autonDone = false;
    	
    	dist = 24;
    	deg = 90;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Test");
    	
    	switch(step) {
	    	case 1: distA = robot.straightDist.driveDistance(dist);
	    			nextStep(distA);
		    		break;
		    		
	    	case 2: turnA = robot.turn.PIDturnDegrees(deg);
					nextStep(turnA);
		    		break;
		    		
	    	case 3: autonDone = true;
	    			break;
	    			
			default: System.out.println("Test Auton is defaulting");
					 break;	
    	}
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
