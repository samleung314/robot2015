package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonThree extends Command {

	Robot robot;
	
	boolean autonDone,
			flippersOutA,
	        liftToteA,
	        elevatorBrakeA,
	        travelForwardA,
	        elevatorReleaseA,
	        
	        elevateDownA,
	        flippersInA, 
	        liftToteB,
	        elevatorBrakeB,
	        travelForwardB,
	        elevatorReleaseB,
	        
	        elevateDownB,
	        flippersOutB, 
	        liftToteC,
	        elevatorBrakeC,
	        
        	turnA,
	        travelForwardC,
	        elevatorReleaseC,
	        elevateDownC,
	        flippersInB; 
	
	double liftHeightA = 0, //tote 1 lift
		   liftHeightB = 0, //tote 2 lift 
		   liftHeightC = 0, //tote 3 lift
		   dropHeightA = 0,
	       dropHeightB = 0,
	       dropHeightC = 0,
		   forwardDistA = 117.5, //to second tote
		   forwardDistB = 117.5, //to last tote
	       forwardDistC = 117.5, //to dump zone
		   turnDegreesA= 90;
	
	int step;
	
	String currentStep;
	
    public AutonThree(Robot robot) {
        this.robot = robot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentStep = "Not started";
    	
    	step = 1;
    	
    	autonDone = flippersOutA = liftToteA = elevatorBrakeA = travelForwardA = 
    	elevatorReleaseA = elevateDownA = flippersInA = liftToteB = elevatorBrakeB = 
    	travelForwardB = elevatorReleaseB = elevateDownB = flippersOutB = liftToteC = 
    	elevatorBrakeC = turnA = travelForwardC = elevatorReleaseC = elevateDownC = flippersInB = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Three");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("---------->" + currentStep);
    	
    	switch (step) {
    	case 1:
    		flippersOutA = robot.air.flippersOut();
			nextStep(flippersOutA);
			currentStep = "Flippers out A";
			break;
			
		case 2:
			liftToteA = robot.elevate.elevatorHeight(liftHeightA);
			nextStep(liftToteA);
			currentStep = "Lifting first tote";
			break;
			
		case 3:
			elevatorBrakeA = robot.elevate.elevatorLock();
			nextStep(elevatorBrakeA);
			currentStep = "Braking elevator A";
			break;
			
		case 4:
			travelForwardA = robot.straightDist.driveDistance(forwardDistA);
			nextStep(travelForwardA);
			currentStep = "Moving to second tote";
			break;
			
		case 5:
			elevatorReleaseA = robot.elevate.elevatorRelease();
			nextStep(elevatorReleaseA);
			currentStep = "Releasing elevator A";
			break;
			
		case 6: 
			elevateDownA = robot.elevate.elevatorHeight(dropHeightA);
			nextStep(elevateDownA);
			currentStep = "Dropping elevator A";
			break;
			
		case 7:
    		flippersInA = robot.air.flippersIn();
			nextStep(flippersInA);
			currentStep = "Flippers in A";
			break;
				
		case 8:
			liftToteB = robot.elevate.elevatorHeight(liftHeightB);
			nextStep(liftToteB);
			currentStep = "Lifting second tote";
			break;
			
		case 9:
			elevatorBrakeB = robot.elevate.elevatorLock();
			nextStep(elevatorBrakeB);
			currentStep = "Braking elevator";
			break;
			
		case 10:
			travelForwardB = robot.straightDist.driveDistance(forwardDistB);
			nextStep(travelForwardB);
			currentStep = "Moving to third tote";
			break;
			
		case 11:
			elevatorReleaseB = robot.elevate.elevatorRelease();
			nextStep(elevatorReleaseB);
			currentStep = "Releasing elevator";
			break;
			
		case 12:
			elevateDownB = robot.elevate.elevatorHeight(dropHeightB);
			nextStep(elevateDownB);
			currentStep = "Dropping elevator B";
			break;
			
		case 13:
    		flippersOutB = robot.air.flippersOut();
			nextStep(flippersOutB);
			currentStep = "Flippers out B";
			break;
			
		case 14:
			liftToteC = robot.elevate.elevatorHeight(liftHeightC);
			nextStep(liftToteC);
			currentStep = "Lifting third tote";
			break;
			
		case 15:
			elevatorBrakeC = robot.elevate.elevatorLock();
			nextStep(elevatorBrakeC);
			currentStep = "Braking elevator";
			break;
			
		case 16:
			turnA = robot.turn.PIDturnDegrees(turnDegreesA);
			nextStep(turnA);
			currentStep = "Turning towards autozone";
			break;
		
		case 17:
			travelForwardC = robot.straightDist.driveDistance(forwardDistC);
			nextStep(travelForwardC);
			currentStep = "Driving into autozone";
			break;
		
		case 18:
			elevatorReleaseC = robot.elevate.elevatorRelease();
			nextStep(elevatorReleaseC);
			currentStep = "Releasing elevator C";
			break;
			
		case 19: 
			elevateDownC = robot.elevate.elevatorHeight(dropHeightC);
			nextStep(elevateDownC);
			currentStep = "Dropping off tote stack";
			break;
			
		case 20:
			autonDone = true;
			currentStep = "Autonomous Three Done";
			break;
		
		case 21:
    		flippersInB = robot.air.flippersIn();
			nextStep(flippersInB);
			currentStep = "Flippers in B";
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
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
