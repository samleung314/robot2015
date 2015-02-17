package org.usfirst.frc.team334.robot.automaton;

import org.usfirst.frc.team334.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class Auton {

    Robot robot;

    private boolean pickupToteDone, elevatorUnlockA, flippersReleaseA, elevatorZeroA, flippersGripA, elevatorToteLvl, elevatorLockA,
    				pickupContainterDone, elevatorUnlockB, flippersReleaseB, elevatorZeroB, flippersGripB, elevatorContainerLvl, elevatorLockB,
    			    putdownDone, elevatorUnlockC, elevatorZeroC, flippersReleaseC;
    
    private int pickupTote, pickupContainer, putdown;
    
    private double levelZero = 0, toteLevel = 14, containterLevel = 30;
    
    public Auton(Robot robot) {
        this.robot = robot;
        
        pickupTote = 1;
        pickupToteDone = elevatorUnlockA = flippersReleaseA = elevatorZeroA = flippersGripA = elevatorToteLvl = elevatorLockA = false;
        
        putdown = 1;
        putdownDone = elevatorUnlockC = elevatorZeroC = flippersReleaseC = false;
        
        pickupContainer = 1;
        pickupContainterDone = elevatorUnlockB = flippersReleaseB = elevatorZeroB = flippersGripB = elevatorContainerLvl = elevatorLockB = false;
        
    }

    public boolean dropLiftTote() {
    	switch(pickupTote) {
	    	case 1: elevatorUnlockA = robot.elevator.elevatorRelease();
					nextStep(pickupTote, elevatorUnlockA);
					break;
			
	    	case 2: flippersReleaseA = robot.air.flippersRelease();
	    			Timer.delay(0.5);
					nextStep(pickupTote, flippersReleaseA);
					break;
    	
	    	case 3: elevatorZeroA = robot.pot.elevatePID(levelZero);
					nextStep(pickupTote, elevatorZeroA);
					break;
			
    		case 4: flippersGripA = robot.air.flippersGrip();
    				Timer.delay(0.5);
		    		nextStep(pickupTote, flippersGripA);
		    		break;
    		
    		case 5: elevatorToteLvl = robot.pot.elevatePID(toteLevel);
		    		nextStep(pickupTote, elevatorToteLvl);
		    		break;
		    		
    		case 6: elevatorLockA = robot.elevator.elevatorLock();
		    		nextStep(pickupTote, elevatorLockA);
		    		break;
	
    		case 7: pickupToteDone = true;
    				break;
    		
    		default: System.out.println("Tote Pickup Defaulting");
    				 break;
    	}
    	
    	//Resets method for reuse
    	if(pickupToteDone) {
    		pickupToteDone = elevatorUnlockA = flippersReleaseA = elevatorZeroA = flippersGripA = elevatorToteLvl = elevatorLockA = false;
    		pickupTote = 1;
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean dropLiftContainer() {
    	switch(pickupContainer) {
	    	case 1: elevatorUnlockB = robot.elevator.elevatorRelease();
					nextStep(pickupContainer, elevatorUnlockB);
					break;
			
	    	case 2: flippersReleaseB = robot.air.flippersRelease();
	    			Timer.delay(0.5);
					nextStep(pickupContainer, flippersReleaseB);
					break;
    	
	    	case 3: elevatorZeroB = robot.pot.elevatePID(levelZero);
					nextStep(pickupContainer, elevatorZeroB);
					break;
			
    		case 4: flippersGripB = robot.air.flippersGrip();
    				Timer.delay(0.5);
		    		nextStep(pickupContainer, flippersGripB);
		    		break;
    		
    		case 5: elevatorContainerLvl = robot.pot.elevatePID(containterLevel);
		    		nextStep(pickupContainer, elevatorContainerLvl);
		    		break;
		    		
    		case 6: elevatorLockB = robot.elevator.elevatorLock();
		    		nextStep(pickupContainer, elevatorLockB);
		    		break;
	
    		case 7: pickupContainterDone = true;
    				break;
    		
    		default: System.out.println("Container Pickup Defaulting");
    				 break;
    	}
    	
    	//Resets method for reuse
    	if(pickupContainterDone) {
    		pickupContainterDone = elevatorUnlockB = flippersReleaseB = elevatorZeroB = flippersGripB = elevatorContainerLvl = elevatorLockB = false;
    		pickupContainer = 1;
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean putDownStack() {

    	switch(putdown) {
	    	case 1: elevatorUnlockC = robot.elevator.elevatorRelease();
	    			Timer.delay(0.5);
					nextStep(putdown, elevatorUnlockC);
					break;
					
	    	case 2: elevatorZeroC = robot.pot.elevatePID(levelZero);
					nextStep(putdown, elevatorZeroC);
					break;
			
	    	case 3: flippersReleaseC = robot.air.flippersRelease();
	    			Timer.delay(0.5);
		    		nextStep(putdown, flippersReleaseC);
		    		break;
    		
	    	case 4: putdownDone = true;
	    			break;
    		
    		default: System.out.println("Elevator Put Down Defaulting");
    				 break;
    	}
    	
    	//Resets method for reuse
    	if(putdownDone) {
    		putdownDone = elevatorUnlockC = elevatorZeroC = flippersReleaseC = false;
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
