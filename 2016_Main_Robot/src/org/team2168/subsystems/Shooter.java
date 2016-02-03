package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.shooter.DriveShooterWithJoysticks;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shooter Subsystem 
 * @author Krystina
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon shooter1;
	private Talon shooter2;
	
	static Shooter instance = null;
		
	/**
	 * Private singleton constructor for Shooter_Superman
	 * 
	 */
	
	private Shooter ()
	{
		shooter1 = new Talon (RobotMap.SHOOTER_WHEEL_1);
		shooter2 = new Talon (RobotMap.SHOOTER_WHEEL_2);
	}
	
	/**
	 * singleton object for Shooter_Superman
	 * @return rerturns the shooter singleton object
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
	 * @param speed -1 to 1 if given a positive value the ball will move inward. If negative the ball will move outward.
	 * @author Krystina
	 */
	public void driveShooter(double speed)
	{
		shooter1.set(speed);
		shooter2.set(speed);
		
	}
	
	/**
	 * Takes in a speed and drives the first shooter wheel in a positive or inward direction
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveShooterWheel1(double speed)
	{
		if(RobotMap.reverseShooterWheel1)
			speed = -speed;
		
		shooter1.set(speed);
	}
	
	/**
	 * Takes in a speed and drives the second shooter wheel in a positive or inward direction
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveShooterWheel2(double speed)
	{
		if(RobotMap.reverseShooterWheel2)
			speed = -speed;
			
		shooter2.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveShooterWithJoysticks());
    }
}

