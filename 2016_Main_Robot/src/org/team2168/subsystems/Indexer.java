package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.Indexer.DriveIndexerWithJoysticks;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Index Subsystem
 *@author David M
 */
public class Indexer extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.


	private Victor roller;

	static Indexer instance = null;

	/**
	 * Default constructors for Index subsystem 	
	 */

	private Indexer()
	{
		roller = new Victor(RobotMap.INDEX_WHEEL);
	}


	/**
	 * Returns Index singleton object
	 * @return is the current index object 
	 */

	public static Indexer getInstance()
	{
		if(instance == null)
		{
			instance = new Indexer();
		}
		return instance;
	}

	/**
	 * Takes in a double and if positive, will move the ball inwards, if negative, will move the ball outwards
	 * @param is a speed from -1 to 1
	 */

	public void setSpeed(double speed)
	{
		roller.set(speed);
	}



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveIndexerWithJoysticks());
	}
}

