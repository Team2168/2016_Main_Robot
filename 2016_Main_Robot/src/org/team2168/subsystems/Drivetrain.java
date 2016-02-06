package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.drivetrain.DriveWithJoysticks;
import org.team2168.utils.BNO055;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsystem
 * @author Peter Dentch
 */
public class Drivetrain extends Subsystem {
	
	
	
	// Drivetrain member variables, the motor controllers
	private static Talon leftMotor1;
	private static Talon leftMotor2;
	private static Talon leftMotor3;
	private static Talon rightMotor1;
	private static Talon rightMotor2;
	private static Talon rightMotor3;
	

	private static BNO055 imu;

	public AverageEncoder drivetrainLeftEncoder;
	public AverageEncoder drivetrainRightEncoder;
	
	private static Drivetrain instance = null;

	
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

		drivetrainLeftEncoder = new AverageEncoder(
				RobotMap.DRIVE_TRAIN_LEFT_ENCODER_A,
				RobotMap.DRIVE_TRAIN_LEFT_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.LEFT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);
		
		drivetrainRightEncoder = new AverageEncoder(
				RobotMap.DRIVE_TRAIN_LEFT_ENCODER_A,
				RobotMap.DRIVE_TRAIN_LEFT_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.RIGHT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);

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
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void driveLeft(double speed){
		
		setLeftMotor1(speed);
		setLeftMotor2(speed);
		setLeftMotor3(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the right motors
	 * @param speed is a double from 1 to -1, 
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void driveRight(double speed){
		
		setRightMotor1(speed);
		setRightMotor2(speed);
		setRightMotor3(speed);
	}
	
	/**
	 * Takes in the speed for the left and speed for the right and sets them to their
	 * respective side motors 
	 * @param leftSpeed is a double from 1 to -1,
	 * @param rightSpeed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void tankDrive(double leftSpeed, double rightSpeed){
		
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}
	
	/**
	 * Takes in a double speed and sets it to the first left motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setLeftMotor1(double speed){
		if(RobotMap.REVERSE_LEFT)
			speed = -speed;
		leftMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the second left motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setLeftMotor2(double speed){
		if(RobotMap.REVERSE_LEFT)
			speed = -speed;
		leftMotor2.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the third left motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setLeftMotor3(double speed){
		if(RobotMap.REVERSE_LEFT)
			speed = -speed;
		leftMotor3.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the first right motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setRightMotor1(double speed){
		if(RobotMap.REVERSE_RIGHT)
			speed = -speed;
		rightMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the second right motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setRightMotor2(double speed){
		if(RobotMap.REVERSE_RIGHT)
			speed = -speed;
		rightMotor2.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the third right motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setRightMotor3(double speed){
		if(RobotMap.REVERSE_RIGHT)
			speed = -speed;
		rightMotor3.set(speed);
	}
	
	/**
	 * Gets the distance traveled by the left wheels
	 * @return distance traveled in feet,
	 * with positive values being forward and negative values being backward
	 */
	public double getLeftPosition(){
		return drivetrainLeftEncoder.getPos();
	}
	
	/**
	 * Gets the distance traveled by the right wheels
	 * @return distance traveled in feet,
	 * with positive values being forward and negative values being backward
	 */
	public double getRightPosition(){
		return drivetrainRightEncoder.getPos();
	}
	
	/**
	 * Gets the distance traveled by the chassis
	 * @return the average distance in inches traveled by the left and right wheels,
	 * with positive values being forward and negative values being backward
	 */
	public double getAverageDistance(){
		return (getLeftPosition() + getRightPosition()) / 2.0;
	}
	
	/**
	 * Zeros the position traveled by the left wheels of the chassis
	 */
	public void resetLeftPosition(){
		drivetrainLeftEncoder.reset();
	}
	
	/**
	 * Zeros the position traveled by the right wheels of the chassis
	 */
	public void resetRightPosition(){
		drivetrainRightEncoder.reset();
	}
	
	/**
	 * Zeros the distance traveled by the chassis
	 */
	public void resetPosition(){
		resetLeftPosition();
		resetRightPosition();
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

