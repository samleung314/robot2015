package org.usfirst.frc.team334.robot.physicals;

import edu.wpi.first.wpilibj.VictorSP;

public class Mechanics {

    public VictorSP leftVicA;
    public VictorSP leftVicB;

    public VictorSP rightVicA;
    public VictorSP rightVicB;
    
    public VictorSP elevatorVicA;
    public VictorSP elevatorVicB;

    public DoubleVics leftVics;
    public DoubleVics rightVics;
    public DoubleVics elevatorVics;

    public Mechanics() {
        leftVicA = new VictorSP(Constants.leftVictorA);
        leftVicB = new VictorSP(Constants.leftVictorB);

        rightVicA = new VictorSP(Constants.rightVictorA);
        rightVicB = new VictorSP(Constants.rightVictorB);
        
        elevatorVicA = new VictorSP(Constants.elevatorVictorA);
        elevatorVicB = new VictorSP(Constants.elevatorVictorB);
        
        leftVics = new DoubleVics(leftVicA, leftVicB);
        rightVics = new DoubleVics(rightVicA, rightVicB);
        elevatorVics = new DoubleVics(elevatorVicA, elevatorVicB);
    }


}
