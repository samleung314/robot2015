package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonThreeTote extends Command {

	Robot robot;
	
	boolean autonDone,
			flippersStart,
			elevatorZero,
			
			//First tote
			flippersGripA,
	        liftToteA,
	        elevatorBrakeA,
	        travelForwardA,
	        
	        //Second tote
	        elevatorReleaseA,
	        flippersReleaseA, 
	        elevateDownA,
	        flippersGripB,
	        liftToteB,
	        elevatorBrakeB,
	        
	        //Third tote
	        travelForwardB,
	        elevatorReleaseB,
	        elevateDownB,
	        flippersGripC, 
	        liftToteC,
	        elevatorBrakeC,
	        
        	turnA,
	        travelForwardC,
	        
	        //Dump stack
	        elevatorReleaseC,
	        elevateDownC,
	        flippersReleaseB;
	
	double liftZero = 0,
		   liftHeightA = 16, //tote 1 lift
		   liftHeightB = 16, //tote 2 lift 
		   liftHeightC = 7, //tote 3 lift
		   forwardDistA = 80.5, //to second tote
		   forwardDistB = 80.5, //to last tote
	       forwardDistC = 112,//141.5, //to dump zone
		   turnDegreesA = -90;
	
	int step;
	
	String currentStep;
	
    public AutonThreeTote(Robot robot) {
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentStep = "Not started";
    	
    	step = 1;
    	
    	autonDone = flippersStart = elevatorZero = 
    	flippersGripA = liftToteA = elevatorBrakeA = travelForwardA = 
    	elevatorReleaseA = flippersReleaseA = elevateDownA = flippersGripB = liftToteB = elevatorBrakeB = 
    	travelForwardB = elevatorReleaseB = elevateDownB = flippersGripC = liftToteC = elevatorBrakeC = 
    	turnA = travelForwardC = 
    	elevatorReleaseC = elevateDownC = flippersReleaseB = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Three");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("---------->" + currentStep);
  
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
		/*---------------------------------Pickup first tote--------------------------------*/ 
		case 3:
			flippersGripA = robot.air.flippersAutoGrip();
			nextStep(flippersGripA);
			currentStep = "Flippers grip A";
			break;

		case 4:
			liftToteA = robot.pot.elevatePID(liftHeightA);
			nextStep(liftToteA);
			currentStep = "Lift tote A";
			break;

		case 5:
			//elevatorBrakeA = robot.elevator.elevatorLock();
			nextStep(true);
			currentStep = "Brake elevator A";
			break;

		case 6:
			travelForwardA = robot.straightDist.driveDistance(forwardDistA);
			nextStep(travelForwardA);
			currentStep = "Move to second tote";
			break;
			
		/*---------------------------------Pickup second tote--------------------------------*/ 
		case 7:
			//elevatorReleaseA = robot.elevator.elevatorAutoRelease();
			nextStep(true);
			currentStep = "Release elevator A";
			break;
			
		case 8:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 9:
			elevateDownA = robot.pot.elevatePID(liftZero);
			nextStep(elevateDownA);
			currentStep = "Drop elevator A";
			break;
			
		case 10:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 11:
			liftToteB = robot.pot.elevatePID(liftHeightB);
			nextStep(liftToteB);
			currentStep = "Lift second tote";
			break;

		case 12:
			//elevatorBrakeB = robot.elevator.elevatorLock();
			nextStep(true);
			currentStep = "Brake elevator B";
			break;
			
		/*---------------------------------Pickup third tote--------------------------------*/ 
		case 13:
			travelForwardB = robot.straightDist.driveDistance(forwardDistB);
			nextStep(travelForwardB);
			currentStep = "Move to third tote";
			break;

		case 14:
			//elevatorReleaseB = robot.elevator.elevatorAutoRelease();
			nextStep(true);
			currentStep = "Release elevator B";
			break;
			
		case 15:
			flippersGripB = robot.air.flippersAutoRelease();
			nextStep(flippersGripB);
			currentStep = "Flippers release B";
			break;

		case 16:
			elevateDownB = robot.pot.elevatePID(liftZero);
			nextStep(elevateDownB);
			currentStep = "Drop elevator B";
			break;
			
		case 17:
			flippersGripC = robot.air.flippersAutoGrip();
			nextStep(flippersGripC);
			currentStep = "Flippers grip C";
			break;

		case 18:
			liftToteC = robot.pot.elevatePID(liftHeightC);
			nextStep(liftToteC);
			currentStep = "Lifting third tote";
			break;

		case 19:
			//elevatorBrakeC = robot.elevator.elevatorLock();
			nextStep(true);
			currentStep = "Braking elevator";
			break;
			
		/*---------------------------------Move to autozone--------------------------------*/ 
		case 20:
			turnA = robot.turn.PIDturnDegrees(turnDegreesA);
			nextStep(turnA);
			currentStep = "Turning towards autozone";
			break;

		case 21:
			travelForwardC = robot.straightDist.driveDistance(forwardDistC);
			nextStep(travelForwardC);
			currentStep = "Driving into autozone";
			break;
		
		/*---------------------------------Drop stack--------------------------------*/ 
		case 22:
			//elevatorReleaseC = robot.elevator.elevatorAutoRelease();
			nextStep(true);
			currentStep = "Release elevator C";
			break;

		case 23:
			elevateDownC = robot.pot.elevatePID(liftZero);
			nextStep(elevateDownC);
			currentStep = "Dropping off tote stack";
			break;
			
		case 24:
			flippersReleaseB = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseB);
			currentStep = "Flippers release B";
			break;

		case 25:
			autonDone = true;
			currentStep = "Autonomous Three Done";
			break;

		default:
			System.out.println("Auton Three is defaulting");
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
