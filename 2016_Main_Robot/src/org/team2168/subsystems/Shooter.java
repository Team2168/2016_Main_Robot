package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon shooter1;
	private Talon shooter2;
	
	static Shooter instance = null;
		
	/**
	 * Private singleton constructor for Shooter_Superman
	 * @author Krystina
	 */
	
	private Shooter ()
	{
		shooter1 = new Talon (RobotMap.shooterWheel1);
		shooter2 = new Talon (RobotMap.shooterWheel2);
	}
	
	/**
	 * singleton object for Shooter_Superman
	 * @author Krystina
	 */
	
	public static Shooter getInstance()
	{
		if (instance == null)
			instance = new Shooter();
		
		return instance;
	}
	
	/**
	 * Takes in a speed and drives the Shooter_Superman wheels in the same directions
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveShooter(double speed)
	{
		shooter1.set(speed);
		shooter2.set(speed);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

