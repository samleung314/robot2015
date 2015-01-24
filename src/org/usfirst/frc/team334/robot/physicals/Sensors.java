package org.usfirst.frc.team334.robot.physicals;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Sensors {

    public Gyro gyro;
    public AnalogPotentiometer elevatorPot;

    public Sensors() {
        gyro = new Gyro(Constants.gyroscope);
        elevatorPot = new AnalogPotentiometer(Constants.gyroscope);
    }

}
