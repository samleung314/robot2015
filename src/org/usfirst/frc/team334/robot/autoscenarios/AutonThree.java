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
	        liftToteA,
	        elevatorBrakeA,
	        travelForwardA,
	        elevatorReleaseA,
	        
	        elevateDownA,
	        liftToteB,
	        elevatorBrakeB,
	        travelForwardB,
	        elevatorReleaseB,
	        
	        elevateDownB,
	        liftToteC,
	        elevatorBrakeC,
	        
        	turnA,
	        travelForwardC,
	        elevatorReleaseC,
	        elevateDownC;
	
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
    	
    	liftToteA = elevatorBrakeA = travelForwardA = elevatorReleaseA = elevateDownA = liftToteB =
        elevatorBrakeB = travelForwardB = elevatorReleaseB = elevateDownB = liftToteC = elevatorBrakeC =
        turnA = travelForwardC = elevatorReleaseC = elevateDownC = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Running Auto Three");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("---------->" + currentStep);
    	
    	switch (step) {
		case 1:
			liftToteA = robot.elevate.elevatorHeight(liftHeightA);
			nextStep(liftToteA);
			currentStep = "Lifting first tote";
			break;
			
		case 2:
			elevatorBrakeA = robot.elevate.elevatorBreak();
			nextStep(elevatorBrakeA);
			currentStep = "Braking elevator A";
			break;
			
		case 3:
			travelForwardA = robot.straightDist.driveDistance(forwardDistA);
			nextStep(travelForwardA);
			currentStep = "Moving to second tote";
			break;
			
		case 4:
			elevatorReleaseA = robot.elevate.elevatorRelease();
			nextStep(elevatorReleaseA);
			currentStep = "Releasing elevator A";
			break;
			
		case 5: 
			elevateDownA = robot.elevate.elevatorHeight(dropHeightA);
			nextStep(elevateDownA);
			currentStep = "Dropping elevator A";
			break;
				
		case 6:
			liftToteB = robot.elevate.elevatorHeight(liftHeightB);
			nextStep(liftToteB);
			currentStep = "Lifting second tote";
			break;
			
		case 7:
			elevatorBrakeB = robot.elevate.elevatorBreak();
			nextStep(elevatorBrakeB);
			currentStep = "Braking elevator";
			break;
			
		case 8:
			travelForwardB = robot.straightDist.driveDistance(forwardDistB);
			nextStep(travelForwardB);
			currentStep = "Moving to third tote";
			break;
			
		case 9:
			elevatorReleaseB = robot.elevate.elevatorRelease();
			nextStep(elevatorReleaseB);
			currentStep = "Releasing elevator";
			break;
			
		case 10:
			elevateDownB = robot.elevate.elevatorHeight(dropHeightB);
			nextStep(elevateDownB);
			currentStep = "Dropping elevator B";
			break;
			
		case 11:
			liftToteC = robot.elevate.elevatorHeight(liftHeightC);
			nextStep(liftToteC);
			currentStep = "Lifting third tote";
			break;
			
		case 12:
			elevatorBrakeC = robot.elevate.elevatorBreak();
			nextStep(elevatorBrakeC);
			currentStep = "Braking elevator";
			break;
			
		case 13:
			turnA = robot.turn.PIDturnDegrees(turnDegreesA);
			nextStep(turnA);
			currentStep = "Turning towards autozone";
			break;
		
		case 14:
			travelForwardC = robot.straightDist.driveDistance(forwardDistC);
			nextStep(travelForwardC);
			currentStep = "Driving into autozone";
			break;
		
		case 15:
			elevatorReleaseC = robot.elevate.elevatorRelease();
			nextStep(elevatorReleaseC);
			currentStep = "Releasing elevator C";
			break;
			
		case 16: 
			elevateDownC = robot.elevate.elevatorHeight(dropHeightC);
			nextStep(elevateDownC);
			currentStep = "Dropping off tote stack";
			break;
			
		case 17:
			autonDone = true;
			currentStep = "Autonomous Three Done";
			break;
			
		default:
			System.out.println("Auton Three is defaulting");
			break;
    	}
    	
    	/*//moving and picking up Totes
    	if(!travelForwardE) {
    		travelForwardE = robot.straightRamp.rampStraight(travelDistanceE);
    	}
    	if(!liftToteA && travelForwardE) {
    		liftToteA = robot.elevate.elevatorHeight(liftHeightA);
    	}
    	if(!travelForwardA && liftToteA) {
    		travelForwardA = robot.straightRamp.rampStraight(travelDistanceA);   //not engaging elevator clamp
    	}
    	if(!liftToteB && travelForwardA) {
    		liftToteB = robot.elevate.elevatorHeight(liftHeightB);
    	}
    	if(!travelForwardB && liftToteB) {
    		travelForwardB = robot.straightRamp.rampStraight(travelDistanceB);
    	}
    	if(!liftToteC && travelForwardB) {
    		liftToteC = robot.elevate.elevatorHeight(liftHeightC);
    	}
    	
    	if(!turnNinety && liftToteC)
    	{
    		robot.turn.PIDturnDegrees(turnDegreesA);
    	}
    	
    	if(!travelForwardC && turnNinety)
    	{
    		
    	}
    	
    	if(liftTote && !elevatorBrake) {
    		elevatorBrake = robot.elevate.elevatorBreak();
    	}
    	if (elevatorBrake && !turnNinety) {
    		robot.turn.PIDturnDegrees(turnDegrees);
    	}
    	if(turnNinety && !travelForward) {
    		travelForward = robot.straightRamp.rampStraight(travelDistance);
    	}
    	if(travelForward) {
    		autonDone = true;
    		*/
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
