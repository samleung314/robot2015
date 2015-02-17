package org.usfirst.frc.team334.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team334.robot.*;

public class Drivetrain {

    Robot robot;

    public final VictorSP leftVic;
    public final VictorSP rightVic;

    public RobotDrive chasisDrive;

    public Drivetrain(Robot robot) {
        this.robot = robot;

        leftVic = new VictorSP(Constants.leftVictor);
        rightVic = new VictorSP(Constants.rightVictor);

        chasisDrive = new RobotDrive(leftVic, rightVic);
    }

    public void victorDrive(double leftSpeed, double rightSpeed) {
    	leftVic.set(leftSpeed);
    	rightVic.set(-rightSpeed);
    }

}
