package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.DoubleVics;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;

public class Elevator {

	Robot robot;

	private final VictorSP elevatorVicA;
	private final VictorSP elevatorVicB;

	public DoubleVics elevatorVics;

	public AnalogPotentiometer elevatorPot;

	private final PIDController elevatorPID;

	//private final DigitalInput highSwitch, lowSwitch;

	boolean lock = false;
	boolean moving = false;
	double desiredpot = 1;
	double maxpot = 2;
	double minpot = 0;
	double p = 0, i = 0, d = 0;

	public Elevator(Robot robot) {
		this.robot = robot;

		elevatorVicA = new VictorSP(Constants.elevatorVictorA);
		elevatorVicB = new VictorSP(Constants.elevatorVictorB);
		
		elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);

		elevatorPot = new AnalogPotentiometer(Constants.elevatorPot);
		// 10 Turns max on the pot, 9.6 turns max on the elevator

		elevatorPID = new PIDController(p, i, d, elevatorPot, elevatorVics);

		//highSwitch = new DigitalInput(Constants.highSwitch);
		//lowSwitch = new DigitalInput(Constants.lowSwitch);
	}

	public void setElevator(double setpoint) {
		if (!lock) {
			elevatorPID.setSetpoint(setpoint);
			elevatorPID.enable();
		}
	}

	public void elevatorStop() // Makes Elevator stay still
	{
		elevatorVics.set(0);
	}

	public void elevatorBreak() // Engages the Dog Break in elevator
	{
		if (!moving) {
			lock = true;
			robot.air.lockDog();
		}
	}

	public void elevatorRelease() // Disengages the Dog Break in elevator
	{
		if (!moving) {
			lock = false;
			robot.air.releaseDog();
		}
	}

	public void elevatorUp() {
		if (elevatorPot.get() < desiredpot && elevatorPot.get() < maxpot
				&& !lock) {
			manualVicsElevator(-0.35);
		} else {
			elevatorStop();
		}
	}

	public void elevatorDown() // Makes Elevator go down
	{
		if (elevatorPot.get() > desiredpot && elevatorPot.get() > minpot
				&& !lock) {
			manualVicsElevator(0.35);
		} else {
			elevatorStop();
		}
	}

	public void manualVicsElevator(double speed) {
		if (speed != 0 && !lock) {
			elevatorVicA.set(-speed);
			elevatorVicB.set(-speed);
			moving = true;
		} else {
			moving = false;
		}
	}

	public void setElevatorLevel(int level) {
		switch (level) {
		case 1:
			elevatorPID.setSetpoint(100);
			break;
		case 2:
			elevatorPID.setSetpoint(100);
			break;
		case 3:
			elevatorPID.setSetpoint(100);
			break;
		case 4:
			elevatorPID.setSetpoint(100);
			break;
		case 5:
			elevatorPID.setSetpoint(100);
			break;
		case 6:
			elevatorPID.setSetpoint(100);
			break;
		}

	}
}
