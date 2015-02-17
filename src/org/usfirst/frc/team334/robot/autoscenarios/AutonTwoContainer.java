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
			forwardDistA = 165, 
			backDistA = -155,
			backDistB = -12, 
			backDistC = -165, 
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
    	
    	//withBrake();
    	noBrake();
    }
    
    private void noBrake() {
    	switch (step) {
		/*---------------------------------Starting Position--------------------------------*/
		case 1:
			flippersStart = robot.air.flippersAutoRelease();
			nextStep(flippersStart);
			currentStep = "Flippers start";
			break;

		case 2:
			elevatorZero = robot.pot.elevatePIDLock(liftZero);
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
			elevateUpA = robot.pot.elevatePIDLock(liftHeightA);
			nextStep(elevateUpA);
			currentStep = "Lifting first containter";
			break;

		case 5:
			turnA = robot.turn.PIDturnDegrees(turnDegA);
			nextStep(turnA);
			currentStep = "Turning to face alliance station";
			break;

		/*---------------------------------Drop first container---------------------------*/
		case 6:
			backwardA = robot.straight.driveDistance(backDistA);
			nextStep(backwardA);
			currentStep = "Backup to autozone A";
			break;

		case 7:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 8:
			elevatorDownA = robot.pot.elevatePIDLock(liftZero);
			nextStep(elevatorDownA);
			currentStep = "Dropping off container";
			break;

		case 9:
			backwardB = robot.straight.driveDistance(backDistB);
			nextStep(backwardB);
			currentStep = "Backing up clear of the containter";
			break;

		case 10:
			turnB = robot.turn.PIDturnDegrees(turnDegB);
			nextStep(turnB);
			currentStep = "Turning to second container";
			break;
		
	    /*---------------------------------Pickup second container---------------------------*/
		case 11:
			forwardA = robot.straight.driveDistance(forwardDistA);
			nextStep(forwardA);
			currentStep = "Driving forwards to second container";
			break;

		case 12:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 13:
			elevatorUpB = robot.pot.elevatePIDLock(liftHeightB);
			nextStep(elevatorUpB);
			currentStep = "Lifting second containter";
			break;

		/*---------------------------------Drop second container---------------------------*/
		case 14:
			backwardC = robot.straight.driveDistance(backDistC);
			nextStep(backwardC);
			currentStep = "Backup to autozone B";
			break;

		case 15:
			flippersReleaseB = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseB);
			currentStep = "Flippers release B";
			break;

		case 16:
			elevatorDownB = robot.pot.elevatePIDLock(liftZero);
			nextStep(elevatorDownB);
			currentStep = "Dropping second container";
			break;

		case 17:
			autonDone = true;
			break;

		default:
			System.out.println("Auton Two is defaulting");
			break;
		}
    }
    
    private void withBrake() {
    	switch (step) {
		/*---------------------------------Starting Position--------------------------------*/
		case 1:
			flippersStart = robot.air.flippersAutoRelease();
			nextStep(flippersStart);
			currentStep = "Flippers start";
			break;

		case 2:
			elevatorZero = robot.pot.elevatePIDLock(liftZero);
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
			elevateUpA = robot.pot.elevatePIDLock(liftHeightA);
			nextStep(elevateUpA);
			currentStep = "Lifting first containter";
			break;

		case 5:
			turnA = robot.turn.PIDturnDegrees(turnDegA);
			nextStep(turnA);
			currentStep = "Turning to face alliance station";
			break;

		/*---------------------------------Drop first container---------------------------*/
		case 6:
			backwardA = robot.straight.driveDistance(backDistA);
			nextStep(backwardA);
			currentStep = "Backup to autozone A";
			break;

		case 7:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 8:
			elevatorDownA = robot.pot.elevatePIDLock(liftZero);
			nextStep(elevatorDownA);
			currentStep = "Dropping off container";
			break;

		case 9:
			backwardB = robot.straight.driveDistance(backDistB);
			nextStep(backwardB);
			currentStep = "Backing up clear of the containter";
			break;

		case 10:
			turnB = robot.turn.PIDturnDegrees(turnDegB);
			nextStep(turnB);
			currentStep = "Turning to second container";
			break;
		
	    /*---------------------------------Pickup second container---------------------------*/
		case 11:
			forwardA = robot.straight.driveDistance(forwardDistA);
			nextStep(forwardA);
			currentStep = "Driving forwards to second container";
			break;

		case 12:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 13:
			elevatorUpB = robot.pot.elevatePIDLock(liftHeightB);
			nextStep(elevatorUpB);
			currentStep = "Lifting second containter";
			break;

		/*---------------------------------Drop second container---------------------------*/
		case 14:
			backwardC = robot.straight.driveDistance(backDistC);
			nextStep(backwardC);
			currentStep = "Backup to autozone B";
			break;

		case 15:
			flippersReleaseB = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseB);
			currentStep = "Flippers release B";
			break;

		case 16:
			elevatorDownB = robot.pot.elevatePIDLock(liftZero);
			nextStep(elevatorDownB);
			currentStep = "Dropping second container";
			break;

		case 17:
			autonDone = true;
			break;

		default:
			System.out.println("Auton Two is defaulting");
			break;
		}
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
