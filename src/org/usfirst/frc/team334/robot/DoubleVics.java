package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

// This class was created to combine the two victor transmissions of the robot to act as one victor object
public class DoubleVics implements SpeedController {

    private VictorSP vic1;
    private VictorSP vic2;
    private double vicSpeed;

    public DoubleVics(VictorSP vsp1, VictorSP vsp2) {
        vic1 = vsp1;
        vic2 = vsp2;
    }

    @Override
    public void pidWrite(double output) {
        set(output);
    }

    @Override
    public double get() {

        return vicSpeed;
    }

    @Override
    public void set(double speed, byte syncGroup) {
        vicSpeed = speed;
        vic1.set(vicSpeed);
        vic2.set(vicSpeed);
    }

    @Override
    public void set(double speed) {
        vicSpeed = speed;
        vic1.set(vicSpeed);
        vic2.set(vicSpeed);
    }

    @Override
    public void disable() {
        vic1.set(0);
        vic2.set(0);
    }

}
