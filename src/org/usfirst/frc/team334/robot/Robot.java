package org.usfirst.frc.team334.robot;

import org.usfirst.frc.team334.robot.automaton.*;
import org.usfirst.frc.team334.robot.autoscenarios.*;
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
	public DistancePID ramp;
	public StraightPID straight;
	public StraightRampPID straightRamp;
	public TurnPID turn;
	public Smartdashboard smart;
	
	public AutonOne auto1;
	public AutonTwo auto2;
	public AutonThree auto3;
	
	public Command autoCommand, testCommand;
	public SendableChooser autoChoose, testChoose; 
	
	boolean testStraight, testTurn;
	
	public void robotInit() {

		/*Create objects*/
		air = new Air(this);
		drive = new Drivetrain(this);
		encode = new Encoders(this);
		auto = new Auton(this);
		ramp = new DistancePID(this);
		turn = new TurnPID(this);
		straight = new StraightPID(this);
		straightRamp = new StraightRampPID(this);
		control = new Controllers(this);
		elevate = new Elevator(this);
		pot = new ElevatorPot(this);
		smart = new Smartdashboard(this);
		
		auto1 = new AutonOne(this);
		auto2 = new AutonTwo(this);
		auto3 = new AutonThree(this);
		
		/*Sendable Chooser Setup*/
		
		//Add objects to the SendableChooser for autonomous 
        autoChoose = new SendableChooser();
        autoChoose.addDefault("Auton One", auto1);
        autoChoose.addObject("Auton Two", auto2);  
        autoChoose.addObject("Auton Three", auto3);  
        SmartDashboard.putData("Choose Auton Mode", autoChoose);
        
        /*//Add objects to the SendableChooser for testing
        testChoose = new SendableChooser();
        testChoose.addDefault("Do Nothing", new Default());
        testChoose.addObject("Cycle Solenoids", new CycleAir(this));   
        SmartDashboard.putData("Choose Test Mode", testChoose);*/
		
	}

	public void autonomousInit() {
		elevate.elevatorRelease(); //Elevator starts unlocked
		smart.getPrefs();
		encode.resetEncoders();
		turn.gyro.reset();
		air.compress.stop();
		
		Scheduler.getInstance().removeAll(); //Need to clear previously selected commands so one a single command runs
		
		autoCommand = (Command) autoChoose.getSelected();
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		//smart.displaySensors();
		
		/*if (!testStraight) {
			testStraight = straight.travelDistance(smart.autoDist, smart.autoSpeed);
		}
		
		SmartDashboard.putData("Straight PID", straight.straightPID);*/
		
		/*if(!testTurn) {
			testTurn = turn.turnDegrees(smart.autoDegrees, smart.autoSpeed);
		}*/
		//SmartDashboard.putData("Turn PID", turn.turnPID);
		
		//turn.PIDturnDegrees(smart.autoDist);
		
		//Scheduler.getInstance().run();
	}

	public void teleopInit() {
		elevate.elevatorRelease(); //Elevator starts unlocked
		encode.resetEncoders();
		turn.gyro.reset();
		
		air.compress.stop();
		
		/*Disable the PIDs*/
		straight.straightPID.disable();
		turn.turnPID.disable();
		straightRamp.keepStraightPID.disable();
		ramp.rampPID.disable();
	}

	public void teleopPeriodic() {
		control.getControllers();
		elevate.noSafety(control.xBoxLeftY); //Use only for testing
		control.joystickDrive();
		//control.testSolenoids();
		
		//air.cycleThrough();
		
		smart.displaySensors();
	}
	
	public void testInit() {
		elevate.elevatorRelease(); //Elevator starts unlocked
		smart.getPrefs();
		encode.resetEncoders();
		turn.gyro.reset();
		air.compress.stop();
		
		//testCommand = (Command) testChoose.getSelected();
		//testCommand.start();
	}

	public void testPeriodic() {
		straightRamp.rampStraight(smart.autoDist);
		
		SmartDashboard.putData("KeepStraight PID", straightRamp.keepStraightPID);
		SmartDashboard.putData("Ramp PID", ramp.rampPID);
		
		smart.displaySensors();
		//Scheduler.getInstance().run();
	}

}
