package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonOne extends Command {

	Robot robot; 
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
			elevatorUpA,
			elevatorBrakeA,
			turnA,
			forwardA,
			elevatorDownA;
	
	double liftHeightA = 5, 
		   turnDegA = -90,
		   forwardDistA = 117.5,
		   dropHeightA = -5;
	
	int step = 1;
	
	String currentStep = "Not started";
	
    public AutonOne(Robot robot) {
    	this.robot = robot;
    	autonDone = elevatorUpA = elevatorBrakeA = turnA = forwardA = elevatorDownA = false;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Running Auto One");
    	SmartDashboard.putString("Current step", currentStep);
    	System.out.println("Auton One");
    	System.out.println(currentStep);
    	
    	switch (step) {
    		case 1: elevatorUpA = robot.elevate.elevatorHeight(liftHeightA);
    				nextStep(elevatorUpA);
    				currentStep = "Lifting container";
    				break;
    				
    		case 2: elevatorBrakeA = robot.elevate.elevatorBreak();
    				nextStep(elevatorBrakeA);
    				currentStep = "Braking the elevator";
    				break;
    				
    		case 3: turnA = robot.turn.PIDturnDegrees(turnDegA);
					nextStep(turnA);
					currentStep = "Turning 90 degrees counter-clockwise";
					break;
					
    		case 4: forwardA = robot.straightRamp.rampStraight(forwardDistA);
					nextStep(forwardA);
					currentStep = "Moving to the landmark";
					break;
			
    		case 5: elevatorDownA = robot.elevate.elevatorHeight(dropHeightA);
    				nextStep(elevatorDownA);
    				currentStep = "Dropping container";
					
    		case 6: autonDone = true;
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
