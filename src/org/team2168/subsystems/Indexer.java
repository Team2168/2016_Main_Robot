package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.indexer.DriveIndexerWithJoysticks;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Indexer subsystem
 *@author David M
 */
public class Indexer extends Subsystem {

	private Victor roller;
	private static Indexer instance = null;

	/**
	 * Default constructors for Index subsystem 	
	 */
	private Indexer() {
		roller = new Victor(RobotMap.INDEX_WHEEL);
	}

	/**
	 * Returns an instance of this class.
	 * @return an instance of this class. 
	 */
	public static Indexer getInstance(){
		if(instance == null) {
			instance = new Indexer();
		}
		return instance;
	}

	/**
	 * Set the speed of the index roller.
	 * @param speed -1.0 to 1.0. positive will move the ball inwards.
	 *              negative, will move the ball outwards. 
	 */
	public void setSpeed(double speed) {
		roller.set(speed);
	}
	
	/**
	 * Set the default command for a subsystem here.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveIndexerWithJoysticks());
	}
}

