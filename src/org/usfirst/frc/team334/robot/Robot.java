package org.usfirst.frc.team334.robot;

import org.usfirst.frc.team334.robot.automaton.*;
import org.usfirst.frc.team334.robot.autoscenarios.*;
import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public Auton auto;
	public Air air;
	public Controllers control;
	public Drivetrain drive;
	public Elevator elevator;
	public Encoders encode;
	public Smartdashboard smart;
	
	public ElevatorPID pot;
	public DistancePID distance;
	public StraightPID straight;
	public StraightDistancePID straightDist;
	public TurnPID turn;
	public UltrasonicPID ultrasonic;

	public AutonOneTote oneTote;
	public AutonOneContainer oneContainer;
	public AutonTwoContainer twoContainer;
	public AutonThreeTote threeTotes;
	public AutonTest autonTest;

	public Command autoCommand;
	public SendableChooser autoChoose;

	boolean lifted, clamp, once;

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
		elevator = new Elevator(this);
		pot = new ElevatorPID(this);
		smart = new Smartdashboard(this);

		oneTote = new AutonOneTote(this);
		oneContainer = new AutonOneContainer(this);
		twoContainer = new AutonTwoContainer(this);
		threeTotes = new AutonThreeTote(this);
		autonTest = new AutonTest(this);

		/* Sendable Chooser Setup */

		// Add objects to the SendableChooser for autonomous
		autoChoose = new SendableChooser();
		autoChoose.addDefault("ONE Tote", oneTote);
		autoChoose.addObject("ONE Container", oneContainer);
		autoChoose.addObject("TWO Containers", twoContainer);
		autoChoose.addObject("THREE Totes", threeTotes);
		autoChoose.addObject("TESTING", autonTest);
		SmartDashboard.putData("Choose Auton Mode", autoChoose);
	}

	public void autonomousInit() {
		elevator.elevatorRelease(); // Elevator starts unlocked
		smart.getPrefs();
		encode.resetEncoders();
		turn.gyro.reset();
		
		lifted = clamp = once = false;

		Scheduler.getInstance().removeAll(); //Need to clear previously selected commands so only a single command runs
		autoCommand = (Command) autoChoose.getSelected();
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//elevatorTuning();
		//runOnce();
		
		SmartDashboard.putNumber("Elevator Height", pot.getLevel());
		
		SmartDashboard.putNumber("Turn Output", turn.turnOutput);
		
		SmartDashboard.putNumber("Gyro", turn.gyro.getAngle());
		
		SmartDashboard.putNumber("Distance", encode.averageDist());
		
		SmartDashboard.putData("DistancePID", distance.distancePID);
		SmartDashboard.putData("StraightPID", straightDist.keepStraightPID);
		
		SmartDashboard.putData("ElevatorPID", pot.elevatorPID);
		SmartDashboard.putData("TurnPID", turn.turnPID);
		
	}
	
	public void elevatorTuning() {
		if(!clamp) {
			clamp = air.flippersAutoGrip();
		} else if(clamp && !lifted) {
			lifted = pot.elevatePID(14);
		}
		
		SmartDashboard.putBoolean("At height?", lifted);
		
		SmartDashboard.putData("ElevatorPID", pot.elevatorPID);
		SmartDashboard.putNumber("Elevator Height", pot.getLevel());
		SmartDashboard.putNumber("Elevator Raw", pot.elevatorPot.get());
	}
	
	public void runOnce() {
		if(!once) {
			once = turn.PIDturnDegrees(90);
		}
	}

	public void teleopInit() {
		elevator.elevatorRelease(); // Elevator starts unlocked
		air.flippersRelease(); //Flippers start released
		encode.resetEncoders();
		turn.gyro.reset();
		
		/*Disable the PIDs*/
		straight.straightPID.disable();
		straightDist.keepStraightPID.disable();
		turn.turnPID.disable();
		distance.distancePID.disable();
		ultrasonic.ultrasonicPID.disable();
		pot.elevatorPID.disable();
	}

	public void teleopPeriodic() {
		air.chargeAir();
		
		control.getControllers();
		control.controlElevator();
		control.joystickDrive();

		//smart.displaySensors();
		
		SmartDashboard.putNumber("Gyro", turn.gyro.getAngle());
		SmartDashboard.putNumber("Elevator Height", pot.getLevel());
		SmartDashboard.putNumber("Elevator Raw", pot.elevatorPot.get());
	}

	public void testInit() {

	}

	public void testPeriodic() {

	}

}
