package org.usfirst.frc.team334.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team334.robot.*;

public class Drivetrain{

    Robot robot;
    
    private final VictorSP leftVicA;
    private final VictorSP leftVicB;

    private final VictorSP rightVicA;
    private final VictorSP rightVicB;
    
    public DoubleVics leftVics;
    public DoubleVics rightVics;
    
    public RobotDrive chasisDrive;

    public Drivetrain(Robot robot) {
        this.robot = robot;
        
        leftVicA = new VictorSP(Constants.leftVictorA);
        leftVicB = new VictorSP(Constants.leftVictorB);

        rightVicA = new VictorSP(Constants.rightVictorA);
        rightVicB = new VictorSP(Constants.rightVictorB);
        
        leftVics = new DoubleVics(leftVicA, leftVicB);
        rightVics = new DoubleVics(rightVicA, rightVicB);
        
        chasisDrive = new RobotDrive(leftVics, rightVics);
    }
    
    public void manualVicsDrive(double leftSpeed, double rightSpeed) {
        leftVicA.set(leftSpeed);
        leftVicB.set(leftSpeed);

        rightVicA.set(-rightSpeed);
        rightVicB.set(-rightSpeed);
    }
    
    public void doubleVicsDrive(double leftSpeed, double rightSpeed) {
        leftVics.set(leftSpeed);
        rightVics.set(rightSpeed);
    }

}
