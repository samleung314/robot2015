package org.usfirst.frc.team334.robot.human;

import org.usfirst.frc.team334.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Smartdashboard {

	Controllers control;
	Drivetrain drive;
	
	public static double chooseStick;

	public Smartdashboard(Controllers control, Drivetrain drive){
		this.control = control;
		this.drive = drive;
	}
	
	//Lets you choose between xBox and joystick drive by entering 0 or 1 in smartdash field
	public void testCommands() {
		chooseStick = SmartDashboard.getNumber("Choose Joystick", 0);
	}
	
	//Places PID object onto smartdash
	public void displayPIDs() {
		SmartDashboard.putData("Drive Straight PID", drive.straightPID);
	}
}
