package org.usfirst.frc.team334.robot;

import org.usfirst.frc.team334.robot.automaton.Auton;
import org.usfirst.frc.team334.robot.automaton.DistancePID;
import org.usfirst.frc.team334.robot.automaton.StraightDistancePID;
import org.usfirst.frc.team334.robot.automaton.StraightPID;
import org.usfirst.frc.team334.robot.automaton.TurnPID;
import org.usfirst.frc.team334.robot.automaton.UltrasonicPID;
import org.usfirst.frc.team334.robot.autoscenarios.AutonOne;
import org.usfirst.frc.team334.robot.autoscenarios.AutonTest;
import org.usfirst.frc.team334.robot.autoscenarios.AutonThree;
import org.usfirst.frc.team334.robot.autoscenarios.AutonTwo;
import org.usfirst.frc.team334.robot.human.Controllers;
import org.usfirst.frc.team334.robot.human.Smartdashboard;
import org.usfirst.frc.team334.robot.subsystems.Air;
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
	public DistancePID distance;
	public StraightPID straight;
	public StraightDistancePID straightDist;
	public TurnPID turn;
	public UltrasonicPID ultrasonic;
	public Smartdashboard smart;

	public AutonOne oneContainer;
	public AutonTwo twoContainer;
	public AutonThree threeTotes;
	public AutonTest autonTest;

	public Command autoCommand;
	public SendableChooser autoChoose;

	boolean testStraight, testTurn;

	public void robotInit() {

		/* Create objects */
		air = new Air(this);
		drive = new Drivetrain(this);
		encode = new Encoders(this);
		auto = new Auton(this);
		distance = new DistancePID(this);
		turn = new TurnPID(this);
		ultrasonic = new UltrasonicPID(this);
		straight = new StraightPID(this);
		straightDist = new StraightDistancePID(this);
		control = new Controllers(this);
		elevate = new Elevator(this);
		pot = new ElevatorPot(this);
		smart = new Smartdashboard(this);

		oneContainer = new AutonOne(this);
		twoContainer = new AutonTwo(this);
		threeTotes = new AutonThree(this);
		autonTest = new AutonTest(this);

		/* Sendable Chooser Setup */

		// Add objects to the SendableChooser for autonomous
		autoChoose = new SendableChooser();
		autoChoose.addDefault("ONE Container", oneContainer);
		autoChoose.addObject("TWO Containers", twoContainer);
		autoChoose.addObject("THREE Totes", threeTotes);
		autoChoose.addObject("TESTING", autonTest);
		SmartDashboard.putData("Choose Auton Mode", autoChoose);

	}

	public void autonomousInit() {
		elevate.elevatorRelease(); // Elevator starts unlocked
		smart.getPrefs();
		encode.resetEncoders();
		turn.gyro.reset();
		air.compress.stop();

		Scheduler.getInstance().removeAll(); //Need to clear previously selected commands so only a single command runs
		autoCommand = (Command) autoChoose.getSelected();
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		elevate.elevatorRelease(); // Elevator starts unlocked
		encode.resetEncoders();
		turn.gyro.reset();
		
		/*Disable the PIDs*/
		straight.straightPID.disable();
		turn.turnPID.disable();
		straightDist.keepStraightPID.disable();
		distance.rampPID.disable();
		ultrasonic.ultrasonicPID.disable();
	}

	public void teleopPeriodic() {
		control.getControllers();
		// control.controlElevator();
		elevate.noSafety(control.xBoxLeftY); // Use only for testing
		
		//control.joystickDrive();
		control.dynamicDrive();
		// control.testSolenoids();

		// air.cycleThrough();

		smart.displaySensors();
	}

	public void testInit() {

	}

	public void testPeriodic() {

	}

}
