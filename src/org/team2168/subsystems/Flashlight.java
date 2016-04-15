package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.pneumatics.StartCompressor;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the compressor and reads pressure sensor
 * @author Ben Waid
 */
public class Flashlight extends Subsystem {
    
    private Relay flashlight;
     
    private static Flashlight instance = null;
    
    /**
     * Private constructor for the Pneumatics subsystem
     */
    private Flashlight(){
    	flashlight = new Relay(RobotMap.FLASHLIGHT_RELAY);
    }
    
    /**
     * Singleton constructor for Pneumatics subsystem
     * @return singleton instance of Pneumatics subsystem
     */
    public static Flashlight getInstance(){
    	if(instance == null)
    		instance = new Flashlight();
    	return instance;
    }
    
    public void setFlashlightOn() {
    	flashlight.set(Value.kForward);
    }
    
    public void setFlashlightOff() {
    	flashlight.set(Value.kOff);
    }
    
    public boolean isFlashlightOff() {
    	return (flashlight.get() == Value.kOff);
    }
    
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}
    
}

