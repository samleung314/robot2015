package org.usfirst.frc.team334.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc.team334.robot.Robot;

public class Drivetrain {

    Robot robot;

    public PIDController straightPID;
    public PIDController turnPID;
    public PIDController distancePID;

    public double sP, sI, sD, tP, tI, tD;

    public Drivetrain(Robot robot) {
        this.robot = robot;

        straightPID = new PIDController(sP, sI, sD, robot.sensors.gyro,
                robot.mech.leftVics);
        // turnPID = new PIDController(tP, tI, tD, sensors.gyro, )
    }
    
    public void manualVicsDrive(double leftSpeed, double rightSpeed) {
        robot.mech.leftVicA.set(leftSpeed);
        robot.mech.leftVicB.set(leftSpeed);

        robot.mech.rightVicA.set(-rightSpeed);
        robot.mech.rightVicB.set(-rightSpeed);
    }

}
