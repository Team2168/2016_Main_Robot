package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.shooter.DriveShooterWithJoysticks;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shooter Subsystem 
 * @author Krystina
 */
public class Shooter extends Subsystem {
	private Talon shooterFWD;
	private Talon shooterAFT;
	private AverageEncoder shooterEncoder;
	
	static Shooter instance = null;
		
	/**
	 * Private singleton constructor for the Shooter subsystem
	 */
	private Shooter () {
		shooterFWD = new Talon (RobotMap.SHOOTER_WHEEL_FWD);
		shooterFWD.setExpiration(0.1);
		shooterFWD.setSafetyEnabled(true);
		
		shooterAFT = new Talon (RobotMap.SHOOTER_WHEEL_AFT);
		shooterAFT.setExpiration(0.1);
		shooterAFT.setSafetyEnabled(true);
		

		shooterEncoder = new AverageEncoder(RobotMap.SHOOTER_ENCODER_A, 
				   							   RobotMap.SHOOTER_ENCODER_B, 
				   							   RobotMap.SHOOTER_ENCODER_PULSE_PER_ROT,
				   							   RobotMap.SHOOTER_ENCODER_DIST_PER_TICK,
				   							   RobotMap.AFT_SHOOTER_ENCODER_REVERSE,
				   							   RobotMap.SHOOTER_ENCODING_TYPE,
				   							   RobotMap.SHOOTER_SPEED_RETURN_TYPE,
				   							   RobotMap.SHOOTER_POS_RETURN_TYPE,
				   							   RobotMap.SHOOTER_AVG_ENCODER_VAL);
	}
	
	/**
	 * singleton object for Shooter_Superman
	 * @return rerturns the shooter singleton object
	 * @author Krystina
	 */
	public static Shooter getInstance() {
		if (instance == null)
			instance = new Shooter();
		
		return instance;
	}
	
	/**
	 * Takes in a speed and drives the Shooter_Superman wheels in the same directions
	 * @param speed -1 to 1 if given a positive value the ball will move inward. If negative the ball will move outward.
	 * @author Krystina
	 */
	public void driveShooter(double speed) {
		driveFWDShooterWheel(speed);
		driveAFTShooterWheel(speed);
		
	}
	
	/**
	 * Takes in a speed and drives the first shooter wheel in a positive or inward direction
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveFWDShooterWheel(double speed) {
		if(RobotMap.REVERSE_SHOOTER_WHEEL_FWD)
			speed = -speed;
		
		shooterFWD.set(speed);
	}
	
	/**
	 * Takes in a speed and drives the second shooter wheel in a positive or inward direction
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveAFTShooterWheel(double speed) {
		if(RobotMap.REVERSE_SHOOTER_WHEEL_AFT)
			speed = -speed;
			
		shooterAFT.set(speed);
	}
	
	/**
	 * Gets the speed of the shooter wheel
	 * @return speed in RPM
	 */
	public double getSpeed() {
		return shooterEncoder.getRate();
	}
	
	
	/**
	 * zeros the position traveled by motors
	 */
	public void resetPosition() {
		shooterEncoder.reset();
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveShooterWithJoysticks());
    }
}

