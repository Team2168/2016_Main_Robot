package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.shooter.DriveShooterWithJoysticks;
import org.team2168.commands.shooterhood.DriveShooterHoodWithJoysticks;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *ShooterHood subsystem
 *@author Harris Jilani
 */
public class ShooterHood extends Subsystem {
	private Servo hoodServo;

	private static ShooterHood instance = null;
	
	double startAngle;
	double endAngle;
	double currentAngle;
	double startTime;
	double currentTime;
	final static double DEGREES_PER_SECOND = 90;

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
		
		if(startAngle != degrees) {
			hoodServo.setAngle(degrees); 
			startAngle = hoodServo.getAngle();
			startTime = Timer.getFPGATimestamp();
		}
	}
	
	/**
	 * Finds the motor's current angle.
	 * Takes the angle and time from the setAngle command before movement begins
	 * and after that is called, gets the current system time and angle it is
	 * moving to and does the math to get an estimate of the current angle
	 * @return the angle (degrees) the motor was last commanded to.
	 */
	public double getAngle() {
		endAngle = hoodServo.getAngle();
		double angleDifference = endAngle - startAngle;
		double timeDifference = Timer.getFPGATimestamp() - startTime;
		if(angleDifference > 0)
			currentAngle = (startAngle + (timeDifference * DEGREES_PER_SECOND));
		else if(angleDifference < 0)
			currentAngle = (startAngle - (timeDifference * DEGREES_PER_SECOND));
		else
			currentAngle = endAngle;
		return currentAngle;
	}
  		
	/**
	 * Set the default command for a subsystem here.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveShooterHoodWithJoysticks());
	}
}
