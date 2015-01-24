
package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.modes.*;
import org.usfirst.frc.team334.robot.physicals.*;
import org.usfirst.frc.team334.robot.subsystems.*;

public class Robot extends IterativeRobot {
	
	//Note to self: why not just make all these public and use objects from Robot instead of passing them into each other?
	Auton auto;
	Controllers control;
	Drivetrain drive;
	Mechanics mech;
	Sensors sensors;
	Smartdashboard smart;
	Teleop tele;
	
    public void robotInit() {
    	
    	mech = new Mechanics();
    	sensors = new Sensors();
    	control = new Controllers(mech);
    	drive = new Drivetrain(sensors, mech);
    	smart = new Smartdashboard(control, drive);
    	tele = new Teleop(control, drive, smart);

    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        tele.teleopPeri();
    }
    
    public void testPeriodic() {
    
    }
    
}
