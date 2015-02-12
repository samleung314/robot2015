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
			liftContainer,
			elevatorBrake,
			turnNinety,
			travelForward;
	
	double liftHeight = 5, 
		   turnDegrees= 90,
		   travelDistance = 117.5;
	
    public AutonOne(Robot robot) {
    	this.robot = robot;
    	autonDone = liftContainer = elevatorBrake = turnNinety = travelForward = false;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto One");
    	System.out.println("Auton One");
    	
    	if(!liftContainer) {
    		liftContainer = robot.elevate.elevatorHeight(liftHeight);
    	}
    	if(liftContainer && !elevatorBrake) {
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
