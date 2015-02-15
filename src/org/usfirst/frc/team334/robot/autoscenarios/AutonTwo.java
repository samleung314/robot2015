package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTwo extends Command {

	Robot robot; 
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
			flippersOutA, 
			elevateUpA,
			elevatorBrakeA,
			turnA,
			
			backwardA,
			elevatorUnlockA,
			elevatorDownA,
			flippersInA, 
			backwardB,
			turnB,
			
			forwardA,
			flippersOutB, 
			elevatorUpB,
			elevatorBrakeB,
			
			backwardC,
			elevatorUnlockB,
			elevatorDownB,
			flippersInB; 
	
	double liftHeightA, liftHeightB, dropHeightA, dropHeightB, forwardDistA, backDistA, backDistB, backDistC, turnDegA, turnDegB;
			
	int step;
	
	String currentStep;
	
    public AutonTwo(Robot robot) {
        this.robot = robot;
        
        liftHeightA = 0;
        liftHeightB = 0;
        dropHeightA = 0;
        dropHeightB = 0;
        forwardDistA = 0;
        backDistA = 0;
        backDistB = 0;
        backDistC = 0;
        turnDegA = 0;
        turnDegB = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	
    	autonDone = flippersOutA = elevateUpA = elevatorBrakeA = turnA = 
		backwardA = elevatorUnlockA = elevatorDownA = flippersInA = 
    	backwardB = turnB = forwardA = flippersOutB = elevatorUpB = 
    	elevatorBrakeB = backwardC = elevatorUnlockB = elevatorDownB = flippersInB = false;
    	
    	currentStep = "Not started";
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Two");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("------------> " + currentStep);
    	
		switch (step) {
			case 1: flippersOutA = robot.air.flippersOut();
					nextStep(flippersOutA);
					currentStep = "Flippers out A";
					break; 
					
			case 2:
				elevateUpA = robot.elevate.elevatorHeight(liftHeightA);
				nextStep(elevateUpA);
				currentStep = "Lifting containter";
				break;
				
			case 3:
				elevatorBrakeA = robot.elevate.elevatorBreak();
				nextStep(elevatorBrakeA);
				currentStep = "Braking elevator";
				break;
				
			case 4:
				turnA = robot.turn.PIDturnDegrees(turnDegA);
				nextStep(turnA);
				currentStep = "Turning to face alliance station";
				break;
				
			case 5:
				backwardA = robot.straightDist.driveDistance(backDistA);
				nextStep(backwardA);
				currentStep = "Moving backwards to landmark";
				break;
				
			case 6: 
				elevatorUnlockA = robot.elevate.elevatorRelease();
				nextStep(elevatorUnlockA);
				currentStep = "Releasing elevator";
				break;
					
			case 7:
				elevatorDownA = robot.elevate.elevatorHeight(dropHeightA);
				nextStep(elevatorDownA);
				currentStep = "Dropping off container";
				break;
				
			case 8: flippersInA = robot.air.flippersOut();
					nextStep(flippersInA);
					currentStep = "Flippers in A";
					break; 
				
			case 9:
				backwardB = robot.straightDist.driveDistance(backDistB);
				nextStep(backwardB);
				currentStep = "Backing up clear of the containter";
				break;
				
			case 10:
				turnB = robot.turn.PIDturnDegrees(turnDegB);
				nextStep(turnB);
				currentStep = "Turning towards second container";
				break;
				
			case 11:
				forwardA = robot.straightDist.driveDistance(forwardDistA);
				nextStep(forwardA);
				currentStep = "Driving forwards to second container";
				break;
				
			case 12: flippersOutB = robot.air.flippersOut();
					nextStep(flippersOutB);
					currentStep = "Flippers out B";
					break; 
				
			case 13:
				elevatorUpB = robot.elevate.elevatorHeight(liftHeightB);
				nextStep(elevatorUpB);
				currentStep = "Lifting second containter";
				break;
				
			case 14:
				elevatorBrakeB = robot.elevate.elevatorBreak();
				nextStep(elevatorBrakeB);
				currentStep = "Braking elevator";
				break;
				
			case 15:
				backwardC = robot.straightDist.driveDistance(backDistC);
				nextStep(backwardC);
				currentStep = "Moving backwards into the autozone";
				break;
				
			case 16:
				elevatorUnlockB = robot.elevate.elevatorRelease();
				nextStep(elevatorUnlockB);
				currentStep = "Unlocking elevator";
				break;
			
			case 17:
				elevatorDownB = robot.elevate.elevatorHeight(dropHeightB);
				nextStep(elevatorDownB);
				currentStep = "Dropping second container";
				break;
				
			case 18: flippersInB = robot.air.flippersOut();
				nextStep(flippersInB);
				currentStep = "Flippers in B";
				break; 
				
			case 19:
				autonDone = true;
				break;
				
			default:
				System.out.println("Auton Two is defaulting");
				break;
		}
		
		
    	/*if(!elevateUpA) {
    		elevateUpA = robot.elevate.elevatorHeight(liftHeightA);
    	} 	
    	if(elevateUpA && !elevatorBrakeA) {
    		elevatorBrakeA = robot.elevate.elevatorBreak();
    	}
    	if(elevatorBrakeA && !backwardA) {
    		robot.straightRamp.rampStraight(backDistA);
    	}
    	if(backwardA && !turnA) {
    		robot.turn.PIDturnDegrees(turnDegA);
    	}
    	if(turnA && !backwardB) {
    		robot.straightRamp.rampStraight(backDistA);
    	}
    	if(backwardB && !elevatorDownA) {
    		robot.elevate.elevatorHeight(dropHeightA);
    	}
    	if(elevatorDownA && !backwardC) {
    		robot.straightRamp.rampStraight(backDistB);
    	}
    	if(backwardC && !turnB) {
    		
    	}
    	if(turnB && !forwardA) {
    		
    	}
    	if(forwardA && !elevatorUpB) {
    		
    	}
    	if(elevatorUpB && !elevatorBrakeB) {
    		
    	}
    	if(elevatorBrakeB && !backwardD) {
    		
    	}
    	if(backwardD && !dropContainer) {
    		
    	}
    	if(dropContainer && !autonDone) {
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
