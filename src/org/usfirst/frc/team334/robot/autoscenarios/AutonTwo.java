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
			backwardA,
			turnA,
			backwardB,
			elevatorDownA,
			backwardC,
			turnB,
			forwardA,
			elevatorUpB,
			elevatorBrakeB,
			backwardD,
			dropContainer;
	
	double liftHeightA, dropHeightA, dropHeightB, backDistA, backDistB, backDistC, turnDegA;
			
	
    public AutonTwo(Robot robot) {
        this.robot = robot;
        
        autonDone = elevateUpA = elevatorBrakeA = backwardA =
        turnA = backwardB = elevatorDownA = backwardC = 
        turnB = forwardA = elevatorUpB = elevatorBrakeB = backwardD = dropContainer = false;
        
        liftHeightA = 0;
        dropHeightA = 0;
        dropHeightB = 0;
        backDistA = 0;
        backDistB = 0;
        backDistC = 0;
        turnDegA = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Two");
    	System.out.println("Auton Two");
    	
    	if(!elevateUpA) {
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
