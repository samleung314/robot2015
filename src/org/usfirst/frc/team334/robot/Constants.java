package org.usfirst.frc.team334.robot;

public class Constants {
	
	/*Victors*/
	public static final int leftVictorA = 4;
	public static final int leftVictorB = 5;
	
	public static final int rightVictorA = 1;
	public static final int rightVictorB = 2;
	
	public static final int elevatorVictorA = 0;
	public static final int elevatorVictorB = 3;
	
	/*Controllers*/
	public static final int xbox = 0;
	public static final int leftJoystick = 1;
	public static final int rightJoystick = 2;
	
	public static final double driveMuliplier = 0.55;
	public static final double highGearSpeed = 0.65;
	public static final double lowGearSpeed = 0.45;
	
	/*Sensors*/
	public static final int highSwitchLeft = 4;
	public static final int lowSwitchLeft = 5;
	
	public static final int highSwitchRight = 4;
	public static final int lowSwitchRight = 5;
	
	/*Digital I/O*/
	public static final int leftEncoderX = 4;
	public static final int leftEncoderY = 5;
	
	public static final int rightEncoderX = 3;
	public static final int rightEncoderY = 2;
	
	/*Analog*/
	//Note: Analog ports 0,1 are reserved for accumulator sensors such as the gyroscope
	public static final int gyroscope = 0;
	public static final int elevatorPot = 1;
	
	/*Main Pneumatic Controller Module*/
	public static final int PCM0 = 0;

	public static final int solenoidAX = 0;
	public static final int solenoidAY = 7;
	
	public static final int solenoidBX = 1;
	public static final int solenoidBY = 6;
	
	public static final int solenoidCX = 2;
	public static final int solenoidCY = 5;
	
	
	/*Secondary Pneumatic Controller Module*/
	public static final int PCM1 = 1;

    public static final int solenoidDX = 0;
    public static final int solenoidDY = 7;

    public static final int solenoidEX = 1;
    public static final int solenoidEY = 6;

    public static final int solenoidFX = 2;
    public static final int solenoidFY = 5;

    /*ETC*/
    public static final double encoderDistancePerPulse = ((double)9/1088) * Math.PI; //(18/34)*((4*Math.PI)/256);
    
    public static final double elevatorPotLOWLimit = 0.984;
    public static final double elevatorPotHIGHLimit = 0.203;
    public static final double elevatorRawLength = 0.781;
    public static final double elevatorMovementLength = 64 + 1/8; //Inches
    
    public static final double turnPIDTolerance = 1;
    public static final double distancePIDTolerance = 0.5;
    
    //Length of movement on elevators is 64 1/8 inches
    //Difference of pot limits: 0.984 - 0.203 = 0.781
}
