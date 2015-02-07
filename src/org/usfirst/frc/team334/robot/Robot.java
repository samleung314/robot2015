package org.usfirst.frc.team334.robot;

import org.usfirst.frc.team334.robot.automaton.Auton;
import org.usfirst.frc.team334.robot.automaton.Default;
import org.usfirst.frc.team334.robot.automaton.RampPID;
import org.usfirst.frc.team334.robot.automaton.StraightPID;
import org.usfirst.frc.team334.robot.automaton.TurnPID;
import org.usfirst.frc.team334.robot.human.Controllers;
import org.usfirst.frc.team334.robot.human.Smartdashboard;
import org.usfirst.frc.team334.robot.subsystems.Air;
import org.usfirst.frc.team334.robot.subsystems.CycleAir;
import org.usfirst.frc.team334.robot.subsystems.Drivetrain;
import org.usfirst.frc.team334.robot.subsystems.Elevator;
import org.usfirst.frc.team334.robot.subsystems.ElevatorPot;
import org.usfirst.frc.team334.robot.subsystems.Encoders;

import edu.wpi.first.wpilibj.IterativeRobot;
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

	public void robotInit() {

		air = new Air(this);
		drive = new Drivetrain(this);
		encode = new Encoders(this);
		auto = new Auton(this);
		// ramp = new RampPID(this);
		straight = new StraightPID(this);
		// turn = new TurnPID(this);
		control = new Controllers(this);
		elevate = new Elevator(this);
		pot = new ElevatorPot(this);
		smart = new Smartdashboard(this);
		
		/*Sendable Chooser Setup*/
		
		//Add objects to the SendableChooser for autonomous 
        autoChoose = new SendableChooser();
        autoChoose.addDefault("Do Nothing", new Default());
        autoChoose.addObject("Ramping PID", ramp);  
        SmartDashboard.putData("Choose Auton Mode", autoChoose);
        
        //Add objects to the SendableChooser for testing
        testChoose = new SendableChooser();
        testChoose.addDefault("Do Nothing", new Default());
        testChoose.addObject("Cycle Solenoids", new CycleAir(this));   
        SmartDashboard.putData("Choose Test Mode", testChoose);
		
	}

	public void autonomousInit() {
		elevate.elevatorRelease(); //Elevator starts unlocked
		
		//autoCommand = (Command) autoChoose.getSelected();
		//autoCommand.start();
	}

	public void autonomousPeriodic() {
		//air.chargeAir();
		SmartDashboard.putData("Elevator PID", pot.elevatorPID);
		SmartDashboard.putBoolean("Locked?", elevate.locked);
		SmartDashboard.putNumber("Elevator Level", pot.elevatorPot.get());
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// Remember to stop auton PIDs
		elevate.elevatorRelease(); //Elevator starts unlocked
		encode.resetEncoders();
		auto.gyro.reset();
		//straight.straightPID.enable();
	}

	public void teleopPeriodic() {
		control.getControllers();
		//control.controlElevator();
		control.joystickDrive();
		//control.testSolenoids();
		//air.cycleThrough();
		air.compress.stop();
		
		smart.displaySensors();
		//smart.displayPIDs();
		//drive.doubleVicsDrive(0.5, 0.5);
	}
	
	public void testInit() {
		// Remember to stop auton and teleop PIDs
		//elevate.elevatorRelease(); //Elevator starts unlocked
		
		testCommand = (Command) testChoose.getSelected();
		testCommand.start();
	}

	public void testPeriodic() {
		// Used for testing code
	}

}
