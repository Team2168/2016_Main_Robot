package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.shooter.DriveShooterWithJoysticks;
import org.team2168.commands.shooterhood.DriveShooterHoodWithJoysticks;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *ShooterHood subsystem
 *@author Harris Jilani
 */
public class ShooterHood extends Subsystem {
	private Servo hoodServo;

	private static ShooterHood instance = null;

	/**
	 * Default constructor for ShooterHood subsystem
	 */
	private ShooterHood() {
		hoodServo = new Servo(RobotMap.SHOOTER_HOOD_SERVO);
		hoodServo.setBounds(1950, 1504, 1500, 1496, 1050); 
	}

	/**
	 * Returns ShooterHood singleton object
	 * @return is the current shooterhood object
	 */
	public static ShooterHood getInstance() {
		if(instance == null)
			instance = new ShooterHood();

		return instance;	
	}
   
	/**
	 * Takes in a given angle and moves motor to that angle
	 * @param degrees angle from 0.0 to 180.0
	 */
	public void setAngle(double degrees) {   
		hoodServo.setAngle(degrees);
		
	}
	
	/**
	 * Finds the motor's current angle. Note this doesn't reflect the true
	 *   position of the hood, but the last position the hood was commanded to.
	 *   It will not account for the time it takes for the mechanism to get to
	 *   its destination position. Therefore this method SHOULD NOT be used to
	 *   qualify a command has completed moving the hood.
	 * @return the angle (degrees) the motor was last commanded to.
	 */
	public double getAngle() {
		return hoodServo.getAngle();
	}
  		
	/**
	 * Set the default command for a subsystem here.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveShooterHoodWithJoysticks());
	}
}
