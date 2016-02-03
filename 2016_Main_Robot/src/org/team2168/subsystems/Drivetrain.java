package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.drivetrain.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsystem
 * @author Peter Dentch
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Drivetrain member variables, the motor controllers
	private Talon leftMotor1;
	private Talon leftMotor2;
	private Talon leftMotor3;
	private Talon rightMotor1;
	private Talon rightMotor2;
	private Talon rightMotor3;
	
	public AverageEncoder drivetrainLeftEncoder;
	public AverageEncoder drivetrainRightEncoder;
	
	static Drivetrain instance = null;
	
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
	 * @param speed is a double from 1 to -1
	 */
	public void driveLeft(double speed){
		
		if(RobotMap.REVERSE_LEFT)
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
		
		if(RobotMap.REVERSE_RIGHT)
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
		if(RobotMap.REVERSE_LEFT)
			speed = -speed;
		leftMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the second left motor
	 * @param speed is a double from 1 to -1
	 */
	public void left2Drive(double speed){
		if(RobotMap.REVERSE_LEFT)
			speed = -speed;
		leftMotor2.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the third left motor
	 * @param speed is a double from 1 to -1
	 */
	public void left3Drive(double speed){
		if(RobotMap.REVERSE_LEFT)
			speed = -speed;
		leftMotor3.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the first right motor
	 * @param speed is a double from 1 to -1
	 */
	public void right1Drive(double speed){
		if(RobotMap.REVERSE_RIGHT)
			speed = -speed;
		rightMotor1.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the second right motor
	 * @param speed is a double from 1 to -1
	 */
	public void right2Drive(double speed){
		if(RobotMap.REVERSE_RIGHT)
			speed = -speed;
		rightMotor2.set(speed);
	}
	
	/**
	 * Takes in a double speed and sets it to the third right motor
	 * @param speed is a double from 1 to -1
	 */
	public void right3Drive(double speed){
		if(RobotMap.REVERSE_RIGHT)
			speed = -speed;
		rightMotor3.set(speed);
	}
	
	/**
	 * Gets the distance traveled by the left wheels
	 * @return distance traveled in feet
	 */
	public double getLeftPosition(){
		return drivetrainLeftEncoder.getPos();
	}
	
	/**
	 * Gets the distance traveled by the right wheels
	 * @return distance traveled in feet
	 */
	public double getRightPosition(){
		return drivetrainRightEncoder.getPos();
	}
	
	/**
	 * Gets the distance traveled by the chassis
	 * @return the average distance in inches traveled by the left and right wheels
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
}

