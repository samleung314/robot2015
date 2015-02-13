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
	        liftToteB,
	        liftToteC,
        	elevatorBrake,
        	turnNinety,
        	turnTheta,
        	travelForwardA,
	        travelForwardB,
	        travelForwardC,
	        travelForwardD,
	        travelForwardE;
	
	double liftHeightA = 5, //tote 1 lift
		   liftHeightB = 5, //tote 2 lift 
		   liftHeightC = 5, //tote 3 lift
		   turnDegreesA= 90,
	       turnDegreesB= 90,
		   travelDistanceA = 117.5, //to second tote
           travelDistanceB = 117.5, //to last tote
           travelDistanceC = 117.5, //to dump zone
           travelDistanceE = 117.5; //****Starting Move**** May be used in the future do not delete
	
    public AutonThree(Robot robot) {
        this.robot = robot;
        
        autonDone = liftToteA = liftToteB = liftToteC = elevatorBrake = turnNinety = 
        turnTheta = travelForwardA = travelForwardB = travelForwardC = travelForwardD = travelForwardE =  false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Mode", "Auto Three");
    	System.out.println("Auton Three");
    	
    	
    	//moving and picking up Totes
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
