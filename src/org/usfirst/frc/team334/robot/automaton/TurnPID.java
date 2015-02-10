package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class TurnPID implements PIDOutput {

    Robot robot;
    
    public Gyro gyro;

    public PIDController turnPID, doubleTurnPIDA, doubleTurnPIDB;
    double tP, tI, tD, dtP, dtI, dtD, turnOutput;

    public TurnPID(Robot robot) {
        this.robot = robot;
        
        gyro = new Gyro(Constants.gyroscope);

        turnPID = new PIDController(tP, tI, tD, gyro, this);
        turnPID.setInputRange(-360, 360);
        turnPID.setContinuous();
        turnPID.setOutputRange(-0.5, 0.5);
        
        /*Experimental*/
        doubleTurnPIDA = new PIDController(dtP, dtI, dtD, gyro, robot.drive.leftVics);
        doubleTurnPIDA.setContinuous();
        doubleTurnPIDA.setOutputRange(-0.5, 0.5);
        
        doubleTurnPIDB = new PIDController(dtP, dtI, dtD, gyro, robot.drive.rightVics);
        doubleTurnPIDB.setContinuous();
        doubleTurnPIDB.setOutputRange(-0.5, 0.5);
    }

    public boolean turnDegrees(double degrees, double turnSpeed) { //Method for making robot turn a number of degrees at a turnSpeed

        if (gyro.getAngle() < degrees - 2 || gyro.getAngle() > degrees + 2) { //+- 2 degree tolerance

            if (degrees < 0) { //Negative Degrees (CCW Direction)
                if (gyro.getAngle() > degrees) {
                    robot.drive.doubleVicsDrive(-turnSpeed, turnSpeed); //Turn CCW
                } else if (gyro.getAngle() < degrees) {
                    robot.drive.doubleVicsDrive(turnSpeed, -turnSpeed); //Turn CW
                }
            } else if (degrees > 0) {//Positive Degrees (CW Direction)
                if (gyro.getAngle() > degrees) {
                    robot.drive.doubleVicsDrive(turnSpeed, -turnSpeed); //Turn CCW
                } else if (gyro.getAngle() < degrees) {
                    robot.drive.doubleVicsDrive(-turnSpeed, turnSpeed); //Turn CW
                }
            }
            System.out.println("FALSE");
            return false;
        } else {
            robot.drive.doubleVicsDrive(0, 0);
            gyro.reset();
            System.out.println("TRUE");
            return true;
        }
    }
    
    public boolean simpleTurn(double degrees, double turnSpeed) {
    	if (gyro.getAngle() != degrees) {
    		
	    	if (gyro.getAngle() > degrees) {
	    		robot.drive.doubleVicsDrive(-turnSpeed, turnSpeed); //Turn CCW
	    	}
	    	else if (gyro.getAngle() < degrees) {
	    		robot.drive.doubleVicsDrive(turnSpeed, -turnSpeed);//Turn CW
	    	}
	    	return false;
    	}
    	else {
    		return true;
    	}
    }

    public void PIDturnDegrees(double degrees) { //Method for making robot turn a number of degrees using a PID controller
        turnPID.setSetpoint(degrees);
        turnPID.enable();
        robot.drive.doubleVicsDrive(turnOutput, -turnOutput);
    }
    
    /*Experimental*/
    public void doublePIDturnDegrees(double degrees) { //Method for making robot turn a number of degrees using two PID controllers
        doubleTurnPIDA.setSetpoint(degrees);
        doubleTurnPIDB.setSetpoint(degrees);

        doubleTurnPIDA.enable();
        doubleTurnPIDB.enable();
    }
    
    @Override //Returns output speed for robot to turn at
    public void pidWrite(double output) {
        this.turnOutput = output;
    }

}
