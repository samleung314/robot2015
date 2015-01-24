package org.usfirst.frc.team334.robot.modes;

import org.usfirst.frc.team334.robot.human.*;
import org.usfirst.frc.team334.robot.subsystems.*;

public class Teleop {
	
    Controllers control;
    Drivetrain drive;
    Smartdashboard smart;
    
	public Teleop(Controllers control, Drivetrain drive, Smartdashboard smart) {
		this.control = control;
		this.smart = smart; 
		this.drive = drive;
	}
	
	public void teleInit() {
		//drive.straightPID.enable();
	}
	
	public void teleopPeri(){
		chooseController();
		smart.displayPIDs();
	}
	
	//Choose betwen xBox drive and joystick drive
	public void chooseController(){
		if (Smartdashboard.chooseStick == 0){
			control.xBoxDrive();
		}
		else if (Smartdashboard.chooseStick == 1){
			control.joystickDrive();
		}
	}

}
