package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;

public class Air {

	Robot robot;

	public Compressor compress;

	public DoubleSolenoid solA, //Left arm (Check!)
						  solB, //Right arm (Check!)
						  solC, 
						  solD, 
						  solE, //Both flippers (Check!)
						  solF; //Dog gear (Check!)

	public Air(Robot robot) {
		this.robot = robot;

		// Compressor is connected to pneumatic module 0
		compress = new Compressor(Constants.PCM0);

		/*Six solenoid modules: A,B,C,D,E,F*/
		
		/*Solenoids A,B,C are connected to pneumatic module 0. They operate at 60 psi*/
		solA = new DoubleSolenoid(Constants.PCM0, Constants.solenoidAX,
				Constants.solenoidAY);
		solB = new DoubleSolenoid(Constants.PCM0, Constants.solenoidBX,
				Constants.solenoidBY);
		solC = new DoubleSolenoid(Constants.PCM0, Constants.solenoidCX,
				Constants.solenoidCY);
		
		/*Solenoids D,E,F are connected to pneumatic module 1. They operate at 30 psi*/
		solD = new DoubleSolenoid(Constants.PCM1, Constants.solenoidDX,
				Constants.solenoidDY);
		solE = new DoubleSolenoid(Constants.PCM1, Constants.solenoidEX,
				Constants.solenoidEY);
		solF = new DoubleSolenoid(Constants.PCM1, Constants.solenoidFX,
				Constants.solenoidFY);
	}

	// Compressor will run if pressure is less than 120 psi. It will stop at 120
	// psi. False when pressure is low.
	public void chargeAir() {
		if (!compress.getPressureSwitchValue()) {
			compress.start();
		} else {
			compress.stop();
		}
	}
	
	public boolean armsExtend() { //Extends grabbers
		solA.set(Value.kForward);
		solB.set(Value.kForward);
		return true;
	}
	
	public boolean armsRetract() { //Retracts grabbers
		solA.set(Value.kReverse);
		solB.set(Value.kReverse);
		return true;
	}
	
	public boolean flippersGrip() {
		solE.set(Value.kForward);
		return true;
	}
	
	public boolean flippersRelease() {
		solE.set(Value.kReverse);
		return true;
	}
	
	public boolean flippersAutoGrip() {
		solE.set(Value.kForward);
		Timer.delay(0.5);
		return true;
	}
	
	public boolean flippersAutoRelease() {
		solE.set(Value.kReverse);
		Timer.delay(0.5);
		return true;
	}

	public void dogLock() { //Engages the Dog Gear piston
		solF.set(Value.kForward);
	}

	public void dogRelease() { //Disengages the Dog Gear piston
		solF.set(Value.kReverse);
	}

	public void testReverse() {
		solA.set(DoubleSolenoid.Value.kReverse);
		solB.set(DoubleSolenoid.Value.kReverse);
		solC.set(DoubleSolenoid.Value.kReverse);
		solD.set(DoubleSolenoid.Value.kReverse);
		solE.set(DoubleSolenoid.Value.kReverse);
		solF.set(DoubleSolenoid.Value.kReverse);
	}

	public void testForward() {
		solA.set(DoubleSolenoid.Value.kForward);
		solB.set(DoubleSolenoid.Value.kForward);
		solC.set(DoubleSolenoid.Value.kForward);
		solD.set(DoubleSolenoid.Value.kForward);
		solE.set(DoubleSolenoid.Value.kForward);
		solF.set(DoubleSolenoid.Value.kForward);
	}

	public void testOff() {
		solA.set(DoubleSolenoid.Value.kOff);
		solB.set(DoubleSolenoid.Value.kOff);
		solC.set(DoubleSolenoid.Value.kOff);
		solD.set(DoubleSolenoid.Value.kOff);
		solE.set(DoubleSolenoid.Value.kOff);
		solF.set(DoubleSolenoid.Value.kOff);
	}

	public void cycleThrough() { //Cycles through the forward, reverse, and off states of each solenoid for testing
		for (int i = 1; i < 13; i++) {
			switch (i) {
			case 1:
				solA.set(DoubleSolenoid.Value.kForward);
				Timer.delay(1);
				break;

			case 2:
				solA.set(DoubleSolenoid.Value.kReverse);
				Timer.delay(1);
				solA.set(DoubleSolenoid.Value.kOff);
				break;

			case 3:
				solB.set(DoubleSolenoid.Value.kForward);
				Timer.delay(1);
				break;

			case 4:
				solB.set(DoubleSolenoid.Value.kReverse);
				Timer.delay(1);
				solB.set(DoubleSolenoid.Value.kOff);
				break;

			case 5:
				solC.set(DoubleSolenoid.Value.kForward);
				Timer.delay(1);
				break;

			case 6:
				solC.set(DoubleSolenoid.Value.kReverse);
				Timer.delay(1);
				solC.set(DoubleSolenoid.Value.kOff);
				break;

			case 7:
				solD.set(DoubleSolenoid.Value.kForward);
				Timer.delay(1);
				break;

			case 8:
				solD.set(DoubleSolenoid.Value.kReverse);
				Timer.delay(1);
				solD.set(DoubleSolenoid.Value.kOff);
				break;

			case 9:
				solE.set(DoubleSolenoid.Value.kForward);
				Timer.delay(1);
				break;

			case 10:
				solE.set(DoubleSolenoid.Value.kReverse);
				Timer.delay(1);
				solE.set(DoubleSolenoid.Value.kOff);
				break;

			case 11:
				solF.set(DoubleSolenoid.Value.kForward);
				Timer.delay(1);
				break;

			case 12:
				solF.set(DoubleSolenoid.Value.kReverse);
				Timer.delay(1);
				solF.set(DoubleSolenoid.Value.kOff);
				break;
			}
		}
	}
}
