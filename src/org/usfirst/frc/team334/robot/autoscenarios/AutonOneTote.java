package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonOneTote extends Command {

	Robot robot; 
	
	Timer autonTime;
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
			flippersReleaseA,
			elevatorZero,
			flippersGripA,
			elevatorUpA,
			elevatorBrakeA,
			elevatorUnlockA,
			turnA,
			forwardA,
			elevatorDownA,
			flippersReleaseB,
	
			pickUpA, dropDownA;
	
	double liftZero = 0,
		   liftHeightA = 14, 
		   turnDegA = 90,
		   forwardDistA = 141.5,
		   dropHeightA = -14;
	
	int step;
	
	String currentStep = "Not started";
	
    public AutonOneTote(Robot robot) {
    	this.robot = robot;
    	
    	autonTime = new Timer();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	step = 1;
    	
    	autonDone = flippersReleaseA = elevatorZero = flippersGripA = elevatorUpA = elevatorBrakeA = 
    	elevatorUnlockA = turnA = forwardA = elevatorDownA = flippersReleaseB = false;
    	
    	pickUpA = dropDownA = false;
    	
    	autonTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto One");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("------------> " + currentStep);
    	
    	/*
		switch (step) {
		case 1:
			pickUpA = robot.auto.dropLiftTote();
			nextStep(pickUpA);
			System.out.println("pickupA time: " + autonTime.get());
			currentStep = "Pick up tote";
			break;

		case 2:
			turnA = robot.turn.PIDturnDegrees(turnDegA);
			nextStep(turnA);
			System.out.println("turnA time: " + autonTime.get());
			currentStep = "Turning 90 degrees";
			break;

		case 3:
			forwardA = robot.straightDist.driveDistance(forwardDistA);
			nextStep(forwardA);
			System.out.println("forwardA time: " + autonTime.get());
			currentStep = "Moving to the landmark";
			break;

		case 4:
			dropDownA = robot.auto.putDownStack();
			System.out.println("dropdownA time: " + autonTime.get());
			nextStep(dropDownA);
			currentStep = "Drop down tote";
			break;

		case 5:
			autonDone = true;
			System.out.println("Total time: " + autonTime.get());
			autonTime.stop();
			autonTime.reset();
			break;

		default:
			System.out.println("Auton One is defaulting");
			break;
		}
		*/
		
    	switch (step) {
    		case 1: flippersReleaseA = robot.air.flippersRelease();
					Timer.delay(0.5);
					nextStep(flippersReleaseA);
					currentStep = "Flippers release A";
					break;
    	
	    	case 2: elevatorZero = robot.pot.elevatePID(liftZero);
					nextStep(elevatorZero);
					currentStep = "Elevator zero";
					break;
					
    		case 3: flippersGripA = robot.air.flippersGrip();
    				Timer.delay(0.5);
    				nextStep(flippersGripA);
    				currentStep = "Flippers grip A";
    				break;
    				
    		case 4: elevatorUpA = robot.pot.elevatePID(liftHeightA);
					nextStep(elevatorUpA);
					currentStep = "Lifting container A";
					break;
    				
    		case 5: elevatorBrakeA = robot.elevator.elevatorLock();
    				nextStep(elevatorBrakeA);
    				currentStep = "Braking the elevator";
    				break;
    				
    		case 6: turnA = robot.turn.PIDturnDegrees(turnDegA);
					nextStep(turnA);
					currentStep = "Turning 90 degrees";
					break;
					
    		case 7: forwardA = robot.straightDist.driveDistance(forwardDistA);
					nextStep(forwardA);
					currentStep = "Moving to the landmark";
					break;
			
    		case 8: elevatorUnlockA = robot.elevator.elevatorRelease();
    				Timer.delay(0.5);
					nextStep(elevatorUnlockA);
					currentStep = "Unlocking the elevator";
					break;
			
    		case 9: elevatorDownA = robot.pot.elevatePID(liftZero);
    				nextStep(elevatorDownA);
    				currentStep = "Dropping container";
    				break;
    				
    		case 10: flippersReleaseB = robot.air.flippersRelease();
					nextStep(flippersReleaseB);
					currentStep = "Flippers in";
					break;
					
    		case 11: autonDone = true;
					 break;	
					
			default: System.out.println("Auton One is defaulting");
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
