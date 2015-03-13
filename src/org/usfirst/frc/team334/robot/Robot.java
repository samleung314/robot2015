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

	public Air air;
	public Controllers control;
	public Drivetrain drive;
	public Elevator elevator;
	public Encoders encode;
	public Smartdashboard smart;
	
	public ElevatorPID pot;
	public DistancePID distance;
	public StraightDistancePID straight;
	public TurnPID turn;
	public UltrasonicPID ultrasonic;

	public AutonOneTote oneTote;
	public AutonOneContainer oneContainer;
	public AutonTwoContainer twoContainer;
	public AutonThreeTote threeTotes;
	public AutonThreeToteUltra threeTotesUltra;

	public Command autoCommand;
	public SendableChooser autoChoose;

	boolean lifted, clamp, once, testDistance, testTurn;

	public void robotInit() {

		/* Create objects */
		air = new Air(this);
		drive = new Drivetrain(this);
		encode = new Encoders(this);
		distance = new DistancePID(this);
		turn = new TurnPID(this);
		ultrasonic = new UltrasonicPID(this);
		straight = new StraightDistancePID(this);
		control = new Controllers(this);
		elevator = new Elevator(this);
		pot = new ElevatorPID(this);
		smart = new Smartdashboard(this);

		oneTote = new AutonOneTote(this);
		oneContainer = new AutonOneContainer(this);
		twoContainer = new AutonTwoContainer(this);
		threeTotes = new AutonThreeTote(this);
		threeTotesUltra = new AutonThreeToteUltra(this);

		/* Sendable Chooser Setup */

		// Add objects to the SendableChooser for autonomous
		autoChoose = new SendableChooser();
		autoChoose.addDefault("ONE Tote", oneTote);
		autoChoose.addObject("ONE Container", oneContainer);
		autoChoose.addObject("TWO Containers", twoContainer);
		autoChoose.addObject("THREE Totes", threeTotes);
		autoChoose.addObject("THREE Totes ULTRA", threeTotesUltra);
		SmartDashboard.putData("Choose Auton Mode", autoChoose);
	}

	public void autonomousInit() {
		elevator.elevatorRelease(); // Elevator starts unlocked
		smart.getPrefs();
		encode.resetEncoders();
		turn.gyro.reset();
		
		lifted = clamp = false;
	    testDistance = testTurn =  false;

		Scheduler.getInstance().removeAll(); //Need to clear previously selected commands so only a single command runs
		autoCommand = (Command) autoChoose.getSelected();
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		//Scheduler.getInstance().run();
		elevatorTuning();
		//runOnce();
		//distanceTuning(smart.autoDist);
		//turnTuning(smart.autoDegrees);
		
		SmartDashboard.putNumber("Elevator Height", pot.getLevel());
		
		SmartDashboard.putNumber("Gyro", turn.gyro.getAngle());
		
		SmartDashboard.putNumber("Distance", encode.averageDist());
		
		SmartDashboard.putData("DistancePID", distance.distancePID);
		SmartDashboard.putData("StraightPID", straight.keepStraightPID);
		
		SmartDashboard.putData("ElevatorPID", pot.elevatorPID);
		SmartDashboard.putData("TurnPID", turn.turnPID);
		
	}
	
	public void distanceTuning(double dist) {
		if(!testDistance) {
			testDistance = straight.driveDistance(dist);
		}
		
		SmartDashboard.putBoolean("DISTANCE?", testDistance);
	}
	
	public void turnTuning(double degrees) {
		if(!testTurn) {
			testTurn = turn.PIDturnDegrees(degrees);
		}
		
		SmartDashboard.putBoolean("TURNED?", testTurn);
	}
	
	public void elevatorTuning() {
		if(!lifted) {
			lifted = pot.elevatePID(smart.autoHeight);
		}
		
		SmartDashboard.putBoolean("LIFTED?", lifted);
	}

	public void teleopInit() {
		elevator.elevatorRelease(); // Elevator starts unlocked
		//air.flippersRelease(); //Flippers start released
		encode.resetEncoders();
		turn.gyro.reset();
		
		/*Disable the PIDs*/
		distance.distancePID.disable();
		pot.elevatorPID.disable();
		straight.keepStraightPID.disable();
		turn.turnPID.disable();
		ultrasonic.ultrasonicPID.disable();
	}

	public void teleopPeriodic() {
		air.chargeAir();
		
		control.getControllers();
		control.joystickDrive();
		control.controlElevator();

		//control.riceOperate();
		//control.forestDrive();

		//smart.displaySensors();
		
		//SmartDashboard.putNumber("Gyro", turn.gyro.getAngle());
		SmartDashboard.putNumber("Elevator Height", pot.getLevel());
		SmartDashboard.putNumber("Elevator Raw", pot.elevatorPot.get());
		
		SmartDashboard.putBoolean("Top Out", elevator.topOut());
		SmartDashboard.putBoolean("Bottom Out", elevator.bottomOut());
	}
}
