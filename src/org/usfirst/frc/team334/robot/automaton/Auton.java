package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

public class Auton {

    Robot robot;

    private boolean pickupDone, elevatorUnlockA, flippersInA, elevatorZeroA, flippersOutA, elevatorToteLvl, elevatorLockA,
    			    putdownDone, elevatorUnlockB, elevatorZeroB, flippersOutB;
    
    private int pickup, putdown;
    
    private double levelZero = 0, levelOne = 0;
    
    public Auton(Robot robot) {
        this.robot = robot;
        
        pickup = 1;
        pickupDone = flippersInA = elevatorZeroA = flippersOutA = elevatorToteLvl = false;
        
        putdown = 1;
        putdownDone = elevatorUnlockB = elevatorZeroB = flippersOutB = false;
    }

    public boolean pickUpTote() {

    	switch(pickup) {
	    	case 1: elevatorUnlockA = robot.elevate.elevatorRelease();
					nextStep(pickup, elevatorUnlockA);
					break;
			
	    	case 2: flippersInA = robot.air.flippersIn();
					nextStep(pickup, flippersInA);
					break;
    	
	    	case 3: elevatorZeroA = robot.elevate.elevatorHeight(levelZero);
					nextStep(pickup, elevatorZeroA);
					break;
			
    		case 4: flippersOutA = robot.air.flippersOut();
		    		nextStep(pickup, flippersOutA);
		    		break;
    		
    		case 5: elevatorToteLvl = robot.elevate.elevatorHeight(levelOne);
		    		nextStep(pickup, elevatorToteLvl);
		    		break;
		    		
    		case 6: elevatorLockA = robot.elevate.elevatorLock();
		    		nextStep(pickup, elevatorLockA);
		    		break;
	
    		case 7: pickupDone = true;
    				break;
    		
    		default: System.out.println("Elevator Pickup Defaulting");
    				 break;
    	}
    	
    	//Resets method for reuse
    	if(pickupDone) {
    		pickupDone = elevatorUnlockA = flippersInA = elevatorZeroA = flippersOutA = elevatorToteLvl = elevatorLockA = false;
    		pickup = 1;
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean putDownStack() {

    	switch(putdown) {
	    	case 1: elevatorUnlockB = robot.elevate.elevatorRelease();
					nextStep(putdown, elevatorUnlockB);
					break;
					
	    	case 2: elevatorZeroB = robot.elevate.elevatorHeight(levelZero);
					nextStep(putdown, elevatorZeroB);
					break;
			
	    	case 3: flippersOutB = robot.air.flippersOut();
		    		nextStep(putdown, flippersOutB);
		    		break;
    		
	    	case 4: putdownDone = true;
	    			break;
    		
    		default: System.out.println("Elevator Put Down Defaulting");
    				 break;
    	}
    	
    	//Resets method for reuse
    	if(putdownDone) {
    		putdownDone = elevatorUnlockB = elevatorZeroB = flippersOutB = false;
    		putdown = 1;
    		return true;
    	}
    	
    	return false;
    }
    
    public void nextStep(int step, boolean action) {
    	if (action) {
    		step++;
    	}
    }
}
