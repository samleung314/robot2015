package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class TurnPID implements PIDOutput{

    Robot robot;

    public PIDController turnPID;
    double tP, tI, tD, turnOutput;
    
    public TurnPID(Robot robot) {
        this.robot = robot;

        turnPID = new PIDController(tP, tI, tD, robot.auto.gyro, this);
        turnPID.setContinuous();
        turnPID.setOutputRange(-1, 1);
    }

    @Override //Returns output speed for robot to turn at
    public void pidWrite(double output) {
        this.turnOutput = output;
    }
    
    public double gyroAngle() {
        return robot.auto.gyro.getAngle();
    }
    
    public boolean turnDegrees(double degrees, double turnSpeed) {

        if (gyroAngle() < degrees - 2 || gyroAngle() > degrees + 2) { //+- 2 degree tolerance

            if (degrees < 0){ //Negative Degrees (CCW Direction)
                if (gyroAngle() > degrees) {
                    robot.drive.doubleVicsDrive(-turnSpeed, turnSpeed); //Turn CCW
                } 
                
                else if (gyroAngle() < degrees) {
                    robot.drive.doubleVicsDrive(turnSpeed, -turnSpeed); //Turn CW
                }
            } 
            else if (degrees > 0) {//Positive Degrees (CW Direction)
                if (gyroAngle() > degrees) {
                    robot.drive.doubleVicsDrive(turnSpeed, -turnSpeed); //Turn CCW
                } 
                
                else if (gyroAngle() < degrees) {
                    robot.drive.doubleVicsDrive(-turnSpeed, turnSpeed); //Turn CW
                }
            }
            return false;
        } else {
        	robot.drive.doubleVicsDrive(0, 0);
            robot.auto.gyro.reset();
            return true;
        }
    }
    
    public void PIDturnDegrees(double degrees) {
        turnPID.setSetpoint(degrees);
        turnPID.enable();
        robot.drive.doubleVicsDrive(turnOutput, -turnOutput);       
}

}
