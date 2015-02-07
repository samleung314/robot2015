package org.usfirst.frc.team334.robot.human;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team334.robot.Robot;
import org.usfirst.frc.team334.robot.automaton.*;
import org.usfirst.frc.team334.robot.subsystems.Air;

public class Smartdashboard {

    Robot robot;

    public SendableChooser autoChoose, testChoose;
    public Command autoCommand;

    public Smartdashboard(Robot robot) {
        this.robot = robot;

        //Add objects to the SendableChooser for autonomous 
        autoChoose = new SendableChooser();
        autoChoose.addDefault("Do Nothing", new Default());
        autoChoose.addObject("Ramping PID", robot.ramp);
        //choose.addObject("Straight PID", robot.straight.travelDistance(distance, speed));
        //choose.addObject("Turning PID", robot.turn.turnDegrees(degrees, turnSpeed));

        SmartDashboard.putData("Choose Auton Mode", autoChoose);

        //Add objects to the SendableChooser for testing
        testChoose = new SendableChooser();
        testChoose.addDefault("Do Nothing", new Default());
        testChoose.addObject("Cycle Solenoids", (Runnable) () -> robot.air.cycleThrough());
        //testChoose.addObject("Cycle Solenoids", robot.air.cycleThrough());
    }

    // Places PID objects onto SmartDashboard
    public void displayPIDs() {
        SmartDashboard.putData("Ramping PID", robot.ramp.rampPID);
        //SmartDashboard.putData("Straight PID", robot.straight.straightPID);
        //SmartDashboard.putData("Turn PID", robot.turn.turnPID);      
        //SmartDashboard.putData("Elevate PID", robot.elevate.elevatorPID);
    }

    // Places sensor values onto SmartDashboard
    public void displaySensors() {
        SmartDashboard.putNumber("Left Encoder", robot.encode.leftEnc.getDistance());
        SmartDashboard.putNumber("Right Encoder", robot.encode.rightEnc.getDistance());
        SmartDashboard.putNumber("Average Encoder", robot.encode.averageDist());

        SmartDashboard.putNumber("Elevtaor Pot", robot.elevate.elevatorPot.get());

        SmartDashboard.putNumber("Gyroscope", robot.auto.gyro.getAngle());
    }
}
