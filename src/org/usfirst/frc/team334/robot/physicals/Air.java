package org.usfirst.frc.team334.robot.physicals;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Air {

    Robot robot;
    
    Compressor compress;
    
    DoubleSolenoid solA, solB, solC, solD ,solE, solF;

    public Air(Robot robot) {
        this.robot = robot;
        
        compress = new Compressor(Constants.PCM0);
        
        /*
         * Six solenoid modules: A,B,C,D,E,F
         * Solenoids A,B,C are connected to pneumatic module 0. They operate at 60 psi
         * Solenoids D,E,F are connected to pneumatic module 1. They operate at 30 psi
         * Compressor is connected to pneumatic module 0
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

    public void pistReverse() {
        solD.set(DoubleSolenoid.Value.kReverse);
        solE.set(DoubleSolenoid.Value.kReverse);
        solF.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void pistForward() {
        solD.set(DoubleSolenoid.Value.kForward);
        solE.set(DoubleSolenoid.Value.kForward);
        solF.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pistOff() {
        solD.set(DoubleSolenoid.Value.kOff);
        solE.set(DoubleSolenoid.Value.kOff);
        solF.set(DoubleSolenoid.Value.kOff);
    }

}
