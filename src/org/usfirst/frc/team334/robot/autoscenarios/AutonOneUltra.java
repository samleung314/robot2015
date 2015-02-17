package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonOneUltra extends Command {

	Robot robot; 
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
	        ultraForward,
			elevatorUpA,
			elevatorBrakeA,
			turnA,
			forwardA,
			elevatorDownA;
	
	double startRamp =30,
		   tolerance = .75,   
	       liftHeightA = 5, 
		   turnDegA = -90,
		   forwardDistA = 117.5,
		   dropHeightA = -5;
	
	int step;
	
	String currentStep = "Not started";
	
    public AutonOneUltra(Robot robot) {
    	this.robot = robot;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	autonDone = elevatorUpA = elevatorBrakeA = turnA = forwardA = elevatorDownA = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Running Auto One");
    	SmartDashboard.putString("Current step", currentStep);
    	System.out.println("------------> " + currentStep);
    	
    	switch (step) {
    	    case 1: ultraForward = robot.ultrasonic.UltraRampAuto(startRamp,tolerance);
    	    	    nextStep(ultraForward);
    	    	    currentStep = "Moving to Container";
    	    	    break;
    	    	    
    		case 2: elevatorUpA = robot.pot.elevatePID(liftHeightA);
    				nextStep(elevatorUpA);
    				currentStep = "Lifting container";
    				break;
    				
    		case 3: elevatorBrakeA = robot.elevator.elevatorLock();
    				nextStep(elevatorBrakeA);
    				currentStep = "Braking the elevator";
    				break;
    				
    		case 4: turnA = robot.turn.PIDturnDegrees(turnDegA);
					nextStep(turnA);
					currentStep = "Turning 90 degrees counter-clockwise";
					break;
					
    		case 5: forwardA = robot.straightDist.driveDistance(forwardDistA);
					nextStep(forwardA);
					currentStep = "Moving to the landmark";
					break;
			
    		case 6: elevatorDownA = robot.pot.elevatePID(dropHeightA);
    				nextStep(elevatorDownA);
    				currentStep = "Dropping container";
    				break;
					
    		case 7: autonDone = true;
					break;	
					
			default: System.out.println("Auton One is defaulting");
					break;
    	}
    	
    	/*if(!elevatorUpA) {
		elevatorUpA = robot.elevate.elevatorHeight(liftHeight);
		}
		if(elevatorUpA && !elevatorBrakeA) {
			elevatorBrakeA = robot.elevate.elevatorBreak();
		}
		if (elevatorBrakeA && !turnA) {
			robot.turn.PIDturnDegrees(turnDegrees);
		}
		if(turnA && !forwardA) {
			forwardA = robot.straightRamp.rampStraight(travelDistance);
		}
		if(forwardA) {
			autonDone = true;
		}*/
    }
    
    private void nextStep(boolean action) {
    	if (action) {
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
