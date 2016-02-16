package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.intakeroller.IntakeWithJoystick;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is an intake system.
 * @author jkaroul
 */
public class IntakeRoller extends Subsystem {
    
	private Victor intakeWheel1;
	private Victor intakeWheel2;
	private AnalogInput intakeDistanceSensor;
	
	//TODO calibrate values
		private final double MIN_SENSOR_VOLTAGE = 0.5;
		private final double IR_SENSOR_AVG_GAIN = 0.5;
		private double averagedBoulderDistance = 0.0;
	
	private static IntakeRoller instance = null;
	
	/**
	 * private constructor for the Singleton intake subsystem
	 * @author jkaroul
	 */
	private IntakeRoller()
	{
		intakeWheel1 = new Victor(RobotMap.INTAKE_WHEEL_1);
		intakeWheel1.setExpiration(0.1);
		intakeWheel1.setSafetyEnabled(true);
		
		intakeWheel2 = new Victor(RobotMap.INTAKE_WHEEL_2);
		intakeWheel2.setExpiration(0.1);
		intakeWheel2.setSafetyEnabled(true);
		
		intakeDistanceSensor = new AnalogInput(RobotMap.INTAKE_DISTANCE_SENSOR);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motors
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntake(double speed)
	{
		intakeWheel1.set(speed);
		intakeWheel2.set(speed);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motor 1
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntakeWheel1(double speed)
	{
		if(RobotMap.REVERSE_INTAKE_WHEEL_1)
			speed = -speed;
			
		intakeWheel1.set(speed);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motor 2
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntakeWheel2(double speed)
	{
		if(RobotMap.REVERSE_INTAKE_WHEEL_2)
			speed = -speed;
			
		intakeWheel2.set(speed);
	
	}
	
	/**
	 * returns a singleton object of the intake subsystem
	 * @return returns the current intake object
	 * @author jkaroul
	 */
	
	public static IntakeRoller getInstance()
	{
		if(instance == null)
			instance = new IntakeRoller();
		
		return instance;
	}
	
	/**
	 * Returns true if boulder is within range of the sensor
	 * @return boolean true if boulder is present
	 */
	public boolean isBoulderPresent() {
		return Robot.intakeRoller.getAveragedRawBoulderDistance() > MIN_SENSOR_VOLTAGE;
	}
	
	/**
	 * Returns the raw voltage from the shooter distance sensor
	 * @return double voltage
	 */
	public double getRawBoulderDistance() {
		return Util.max(MIN_SENSOR_VOLTAGE, intakeDistanceSensor.getVoltage());
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

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeWithJoystick());
    }
}
