package org.usfirst.frc.team334.robot.physicals;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Air {

    Robot robot;
    
    public Compressor compress;
    
    Solenoid solAX, solAY, solBX, solBY, solCX, solCY, solDX, solDY, solEX, solEY, solFX, solFY;
    
    Timer time = new Timer();

    public Air(Robot robot) {
        this.robot = robot;
        
        compress = new Compressor(Constants.PCM0);
        
        solAX = new Solenoid(Constants.PCM0, Constants.solenoid0AX );
        solAY = new Solenoid(Constants.PCM0, Constants.solenoid0AY);
        
        solBX = new Solenoid(Constants.PCM0, Constants.solenoid0BX);
        solBY = new Solenoid(Constants.PCM0, Constants.solenoid0BY);
        
        solCX = new Solenoid(Constants.PCM0, Constants.solenoid0CX);
        solCY = new Solenoid(Constants.PCM0, Constants.solenoid0CY);
        
        solDX = new Solenoid(Constants.PCM1, Constants.solenoid1DX);
        solDY = new Solenoid(Constants.PCM1, Constants.solenoid1DY);
        
        solEX = new Solenoid(Constants.PCM1, Constants.solenoid1EX);
        solEY = new Solenoid(Constants.PCM1, Constants.solenoid1EY);
        
        solFX = new Solenoid(Constants.PCM1, Constants.solenoid1FX);
        solFY = new Solenoid(Constants.PCM1, Constants.solenoid1FY);
    }
    
    //Charge when pressure is low. True when pressure is low.
    public void chargeAir() {
        if (!compress.getPressureSwitchValue()) {
            compress.start();
        }
        else {
            compress.stop();
        }
    }
    
    public void testPist() {
    	solAX.set(true);
    	solAY.set(true);
    	
    	solBX.set(true);
    	solBY.set(true);
    	
    	solCX.set(true);
    	solCY.set(true);
    	
    	solDX.set(true);
    	solDY.set(true);
    	
    	solEX.set(true);
    	solEY.set(true);

    	solFX.set(true);
    	solFY.set(true);
    	
    	time.delay(1);
    	
    	solAX.set(false);
    	solAY.set(false);
    	
    	solBX.set(false);
    	solBY.set(false);
    	
    	solCX.set(false);
    	solCY.set(false);
    	
    	solDX.set(false);
    	solDY.set(false);
    	
    	solEX.set(false);
    	solEY.set(false);

    	solFX.set(false);
    	solFY.set(false);
    	
    	time.delay(1);
    }
    
    public void testPist2() {
    	solFX.set(false);
    	solFY.set(true);
    }

}
