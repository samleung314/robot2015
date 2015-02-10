package org.usfirst.frc.team334.robot;

import org.usfirst.frc.team334.robot.automaton.*;
import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	// Why not just make all these public and use the class objects from Robot
	// instead of passing them into each other?
	public Auton auto;
	public Air air;
	public Controllers control;
	public Drivetrain drive;
	public Elevator elevate;
	public ElevatorPot pot;
	public Encoders encode;
	public RampPID ramp;
	public StraightPID straight;
	public TurnPID turn;
	public Smartdashboard smart;
	
	public Command autoCommand, testCommand;
	public SendableChooser autoChoose, testChoose; 
	
	boolean testStraight, testTurn;

	public void robotInit() {

		air = new Air(this);
		drive = new Drivetrain(this);
		encode = new Encoders(this);
		auto = new Auton(this);
		// ramp = new RampPID(this);
		turn = new TurnPID(this);
		straight = new StraightPID(this);
		control = new Controllers(this);
		elevate = new Elevator(this);
		pot = new ElevatorPot(this);
		smart = new Smartdashboard(this);
		
		/*Sendable Chooser Setup
		
		//Add objects to the SendableChooser for autonomous 
        autoChoose = new SendableChooser();
        autoChoose.addDefault("Do Nothing", new Default());
        autoChoose.addObject("Ramping PID", ramp);  
        SmartDashboard.putData("Choose Auton Mode", autoChoose);
        
        //Add objects to the SendableChooser for testing
        testChoose = new SendableChooser();
        testChoose.addDefault("Do Nothing", new Default());
        testChoose.addObject("Cycle Solenoids", new CycleAir(this));   
        SmartDashboard.putData("Choose Test Mode", testChoose);*/
		
	}

	public void autonomousInit() {
		smart.getPrefs();
		elevate.elevatorRelease(); //Elevator starts unlocked
		encode.resetEncoders();
		turn.gyro.reset();
		air.compress.stop();
		//autoCommand = (Command) autoChoose.getSelected();
		//autoCommand.start();
		
		testStraight = false;
		testTurn = false;
		Timer.delay(0.1);
	}

	public void autonomousPeriodic() {
		smart.displaySensors();
		
		/*if (!testStraight) {
			testStraight = straight.travelDistance(smart.autoDist, smart.autoSpeed);
		}
		
		SmartDashboard.putData("Straight PID", straight.straightPID);*/
		
		/*if(!testTurn) {
			testTurn = turn.turnDegrees(smart.autoDegrees, smart.autoSpeed);
		}*/
		
		turn.PIDturnDegrees(90);
		
		SmartDashboard.putData("Turn PID", turn.turnPID);
		//Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// Remember to stop auton PIDs
		straight.straightPID.disable();
		turn.turnPID.disable();
		elevate.elevatorRelease(); //Elevator starts unlocked
		encode.resetEncoders();
		turn.gyro.reset();
	}

	public void teleopPeriodic() {
		control.getControllers();
		control.controlElevator();
		control.joystickDrive();
		//control.testSolenoids();
		
		//air.cycleThrough();
		air.compress.stop();
		
		smart.displaySensors();
	}
	
	public void testInit() {
		// Remember to stop auton and teleop PIDs
		//elevate.elevatorRelease(); //Elevator starts unlocked
		
		//testCommand = (Command) testChoose.getSelected();
		//testCommand.start();
	}

	public void testPeriodic() {
		// Used for testing code
		//Scheduler.getInstance().run();
	}

}
