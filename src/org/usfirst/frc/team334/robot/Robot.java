package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.modes.*;
import org.usfirst.frc.team334.robot.physicals.*;
import org.usfirst.frc.team334.robot.subsystems.*;

public class Robot extends IterativeRobot {

    // Why not just make all these public and use the class objects from Robot
    // instead of passing them into each other?
    public Auton auton;
    public Air air;
    public Controllers control;
    public Drivetrain drive;
    public Elevator elevate;
    public Mechanics mech;
    public Sensors sensors;
    public Smartdashboard smart;
    public Teleop tele;

    public void robotInit() {
    	
        mech = new Mechanics();
        sensors = new Sensors();
    	air = new Air(this);
    	auton = new Auton(this);
    	control = new Controllers(this);
    	drive = new Drivetrain(this);
    	elevate = new Elevator(this);
    	smart = new Smartdashboard(this);
    	tele = new Teleop(this);	
    }
    
    public void autonomousInit() {
    	
    }

    public void autonomousPeriodic() {

    }
    
    public void teleopInit()
    {
        
    }

    public void teleopPeriodic() {

    }

    public void testPeriodic() {

    }

}
