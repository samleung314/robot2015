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
	        flippersReleaseB,
	        
	        backLittle;
	
	double liftZero = 0,
		   liftHeightA = 16, //tote 1 lift
		   liftHeightB = 16, //tote 2 lift 
		   liftHeightC = 12, //tote 3 lift
		   forwardDistA = 80,//85, //to second tote
		   forwardDistB = 81, //to last tote
	       forwardDistC = 155, //to dump zone
		   turnDegreesA = 90,
	
		   backDist = -20;
	
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
    	
    	backLittle = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Three");
    	SmartDashboard.putString("Current Step", currentStep);
    	System.out.println("---------->" + currentStep);
  
    	noBrake();
    }
    
    private void noBrake() {
    	switch (step) {
		//---------------------------------Starting Position--------------------------------
		case 1:
			flippersStart = robot.air.flippersAutoRelease();
			nextStep(flippersStart);
			currentStep = "Flippers start";
			break;

		case 2:
			elevatorZero = robot.elevator.zeroElevator();
			nextStep(elevatorZero);
			currentStep = "Elevator zero";
			break;
		//---------------------------------Pickup first tote-------------------------------- 
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
			travelForwardA = robot.straight.driveDistance(forwardDistA);
			nextStep(travelForwardA);
			currentStep = "Move to second tote";
			break;
			
		//---------------------------------Pickup second tote-------------------------------- 
		case 6:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 7:
			elevateDownA = robot.elevator.zeroElevator();
			nextStep(elevateDownA);
			currentStep = "Drop elevator A";
			break;
			
		case 8:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 9:
			liftToteB = robot.pot.elevatePID(liftHeightB);
			nextStep(liftToteB);
			currentStep = "Lift second tote";
			break;
			
		//---------------------------------Pickup third tote-------------------------------- 
		case 10:
			travelForwardB = robot.straight.driveDistance(forwardDistB);
			nextStep(travelForwardB);
			currentStep = "Move to third tote";
			break;
			
		case 11:
			flippersGripB = robot.air.flippersAutoRelease();
			nextStep(flippersGripB);
			break;

		case 12:
			elevateDownB = robot.pot.elevatePID(10);
			nextStep(elevateDownB);
			currentStep = "Lift tote A";
			break;
			
		case 13:
			//flippersGripC = robot.air.flippersAutoGrip();
			nextStep(true);
			currentStep = "Flippers grip C";
			break;

		case 14:
			//liftToteC = robot.pot.elevatePID(liftHeightC);
			nextStep(true);
			currentStep = "Lifting third tote";
			break;

		//---------------------------------Move to autozone-------------------------------- 
		case 15:
			turnA = robot.turn.PIDturnDegrees(turnDegreesA);
			nextStep(turnA);
			currentStep = "Turning towards autozone";
			break;

		case 16:
			travelForwardC = robot.straight.driveDistance(forwardDistC);
			nextStep(travelForwardC);
			currentStep = "Driving into autozone";
			break;
		
		//---------------------------------Drop stack-------------------------------- 

		case 17:
			elevateDownC = robot.elevator.zeroElevator();
			nextStep(elevateDownC);
			currentStep = "Dropping off tote stack";
			break;
			
		case 18:
			backLittle  = robot.straight.driveDistance(backDist);
			nextStep(backLittle);
			break;

		case 19:
			autonDone = true;
			currentStep = "Autonomous Three Done";
			break;

		default:
			System.out.println("Auton Three is defaulting");
			break;
		} 
    }
    
    private void withBrake() {
    	switch (step) {
		//---------------------------------Starting Position--------------------------------
		case 1:
			flippersStart = robot.air.flippersAutoRelease();
			nextStep(flippersStart);
			currentStep = "Flippers start";
			break;

		case 2:
			elevatorZero = robot.elevator.zeroElevator();
			nextStep(elevatorZero);
			currentStep = "Elevator zero";
			break;
		//---------------------------------Pickup first tote-------------------------------- 
		case 3:
			flippersGripA = robot.air.flippersAutoGrip();
			nextStep(flippersGripA);
			currentStep = "Flippers grip A";
			break;

		case 4:
			liftToteA = robot.pot.elevatePIDLockFix(liftHeightA);
			nextStep(robot.pot.elevatorPID.onTarget());
			currentStep = "Lift tote A";
			break;

		case 5:
			travelForwardA = robot.straight.driveDistance(forwardDistA);
			nextStep(travelForwardA);
			currentStep = "Move to second tote";
			break;
			
		//---------------------------------Pickup second tote-------------------------------- 
		case 6:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 7:
			elevateDownA = robot.elevator.zeroElevator();
			nextStep(elevateDownA);
			currentStep = "Drop elevator A";
			break;
			
		case 8:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 9:
			liftToteB = robot.pot.elevatePIDLockFix(liftHeightB);
			nextStep(liftToteB);
			currentStep = "Lift second tote";
			break;
			
		//---------------------------------Pickup third tote-------------------------------- 
		case 10:
			travelForwardB = robot.straight.driveDistance(forwardDistB);
			nextStep(travelForwardB);
			currentStep = "Move to third tote";
			break;
			
		case 11:
			flippersGripB = robot.air.flippersAutoRelease();
			nextStep(flippersGripB);
			currentStep = "Flippers release B";
			break;

		case 12:
			elevateDownB = robot.pot.elevatePIDLockFix(liftZero);
			nextStep(elevateDownB);
			currentStep = "Drop elevator B";
			break;
			
		case 13:
			flippersGripC = robot.air.flippersAutoGrip();
			nextStep(flippersGripC);
			currentStep = "Flippers grip C";
			break;

		case 14:
			liftToteC = robot.pot.elevatePIDLockFix(liftHeightC);
			nextStep(liftToteC);
			currentStep = "Lifting third tote";
			break;

		//---------------------------------Move to autozone-------------------------------- 
		case 15:
			turnA = robot.turn.PIDturnDegrees(turnDegreesA);
			nextStep(turnA);
			currentStep = "Turning towards autozone";
			break;

		case 16:
			travelForwardC = robot.straight.driveDistance(forwardDistC);
			nextStep(travelForwardC);
			currentStep = "Driving into autozone";
			break;
		
		//---------------------------------Drop stack-------------------------------- 

		case 17:
			flippersReleaseB = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseB);
			currentStep = "Flippers release B";
			break;
			
		case 18:
			elevateDownC = robot.pot.elevatePIDLockFix(liftZero);
			nextStep(elevateDownC);
			currentStep = "Dropping off tote stack";
			break;

		case 19:
			autonDone = true;
			currentStep = "Autonomous Three Done";
			break;

		default:
			System.out.println("Auton Three is defaulting");
			break;
    	}
    }
    
    private void withBrakeFix() {
    	switch (step) {
		//---------------------------------Starting Position--------------------------------
		case 1:
			flippersStart = robot.air.flippersAutoRelease();
			nextStep(flippersStart);
			currentStep = "Flippers start";
			break;

		case 2:
			elevatorZero = robot.pot.elevatePIDLockFix(liftZero);
			nextStep(elevatorZero);
			currentStep = "Elevator zero";
			break;
		//---------------------------------Pickup first tote-------------------------------- 
		case 3:
			flippersGripA = robot.air.flippersAutoGrip();
			nextStep(flippersGripA);
			currentStep = "Flippers grip A";
			break;

		case 4:
			liftToteA = robot.pot.elevatePIDLockFix(liftHeightA);
			nextStep(robot.pot.elevatorPID.onTarget());
			currentStep = "Lift tote A";
			break;

		case 5:
			travelForwardA = robot.straight.driveDistance(forwardDistA);
			nextStep(travelForwardA);
			currentStep = "Move to second tote";
			break;
			
		//---------------------------------Pickup second tote-------------------------------- 
		case 6:
			flippersReleaseA = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseA);
			currentStep = "Flippers release A";
			break;

		case 7:
			elevateDownA = robot.pot.elevatePIDLockFix(liftZero);
			nextStep(elevateDownA);
			currentStep = "Drop elevator A";
			break;
			
		case 8:
			flippersGripB = robot.air.flippersAutoGrip();
			nextStep(flippersGripB);
			currentStep = "Flippers grip B";
			break;

		case 9:
			liftToteB = robot.pot.elevatePIDLockFix(liftHeightB);
			nextStep(liftToteB);
			currentStep = "Lift second tote";
			break;
			
		//---------------------------------Pickup third tote-------------------------------- 
		case 10:
			travelForwardB = robot.straight.driveDistance(forwardDistB);
			nextStep(travelForwardB);
			currentStep = "Move to third tote";
			break;
			
		case 11:
			flippersGripB = robot.air.flippersAutoRelease();
			nextStep(flippersGripB);
			currentStep = "Flippers release B";
			break;

		case 12:
			elevateDownB = robot.pot.elevatePIDLockFix(liftZero);
			nextStep(elevateDownB);
			currentStep = "Drop elevator B";
			break;
			
		case 13:
			flippersGripC = robot.air.flippersAutoGrip();
			nextStep(flippersGripC);
			currentStep = "Flippers grip C";
			break;

		case 14:
			liftToteC = robot.pot.elevatePIDLockFix(liftHeightC);
			nextStep(liftToteC);
			currentStep = "Lifting third tote";
			break;

		//---------------------------------Move to autozone-------------------------------- 
		case 15:
			turnA = robot.turn.PIDturnDegrees(turnDegreesA);
			nextStep(turnA);
			currentStep = "Turning towards autozone";
			break;

		case 16:
			travelForwardC = robot.straight.driveDistance(forwardDistC);
			nextStep(travelForwardC);
			currentStep = "Driving into autozone";
			break;
		
		//---------------------------------Drop stack-------------------------------- 

		case 17:
			flippersReleaseB = robot.air.flippersAutoRelease();
			nextStep(flippersReleaseB);
			currentStep = "Flippers release B";
			break;
			
		case 18:
			elevateDownC = robot.pot.elevatePIDLockFix(liftZero);
			nextStep(elevateDownC);
			currentStep = "Dropping off tote stack";
			break;

		case 19:
			autonDone = true;
			currentStep = "Autonomous Three Done";
			break;

		default:
			System.out.println("Auton Three is defaulting");
			break;
    	}
    }
    
    private void oneTote() {
    	
    	switch (step) {
    	case 1:
    		nextStep(robot.air.flippersAutoRelease());
    		break;
    		
    	case 2:
    		nextStep(robot.pot.elevatePID(liftZero));
    		break;
    		
    	case 3:
    		nextStep(robot.air.flippersAutoGrip());
    		break;
    		
    	case 4:
    		nextStep(robot.pot.elevatePID(liftHeightA));
    		break;
    		
    	case 5:
    		nextStep(robot.straight.driveDistance(forwardDistA));
    		break;
    		
    	case 6:
			nextStep(robot.turn.PIDturnDegrees(turnDegreesA));
			break;

		case 7:
			nextStep(robot.straight.driveDistance(forwardDistC));
			break;
			
		case 8:
			autonDone = true;
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
