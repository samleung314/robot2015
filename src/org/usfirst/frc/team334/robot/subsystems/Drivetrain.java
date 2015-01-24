package org.usfirst.frc.team334.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc.team334.robot.physicals.*;

public class Drivetrain {
	
	Sensors sensors;
	Mechanics mech;

	public PIDController straightPID;
    public PIDController turnPID;
    public PIDController distancePID;
    
    public double sP, sI, sD, tP, tI, tD;
    
    public Drivetrain(Sensors sensors, Mechanics mech){
    	this.sensors = sensors;
    	this.mech = mech;
    	
    	straightPID = new PIDController(sP, sI, sD, sensors.gyro, mech.leftVics);
    	//turnPID = new PIDController(tP, tI, tD, sensors.gyro, )
    }
    
    
}
