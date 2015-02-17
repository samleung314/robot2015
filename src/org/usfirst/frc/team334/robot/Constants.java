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
	
	public static final double driveMuliplier = 1;
	public static final double highGearSpeed = 0.65;
	public static final double lowGearSpeed = 0.45;
	
	/*Sensors*/
	public static final double  ultraZeroPoint = 2;

	public static final double cutoffMult = 0.6;
    public static final double canTolerance = 12;
    public static final double toteTolerance = 4;

	
	/*Digital I/O*/
	public static final int leftEncoderX = 4;
	public static final int leftEncoderY = 5;
	
	public static final int rightEncoderX = 3;
	public static final int rightEncoderY = 2;

	public static final int ultrasonicInput = 0;
	public static final int ultrasonicOutput = 1;
	
	public static final int highSwitches = 6;
	public static final int lowSwitches = 7;
	
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
    
    public static final double elevatorPotLOWLimit = 0.908;
    public static final double elevatorPotHIGHLimit = 0.281;
    public static final double elevatorRawLength = elevatorPotLOWLimit - elevatorPotHIGHLimit;
    public static final double elevatorHeight = 51 + ((double)3/4); //Inches
    
    public static final double teleopRampDist = 20;
    
    public static final double distancePIDTolerance = 1.5; //+- 0.5 inch from target
    public static final double elevatorPIDTolerance = 0.7; //+- 0.5 inch from target
    public static final double turnPIDTolerance = 1; //+-1 degree tolerance from target
    public static final double ultraSonicPIDTolerance = .5;
    
    //Length of movement on elevators is 56 + 15/16 inches
    //Difference of pot limits: 0.904 - 0.216 = 0.688
    
    /*Elevator Levels (all in inches)*/
    public static final double elevatorLevelOne = 0;
    public static final double elevatorLevelTwo = 14;
    public static final double elevatorLevelThree = 28;
    public static final double elevatorLevelFour = 42;
    public static final double elevatorLevelFive = elevatorHeight;
    public static final double elevatorLevelSix = elevatorHeight;
}
