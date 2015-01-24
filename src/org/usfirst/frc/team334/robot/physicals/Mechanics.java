package org.usfirst.frc.team334.robot.physicals;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class Mechanics {

    public VictorSP leftVicA;
    public VictorSP leftVicB;

    public VictorSP rightVicA;
    public VictorSP rightVicB;

    public DoubleVics leftVics;
    public DoubleVics rightVics;
    
    public VictorSP elevatorVicA;
    public VictorSP elevatorVicB;

    public RobotDrive tank;

    public Mechanics() {
        leftVicA = new VictorSP(Constants.leftVictorA);
        leftVicB = new VictorSP(Constants.leftVictorB);

        rightVicA = new VictorSP(Constants.rightVictorA);
        rightVicB = new VictorSP(Constants.rightVictorB);

        leftVics = new DoubleVics(leftVicA, leftVicB);
        rightVics = new DoubleVics(rightVicA, rightVicB);

        tank = new RobotDrive(leftVics, rightVics);
    }


}
