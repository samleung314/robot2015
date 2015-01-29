package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Gyro;

public class Auton{

    Robot robot;
    
    Gyro gyro;
    
    public Auton(Robot robot) {
        this.robot = robot;
        gyro = new Gyro(Constants.gyroscope);
    }

}
