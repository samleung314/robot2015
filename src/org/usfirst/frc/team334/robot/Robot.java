package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.automaton.*;
import org.usfirst.frc.team334.robot.subsystems.*;

public class Robot extends IterativeRobot {

    // Why not just make all these public and use the class objects from Robot instead of passing them into each other?
    public Auton auton;
    public Air air;
    public Controllers control;
    public Drivetrain drive;
    public Elevator elevate;
    public Encoders encode;
    public RampPID ramp;
    public StraightPID straight;
    public TurnPID turn;
    public Smartdashboard smart;

    public void robotInit() {
    	
    	air = new Air(this);
    	drive = new Drivetrain(this);
    	//encode = new Encoders(this);
    	//auton = new Auton(this);
    	//ramp = new RampPID(this);
    	//straight = new StraightPID(this);
    	//turn = new TurnPID(this);
    	control = new Controllers(this);
    	elevate = new Elevator(this);
    	smart = new Smartdashboard(this);
    }
    
    public void autonomousInit() {
    	//Reset any encoders, start any timers, start any PIDs; as needed
    }

    public void autonomousPeriodic() {

    }
    
    public void teleopInit() {
        //Stop the PIDs
    }

    public void teleopPeriodic() {
        
    }

    public void testPeriodic() {
        //Generatlly not used
    }

}
