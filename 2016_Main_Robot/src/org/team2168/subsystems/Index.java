package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.Index.DriveIndexWheel;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Index Subsystem
 *@author David M
 */
public class Index extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	private Victor roller;
	
	static Index instance = null;
	
	/**
	 * Default constructors for Index subsystem 	
	 */
	
	private Index()
	{
		roller = new Victor(RobotMap.INDEX_WHEEL);
	}
	
	
	/**
	 * Returns Index singleton object
	 * @return is the current index object 
	 */
	
	public static Index getInstance()
	{
		if(instance == null)
			instance = new Index();
			return instance;
	}
	
	/**
	 * Takes in a double speed at sets it to roller
	 * @param is a speed from -1 to 1
	 */
	 
	public void spinWheel(double speed)
	{
		roller.set(speed);
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveIndexWheel());
    }
}

