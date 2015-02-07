package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Constants;
import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Gyro;

public class Auton{

    Robot robot;
    
    public Gyro gyro;
    
    boolean seg1, seg2, seg3, seg4, turn1, turn2, turn3, turn4;
    
    public Auton(Robot robot) {
        this.robot = robot;
        gyro = new Gyro(Constants.gyroscope);
    }
    
    public void squareSequence(double speed) {
        if (!seg1) {
            seg1 = robot.straight.travelDistance(38, speed);
        }

        if ((seg1) && (!turn1)) {
        	turn2 = robot.turn.turnDegrees(90, 0.5);
        }

        if ((turn1) && (!seg2)) {
            seg2 = robot.straight.travelDistance(36, speed);
        }

        if ((seg2) && (!turn2)) {
            turn2 = robot.turn.turnDegrees(90, 0.5);
        }

        if ((turn2) && (!seg3)) {
            seg3 = robot.straight.travelDistance(38, speed);
        }

        if ((seg3) && (!turn3)) {
        	turn2 = robot.turn.turnDegrees(90, 0.5);
        }

        if ((turn3) && (!seg4)) {
            seg4 = robot.straight.travelDistance(36, speed);
        }

        if ((seg4) && (!turn4)) {
        	turn2 = robot.turn.turnDegrees(90, 0.5);
        }
    }

}
