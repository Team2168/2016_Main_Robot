package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.DriveWithJoysticks;
import org.team2168.utils.BNO055;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsystem
 * @author Peter Dentch
 */
public class Drivetrain extends Subsystem {
	
	private static Drivetrain instance = null;
	
	// Drivetrain member variables, the motor controllers
	private static Talon leftMotor1;
	private static Talon leftMotor2;
	private static Talon leftMotor3;
	private static Talon rightMotor1;
	private static Talon rightMotor2;
	private static Talon rightMotor3;
	
	private static BNO055 imu;
	
	/**
	 * Default constructors for the Drivetrain subsystem
	 */
	private Drivetrain(){
		leftMotor1 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_1);
		leftMotor2 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_2);
		leftMotor3 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_3);
		rightMotor1 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_1);
		rightMotor2 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_2);
		rightMotor3 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_3);
		
		imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
				BNO055.vector_type_t.VECTOR_EULER);
	}
	
	/**
	 * Returns Drivetrain singleton object
	 * @return is the current drivetrain object
	 */
	public static Drivetrain getInstance(){
		
		if(instance == null)
			instance = new Drivetrain();
		
		return instance;
	}
	
	/**
	 * Takes in a double speed and sets it to the left motors
	 * @param speed is a double from 1 to -1
	 */
	public void driveLeft(double speed){
		
		if(RobotMap.reverseLeft)
			speed = -speed;
		
		leftMotor1.set(speed);
		leftMotor2.set(speed);
		leftMotor3.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the right motors
	 * @param speed is a double from 1 to -1
	 */
	public void driveRight(double speed){
		
		if(RobotMap.reverseRight)
			speed = -speed;
		
		rightMotor1.set(speed);
		rightMotor2.set(speed);
		rightMotor3.set(speed);
	}
	
	/**
	 * Takes in the speed for the left and speed for the right and sets them to their
	 * respective side motors 
	 * @param leftSpeed is a double from 1 to -1
	 * @param rightSpeed is a double from 1 to -1
	 */
	public void tankDrive(double leftSpeed, double rightSpeed){
		
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}
	
	/**
	 * Takes in a double speed and sets it to the first left motor
	 * @param speed is a double from 1 to -1
	 */
	public void left1Drive(double speed){
		if(RobotMap.reverseLeft)
			speed = -speed;
		leftMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the second left motor
	 * @param speed is a double from 1 to -1
	 */
	public void left2Drive(double speed){
		if(RobotMap.reverseLeft)
			speed = -speed;
		leftMotor2.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the third left motor
	 * @param speed is a double from 1 to -1
	 */
	public void left3Drive(double speed){
		if(RobotMap.reverseLeft)
			speed = -speed;
		leftMotor3.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the first right motor
	 * @param speed is a double from 1 to -1
	 */
	public void right1Drive(double speed){
		if(RobotMap.reverseRight)
			speed = -speed;
		rightMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the second right motor
	 * @param speed is a double from 1 to -1
	 */
	public void right2Drive(double speed){
		if(RobotMap.reverseRight)
			speed = -speed;
		rightMotor2.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the third right motor
	 * @param speed is a double from 1 to -1
	 */
	public void right3Drive(double speed){
		if(RobotMap.reverseRight)
			speed = -speed;
		rightMotor3.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }

    /**
     * Get the heading of the robot
     * @return heading in degrees (doesn't roll over at 360)
     */
    public static double getHeading() {
    	return imu.getHeading();
    }
    
    /**
     * Reset the heading of the IMU
     */
    public static void reset() {
    	imu.resetHeading();
    }
}

