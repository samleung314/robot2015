package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

public class Auton {

    Robot robot;

    public boolean seg1 = false, 
    		seg2 = false, 
    		seg3 = false, 
    		seg4 = false, 
    		turn1 = false, 
    		turn2 = false, 
    		turn3 = false, 
    		turn4 = false;
    
    public String currentState;

    public Auton(Robot robot) {
        this.robot = robot;
    }

    public void squareSequence(double speed) { //Makes the robot drive in a 38x38 square at a specified speed
        if (!seg1) {
            seg1 = robot.straight.travelDistance(38, speed);
            currentState = "FIRST of 4 segments";
        }

        if ((seg1) && (!turn1)) {
            turn1 = robot.turn.turnDegrees(90, 0.5);
            currentState = "FIRST of 4 turns";
        }

        if ((turn1) && (!seg2)) {
            seg2 = robot.straight.travelDistance(38, speed);
            currentState = "SECOND of 4 segments";
        }

        if ((seg2) && (!turn2)) {
            turn2 = robot.turn.turnDegrees(90, 0.5);
            currentState = "SECOND of 4 turns";
        }

        if ((turn2) && (!seg3)) {
            seg3 = robot.straight.travelDistance(38, speed);
            currentState = "THIRD of 4 segments";
        }

        if ((seg3) && (!turn3)) {
            turn3 = robot.turn.turnDegrees(90, 0.5);
            currentState = "THIRD of 4 turns";
        }

        if ((turn3) && (!seg4)) {
            seg4 = robot.straight.travelDistance(38, speed);
            currentState = "LAST of 4 segments";
        }

        if ((seg4) && (!turn4)) {
            turn4 = robot.turn.turnDegrees(90, 0.5);
            currentState = "LAST of 4 turns";
        }
    }

}
