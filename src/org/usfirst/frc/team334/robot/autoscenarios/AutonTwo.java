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
			elevateUpA,
			elevatorBrakeA,
			turnA,
			backwardA,
			elevatorUnlockA,
			elevatorDownA,
			backwardB,
			turnB,
			forwardA,
			elevatorUpB,
			elevatorBrakeB,
			backwardC,
			elevatorUnlockB,
			elevatorDownB;
	
	double liftHeightA, liftHeightB, dropHeightA, dropHeightB, forwardDistA, backDistA, backDistB, backDistC, turnDegA, turnDegB;
			
	int step = 1;
	
	String currentStep = "Not started";
	
    public AutonTwo(Robot robot) {
        this.robot = robot;
        
        autonDone = elevateUpA = elevatorBrakeA = turnA = backwardA = elevatorUnlockA = elevatorDownA = 
        backwardB = turnB = forwardA = elevatorUpB = elevatorBrakeB = backwardC = elevatorUnlockB = elevatorDownB = false;
        
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Running Auto Two");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("Auton Two");
    	System.out.println("----------> currentStep");
    	
		switch (step) {
			case 1:
				elevateUpA = robot.elevate.elevatorHeight(liftHeightA);
				nextStep(elevateUpA);
				currentStep = "Lifting containter";
				break;
				
			case 2:
				elevatorBrakeA = robot.elevate.elevatorBreak();
				nextStep(elevatorBrakeA);
				currentStep = "Braking elevator";
				break;
				
			case 3:
				turnA = robot.turn.PIDturnDegrees(turnDegA);
				nextStep(turnA);
				currentStep = "Turning to face alliance station";
				break;
				
			case 4:
				backwardA = robot.straightRamp.rampStraight(backDistA);
				nextStep(backwardA);
				currentStep = "Moving backwards to landmark";
				break;
				
			case 5: 
				elevatorUnlockA = robot.elevate.elevatorRelease();
				nextStep(elevatorUnlockA);
				currentStep = "Releasing elevator";
				break;
					
			case 6:
				elevatorDownA = robot.elevate.elevatorHeight(dropHeightA);
				nextStep(elevatorDownA);
				currentStep = "Dropping off container";
				break;
				
			case 7:
				backwardB = robot.straightRamp.rampStraight(backDistB);
				nextStep(backwardB);
				currentStep = "Backing up clear of the containter";
				break;
				
			case 8:
				turnB = robot.turn.PIDturnDegrees(turnDegB);
				nextStep(turnB);
				currentStep = "Turning towards second container";
				break;
				
			case 9:
				forwardA = robot.straightRamp.rampStraight(forwardDistA);
				nextStep(forwardA);
				currentStep = "Driving forwards to second container";
				break;
				
			case 10:
				elevatorUpB = robot.elevate.elevatorHeight(liftHeightB);
				nextStep(elevatorUpB);
				currentStep = "Lifting second containter";
				break;
				
			case 11:
				elevatorBrakeB = robot.elevate.elevatorBreak();
				nextStep(elevatorBrakeB);
				currentStep = "Braking elevator";
				break;
				
			case 12:
				backwardC = robot.straightRamp.rampStraight(backDistC);
				nextStep(backwardC);
				currentStep = "Moving backwards into the autozone";
				break;
				
			case 13:
				elevatorUnlockB = robot.elevate.elevatorRelease();
				nextStep(elevatorUnlockB);
				currentStep = "Unlocking elevator";
				break;
			
			case 14:
				elevatorDownB = robot.elevate.elevatorHeight(dropHeightB);
				nextStep(elevatorDownB);
				currentStep = "Dropping second container";
				break;
			
			case 15:
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
