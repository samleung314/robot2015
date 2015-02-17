package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTwoContainer extends Command {

	Robot robot; 
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
			flippersStart,
			elevatorZero,
			
			flippersGripA, 
			elevateUpA,
			elevatorBrakeA,
			turnA,
			
			backwardA,
			elevatorUnlockA,
			flippersReleaseA, 
			elevatorDownA,
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
	
	double liftHeightA = 32, 
			liftHeightB = 32, 
			liftZero = 0, 
			forwardDistA = 129.5, 
			backDistA = -117.5,
			backDistB = -12, 
			backDistC = -120, 
			turnDegA = 90, 
			turnDegB = -35;
			
	int step;
	
	String currentStep;
	
    public AutonTwoContainer(Robot robot) {
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	
    	autonDone = flippersStart = elevatorZero = 
    	flippersGripA = elevateUpA = elevatorBrakeA = turnA = 
		backwardA = elevatorUnlockA = flippersReleaseA = elevatorDownA = backwardB = turnB = 
		forwardA = flippersGripB = elevatorUpB = elevatorBrakeB = 
		backwardC = elevatorUnlockB = elevatorDownB = flippersReleaseB = false;
    	
    	currentStep = "Not started";
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Two");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("------------> " + currentStep);
    	
		switch (step) {
		/*---------------------------------Starting Position--------------------------------*/
		case 1:
			flippersStart = robot.air.flippersAutoRelease();
			nextStep(flippersStart);
			currentStep = "Flippers start";
			break;

		case 2:
			elevatorZero = robot.pot.elevatePID(liftZero);
			nextStep(elevatorZero);
			currentStep = "Elevator zero";
			break;
		/*---------------------------------Pickup first container---------------------------*/
		case 3:
			flippersGripA = robot.air.flippersAutoGrip();
			nextStep(flippersGripA);
			currentStep = "Flippers grip A";
			break;

		case 4:
			elevateUpA = robot.pot.elevatePID(liftHeightA);
			nextStep(elevateUpA);
			currentStep = "Lifting first containter";
			break;

		case 5:
			elevatorBrakeA = robot.elevator.elevatorLock();
			nextStep(elevatorBrakeA);
			currentStep = "Braking elevator";
			break;

		case 6:
			turnA = robot.turn.PIDturnDegrees(turnDegA);
			nextStep(turnA);
			currentStep = "Turning to face alliance station";
			break;

		/*---------------------------------Drop first container---------------------------*/
		case 7:
			backwardA = robot.straightDist.driveDistance(backDistA);
			nextStep(backwardA);
			currentStep = "Backup to autozone A";
			break;

		case 8:
			elevatorUnlockA = robot.elevator.elevatorAutoRelease();
			nextStep(elevatorUnlockA);
			currentStep = "Releasing elevator A";
			break;

		case 9:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 10:
			elevatorDownA = robot.pot.elevatePID(liftZero);
			nextStep(elevatorDownA);
			currentStep = "Dropping off container";
			break;

		case 11:
			backwardB = robot.straightDist.driveDistance(backDistB);
			nextStep(backwardB);
			currentStep = "Backing up clear of the containter";
			break;

		case 12:
			turnB = robot.turn.PIDturnDegrees(turnDegB);
			nextStep(turnB);
			currentStep = "Turning to second container";
			break;
		
	    /*---------------------------------Pickup second container---------------------------*/
		case 13:
			forwardA = robot.straightDist.driveDistance(forwardDistA);
			nextStep(forwardA);
			currentStep = "Driving forwards to second container";
			break;

		case 14:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 15:
			elevatorUpB = robot.pot.elevatePID(liftHeightB);
			nextStep(elevatorUpB);
			currentStep = "Lifting second containter";
			break;

		case 16:
			elevatorBrakeB = robot.elevator.elevatorLock();
			nextStep(elevatorBrakeB);
			currentStep = "Braking elevator";
			break;

		/*---------------------------------Drop second container---------------------------*/
		case 17:
			backwardC = robot.straightDist.driveDistance(backDistC);
			nextStep(backwardC);
			currentStep = "Backup to autozone B";
			break;

		case 18:
			elevatorUnlockB = robot.elevator.elevatorAutoRelease();
			nextStep(elevatorUnlockB);
			currentStep = "Unlocking elevator B";
			break;

		case 19:
			flippersReleaseB = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseB);
			currentStep = "Flippers release B";
			break;

		case 20:
			elevatorDownB = robot.pot.elevatePID(liftZero);
			nextStep(elevatorDownB);
			currentStep = "Dropping second container";
			break;

		case 21:
			autonDone = true;
			break;

		default:
			System.out.println("Auton Two is defaulting");
			break;
		}
		
		/*switch (step) {
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
	} */
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
