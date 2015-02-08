package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Gyro;

public class Auton {

    Robot robot;

    public Gyro gyro;

    boolean seg1 = false, 
    		seg2 = false, 
    		seg3 = false, 
    		seg4 = false, 
    		turn1 = false, 
    		turn2 = false, 
    		turn3 = false, 
    		turn4 = false;

    public Auton(Robot robot) {
        this.robot = robot;
        gyro = new Gyro(Constants.gyroscope);
    }

    public void squareSequence(double speed) { //Makes the robot drive in a 38x38 square
        if (!seg1) {
            seg1 = robot.straight.travelDistance(38, speed);
        }

        if ((seg1) && (!turn1)) {
            turn1 = robot.turn.turnDegrees(90, 0.5);
        }

        if ((turn1) && (!seg2)) {
            seg2 = robot.straight.travelDistance(38, speed);
        }

        if ((seg2) && (!turn2)) {
            turn2 = robot.turn.turnDegrees(90, 0.5);
        }

        if ((turn2) && (!seg3)) {
            seg3 = robot.straight.travelDistance(38, speed);
        }

        if ((seg3) && (!turn3)) {
            turn3 = robot.turn.turnDegrees(90, 0.5);
        }

        if ((turn3) && (!seg4)) {
            seg4 = robot.straight.travelDistance(38, speed);
        }

        if ((seg4) && (!turn4)) {
            turn4 = robot.turn.turnDegrees(90, 0.5);
        }
    }

}
