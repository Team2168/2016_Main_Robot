package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.sensors.AverageEncoder;
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
	
	private static Talon shooterFWD;
	private static Talon shooterAFT;
	private static AverageEncoder shooterFWDEncoder;
	private static AverageEncoder shooterAFTEncoder;
	
	
	static Shooter instance = null;
		
	/**
	 * Private singleton constructor for Shooter_Superman
	 * 
	 */
	
	private Shooter ()
	{
		shooterFWD = new Talon (RobotMap.SHOOTER_WHEEL_FWD);
		shooterAFT = new Talon (RobotMap.SHOOTER_WHEEL_AFT);
		shooterFWDEncoder = new AverageEncoder(RobotMap.SHOOTER_FWD_ENCODER_A, 
											   RobotMap.SHOOTER_FWD_ENCODER_B, 
											   RobotMap.SHOOTER_ENCODER_PULSE_PER_ROT,
											   RobotMap.SHOOTER_ENCODER_DIST_PER_TICK,
											   RobotMap.FWD_SHOOTER_ENCODER_REVERSE,
											   RobotMap.SHOOTER_ENCODING_TYPE,
											   RobotMap.SHOOTER_SPEED_RETURN_TYPE,
											   RobotMap.SHOOTER_POS_RETURN_TYPE,
											   RobotMap.SHOOTER_AVG_ENCODER_VAL);
		
		shooterAFTEncoder = new AverageEncoder(RobotMap.SHOOTER_AFT_ENCODER_A, 
				   							   RobotMap.SHOOTER_AFT_ENCODER_B, 
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
	 * @return returns the shooter singleton object
	 * @author Krystina
	 */
	
	public static Shooter getInstance()
	{
		if (instance == null)
			instance = new Shooter();
		
		return instance;
	}
	/**
	 * Gets distance traveled by aft motor
	 * @return
	 */
	public double getAFTPosition()
	{
		return shooterAFTEncoder.getPos();
	}
	
	/**
	 * Gets distance traveled by forward motor
	 * @return double
	 */
	public double getFWDPosition()
	{
		return shooterFWDEncoder.getPos();
	}
	/**
	 * Gets average distance traveled by both motors
	 * @return
	 */
	public double getAverageDistance()
	{
		return(getAFTPosition() + getFWDPosition())/2;
	}
	/**
	 * zeros the position traveled by aft motors
	 */
	public void resetAFTPosition()
	{
		shooterAFTEncoder.reset();
	}
	/**
	 * zeros the position traveled by forward motors
	 */
	public void resetFWDPosition()
	{
		shooterFWDEncoder.reset();
	}
	/**
	 * resets position of both motors
	 */
	public void resetPosition()
	{
		resetAFTPosition();
		resetFWDPosition();
	}
	
	
	/**
	 * Takes in a speed and drives the Shooter_Superman wheels in the same directions
	 * @param speed -1 to 1 if given a positive value the ball will move inward. If negative the ball will move outward.
	 * @author Krystina
	 */
	public void driveShooter(double speed)
	{
		driveFWDShooterWheel(speed);
		driveAFTShooterWheel(speed);
		
	}
	
	/**
	 * Takes in a speed and drives the first shooter wheel in a positive or inward direction
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveFWDShooterWheel(double speed)
	{
		if(RobotMap.REVERSE_SHOOTER_WHEEL_FWD)
			speed = -speed;
		
		shooterFWD.set(speed);
	}
	
	/**
	 * Takes in a speed and drives the second shooter wheel in a positive or inward direction
	 * @param speed -1 to 1
	 * @author Krystina
	 */
	public void driveAFTShooterWheel(double speed)
	{
		if(RobotMap.REVERSE_SHOOTER_WHEEL_AFT)
			speed = -speed;
			
		shooterAFT.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveShooterWithJoysticks());
    }
}

