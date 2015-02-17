package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTwo extends Command {

	Robot robot; 
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
			flippersGripA, 
			elevateUpA,
			elevatorBrakeA,
			turnA,
			
			backwardA,
			elevatorUnlockA,
			elevatorDownA,
			flippersReleaseA, 
			backwardB,
			turnB,
			
			forwardA,
			flippersGripB, 
			elevatorUpB,
			elevatorBrakeB,
			
			backwardC,
			elevatorUnlockB,
			elevatorDownB,
			flippersReleaseB; 
	
	boolean containerALift, containerADrop, containerBLift, containerBDrop;
	
	double liftHeightA, liftHeightB, dropHeightA, dropHeightB, forwardDistA, backDistA, backDistB, backDistC, turnDegA, turnDegB;
			
	int step;
	
	String currentStep;
	
    public AutonTwo(Robot robot) {
        this.robot = robot;
        
        liftHeightA = 30;
        liftHeightB = 30;
        dropHeightA = 0;
        dropHeightB = 0;
        forwardDistA = 129.5;
        backDistA = -117.5;
        backDistB = -12;
        backDistC = -120;
        turnDegA = 90;
        turnDegB = -35;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	
    	autonDone = flippersGripA = elevateUpA = elevatorBrakeA = turnA = 
		backwardA = elevatorUnlockA = elevatorDownA = flippersReleaseA = 
    	backwardB = turnB = forwardA = flippersGripB = elevatorUpB = 
    	elevatorBrakeB = backwardC = elevatorUnlockB = elevatorDownB = flippersReleaseB = false;
    	
    	currentStep = "Not started";
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Two");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("------------> " + currentStep);
    	
    	switch (step) {
    		case 1: containerALift = robot.auto.dropLiftContainer();
		    		nextStep(containerALift);
		    		currentStep = "Lifting first container";
		    		break;
		    		
    		case 2: turnA = robot.turn.PIDturnDegrees(turnDegA);
					nextStep(turnA);
					currentStep = "Turning to face alliance station";
					break;
					
    		case 3: backwardA = robot.straightDist.driveDistance(backDistA);
					nextStep(backwardA);
					currentStep = "Moving backwards to landmark";
					break;
					
    		case 4: containerADrop = robot.auto.putDownStack();
					nextStep(containerADrop);
					currentStep = "Dropping first container";
					break;
					
    		case 5:	backwardB = robot.straightDist.driveDistance(backDistB);
					nextStep(backwardB);
					currentStep = "Backing up clear of the containter";
					break;
					
    		case 6: turnB = robot.turn.PIDturnDegrees(turnDegB);
					nextStep(turnB);
					currentStep = "Turning towards second container";
					break;
					
    		case 7: forwardA = robot.straightDist.driveDistance(forwardDistA);
					nextStep(forwardA);
					currentStep = "Driving forwards to second container";
					break;
					
    		case 8: containerBLift = robot.auto.dropLiftContainer();
    				nextStep(containerBLift);
    				currentStep = "Lifting second container";
    				break;
    				
    		case 9: backwardC = robot.straightDist.driveDistance(backDistC);
					nextStep(backwardC);
					currentStep = "Moving backwards into the autozone";
					break;
					
    		case 10: containerBDrop = robot.auto.putDownStack();
					nextStep(containerBDrop);
					currentStep = "Dropping second container";
					break;
			
    		case 11: autonDone = true;
					break;
		
    		default: System.out.println("Auton Two is defaulting");
					 break;
    	}
    	
    	/*
		switch (step) {
			case 1: flippersGripA = robot.air.flippersGrip();
					Timer.delay(0.5);
					nextStep(flippersGripA);
					currentStep = "Flippers out A";
					break; 
					
			case 2: elevateUpA = robot.elevator.elevatorHeight(liftHeightA);
					nextStep(elevateUpA);
					currentStep = "Lifting containter";
					break;
				
			case 3: elevatorBrakeA = robot.elevator.elevatorLock();
					Timer.delay(0.5);
					nextStep(elevatorBrakeA);
					currentStep = "Braking elevator";
					break;
				
			case 4: turnA = robot.turn.PIDturnDegrees(turnDegA);
					nextStep(turnA);
					currentStep = "Turning to face alliance station";
					break;
				
			case 5: backwardA = robot.straightDist.driveDistance(backDistA);
					nextStep(backwardA);
					currentStep = "Moving backwards to landmark";
					break;
				
			case 6: elevatorUnlockA = robot.elevator.elevatorRelease();
					Timer.delay(0.5);
					nextStep(elevatorUnlockA);
					currentStep = "Releasing elevator";
					break;
					
			case 7: elevatorDownA = robot.elevator.elevatorHeight(dropHeightA);
					nextStep(elevatorDownA);
					currentStep = "Dropping off container";
					break;
				
			case 8: flippersReleaseA = robot.air.flippersRelease();
					Timer.delay(0.5);
					nextStep(flippersReleaseA);
					currentStep = "Flippers release A";
					break; 
				
			case 9:	backwardB = robot.straightDist.driveDistance(backDistB);
					nextStep(backwardB);
					currentStep = "Backing up clear of the containter";
					break;
				
			case 10: turnB = robot.turn.PIDturnDegrees(turnDegB);
					nextStep(turnB);
					currentStep = "Turning towards second container";
					break;
				
			case 11: forwardA = robot.straightDist.driveDistance(forwardDistA);
					nextStep(forwardA);
					currentStep = "Driving forwards to second container";
					break;
				
			case 12: flippersGripB = robot.air.flippersRelease();
					nextStep(flippersGripB);
					currentStep = "Flippers out B";
					break; 
				
			case 13: elevatorUpB = robot.elevator.elevatorHeight(liftHeightB);
					nextStep(elevatorUpB);
					currentStep = "Lifting second containter";
					break;
				
			case 14: elevatorBrakeB = robot.elevator.elevatorLock();
					nextStep(elevatorBrakeB);
					currentStep = "Braking elevator";
					break;
				
			case 15: backwardC = robot.straightDist.driveDistance(backDistC);
					nextStep(backwardC);
					currentStep = "Moving backwards into the autozone";
					break;
				
			case 16: elevatorUnlockB = robot.elevator.elevatorRelease();
					nextStep(elevatorUnlockB);
					currentStep = "Unlocking elevator";
					break;
			
			case 17: elevatorDownB = robot.elevator.elevatorHeight(dropHeightB);
					nextStep(elevatorDownB);
					currentStep = "Dropping second container";
					break;
				
			case 18: flippersReleaseB = robot.air.flippersRelease();
					nextStep(flippersReleaseB);
					currentStep = "Flippers in B";
					break; 
				
			case 19: autonDone = true;
					break;
				
			default: System.out.println("Auton Two is defaulting");
					break;
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
