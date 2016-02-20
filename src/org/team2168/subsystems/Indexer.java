package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.indexer.DriveIndexerWithJoysticks;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Indexer subsystem
 *@author David M
 */
public class Indexer extends Subsystem {

	private Victor indexerRoller;	
	private AnalogInput indexerDistanceSensor;
	
	private static Indexer instance = null;
	
	//TODO calibrate values
	private final double MIN_SENSOR_VOLTAGE = 0.5;
	private final double BOULDER_PRESENT_VOLTAGE = 2.6;
	private final double IR_SENSOR_AVG_GAIN = 0.5;
	private double averagedBoulderDistance = 0.0;

	/**
	 * Default constructors for Index subsystem 	
	 */
	private Indexer() {

		indexerRoller = new Victor(RobotMap.INDEXER_WHEEL);
		indexerDistanceSensor = new AnalogInput(RobotMap.INDEXER_DISTANCE_SENSOR);

		indexerRoller.setExpiration(0.1);
		indexerRoller.setSafetyEnabled(true);

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
		indexerRoller.set(speed);
	}
	
	public boolean isBoulderPresent() {
		return Robot.indexer.getAveragedRawBoulderDistance() > BOULDER_PRESENT_VOLTAGE;
	}
	
	/**
	 * Returns the raw voltage from the shooter distance sensor
	 * @return double voltage
	 */
	public double getRawBoulderDistance() {
		return Util.max(MIN_SENSOR_VOLTAGE, indexerDistanceSensor.getVoltage());
	}
	
	/**
	 * Note, this method should be called from a loop to prevent data from getting stale.
	 * @return the average voltage from the shooter distance sensor
	 */
	public double getAveragedRawBoulderDistance() {
		averagedBoulderDistance = Util.runningAverage(getRawBoulderDistance(),
				averagedBoulderDistance, IR_SENSOR_AVG_GAIN);
		return averagedBoulderDistance;
	}
	
	/**
	 * Set the default command for a subsystem here.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveIndexerWithJoysticks());
	}
}

