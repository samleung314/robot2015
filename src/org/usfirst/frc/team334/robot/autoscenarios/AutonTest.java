package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTest extends Command {

	Robot robot;
	
	boolean clampA, brakeA, levelOne, unbrakeA, levelZero, autonDone;
	
	double height, deg;
	
	int step;
	
    public AutonTest(Robot robot) {
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	autonDone = clampA = unbrakeA =  false;
    	
    	height = 14;
    	deg = 90;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Test");
    	
    	switch(step) {
	    	case 1: clampA = robot.air.flippersGrip();
	    			Timer.delay(0.5);
	    			nextStep(clampA);
		    		break;
		    		
	    	case 2: levelOne = robot.pot.elevatePID(height);
			    	nextStep(levelOne);
			    	break;
			    	
	    	case 3: brakeA = robot.elevator.elevatorLock();
	    			Timer.delay(0.5);
	    			nextStep(brakeA);
	    			break;
	    			
	    	case 4: unbrakeA = robot.elevator.elevatorReleaseAuto();
	    			nextStep(unbrakeA);
	    			break;
	    			
	    	case 5: levelZero = robot.pot.elevatePID(0);
	    			nextStep(levelZero);
	    			break;
	    		
	    	case 6: autonDone = true;
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
