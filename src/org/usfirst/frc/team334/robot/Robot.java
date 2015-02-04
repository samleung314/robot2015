package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.automaton.*;
import org.usfirst.frc.team334.robot.subsystems.*;

public class Robot extends IterativeRobot {

	// Why not just make all these public and use the class objects from Robot
	// instead of passing them into each other?
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
		// encode = new Encoders(this);
		// auton = new Auton(this);
		// ramp = new RampPID(this);
		// straight = new StraightPID(this);
		// turn = new TurnPID(this);
		control = new Controllers(this);
		elevate = new Elevator(this);
		smart = new Smartdashboard(this);
	}

	public void autonomousInit() {
		elevate.elevatorRelease(); //Elevator starts unlocked
	}

	public void autonomousPeriodic() {
		air.chargeAir();
	}

	public void teleopInit() {
		// Stop the PIDs here
		elevate.elevatorRelease(); //Elevator starts unlocked
	}

	public void teleopPeriodic() {
		//control.getControllers();
		//control.controlElevator();
		//SmartDashboard.putBoolean("Dog Locked?", elevate.locked);
		//SmartDashboard.putBoolean("Elevator Moving?", elevate.moving);
		SmartDashboard.putNumber("Pot", elevate.elevatorPot.get());
		
		//air.chargeAir();
		// control.setElevatorLevel();
		// SmartDashboard.putNumber("ELEVATOR TOTE LEVEL", control.elevatorLevel);
		// SmartDashboard.putNumber("ELEVATOR POT LEVEL", elevate.elevatorPot.get());
	}

	public void testPeriodic() {
		// Generally not used
	}

}
