dswfdewffqwfwqfqwwqfdsafasfsadfsadsadsadpackage org.usfirst.frc.team334.robot.physicals;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Air {

    Robot robot;
    
    Compressor compress;
    
    Solenoid solAX, solAY, solBX, solBY, solCX, solCY, solDX, solDY, solEX, solEY, solFX, solFY;

    public Air(Robot robot) {
        this.robot = robot;
        
        compress = new Compressor(Constants.PCM1);
        
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
        if (compress.getPressureSwitchValue()) {
            compress.start();
        }
        else {
            compress.stop();
        }
    }

}
