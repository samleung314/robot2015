package org.usfirst.frc.team334.robot.autoscenarios;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonOne extends Command {

	Robot robot; 
	
	//Booleans are false when not completed. Will be true once completed.
	boolean autonDone,
			elevateUpA,
			elevatorBrakeA,
			turnA,
			forwardA;
	
	double liftHeight = 5, 
		   turnDegrees= 90,
		   travelDistance = 117.5;
	
    public AutonOne(Robot robot) {
    	this.robot = robot;
    	autonDone = elevateUpA = elevatorBrakeA = turnA = forwardA = false;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto One");
    	System.out.println("Auton One");
    	
    	if(!elevateUpA) {
    		elevateUpA = robot.elevate.elevatorHeight(liftHeight);
    	}
    	if(elevateUpA && !elevatorBrakeA) {
    		elevatorBrakeA = robot.elevate.elevatorBreak();
    	}
    	if (elevatorBrakeA && !turnA) {
    		robot.turn.PIDturnDegrees(turnDegrees);
    	}
    	if(turnA && !forwardA) {
    		forwardA = robot.straightRamp.rampStraight(travelDistance);
    	}
    	if(forwardA) {
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
