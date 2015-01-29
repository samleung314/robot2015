package org.usfirst.frc.team334.robot.subsystems;

import org.usfirst.frc.team334.robot.Robot;

import org.usfirst.frc.team334.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Air {

    Robot robot;
    
    Compressor compress;
    
    DoubleSolenoid solA, solB, solC, solD ,solE, solF;

    public Air(Robot robot) {
        this.robot = robot;
        
        //Compressor is connected to pneumatic module 0
        compress = new Compressor(Constants.PCM0);
        
        /*
         * Six solenoid modules: A,B,C,D,E,F
         * Solenoids A,B,C are connected to pneumatic module 0. They operate at 60 psi
         * Solenoids D,E,F are connected to pneumatic module 1. They operate at 30 psi
        */
        
        solA = new DoubleSolenoid(Constants.PCM0, Constants.solenoidAX, Constants.solenoidAY);
        solB = new DoubleSolenoid(Constants.PCM0, Constants.solenoidBX, Constants.solenoidBY);
        solC = new DoubleSolenoid(Constants.PCM0, Constants.solenoidCX, Constants.solenoidCY);
        
        solD = new DoubleSolenoid(Constants.PCM1, Constants.solenoidDX, Constants.solenoidDY);
        solE = new DoubleSolenoid(Constants.PCM1, Constants.solenoidEX, Constants.solenoidEY);
        solF = new DoubleSolenoid(Constants.PCM1, Constants.solenoidFX, Constants.solenoidFY);
    }
    
    //Compressor will run if pressure is less than 120 psi. It will stop at 120 psi.
    public void chargeAir() {
        if (!compress.getPressureSwitchValue()) {
            compress.start();
        }
        else {
            compress.stop();
        }
    }
    
    public void lockDog() {
        
    }
    
    public void releaseDog() {
        
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

}
