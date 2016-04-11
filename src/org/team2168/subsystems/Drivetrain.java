package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.controllers.PIDPosition;
import org.team2168.PID.controllers.PIDSpeed;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.PID.sensors.BNOHeading;
import org.team2168.PID.sensors.IMU;
import org.team2168.PID.sensors.TCPCamSensor;
import org.team2168.commands.drivetrain.DriveWithJoysticks;
import org.team2168.utils.BNO055;
import org.team2168.utils.TCPSocketSender;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsystem
 * @author Peter Dentch
 */
public class Drivetrain extends Subsystem {
	private SpeedController leftMotor1;
	private SpeedController leftMotor2;
	private SpeedController leftMotor3;
	private SpeedController rightMotor1;
	private SpeedController rightMotor2;
	private SpeedController rightMotor3;
	
	
	DoubleSolenoid gearShifter;
	
	public ADXRS453Gyro gyroSPI;
	private BNO055 gyro;
	private BNOHeading stupidPIDSensorGyro;
	public IMU imu;
	public TCPCamSensor tcpCamSensor;

	public AverageEncoder drivetrainLeftEncoder;
	public AverageEncoder drivetrainRightEncoder;
	
	//declare position/speed controllers
	public PIDPosition driveTrainPosController;
	public PIDPosition rotateController;
	public PIDPosition rotateDriveStraightController;
	public PIDPosition rotateCameraController;	
	
	//declare speed controllers
	public PIDSpeed rightSpeedController;
	public PIDSpeed leftSpeedController;

	//output voltage...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	private volatile double leftMotor1Voltage;
	private volatile double leftMotor2Voltage;
	private volatile double leftMotor3Voltage;
	private volatile double rightMotor1Voltage;
	private volatile double rightMotor2Voltage;
	private volatile double rightMotor3Voltage;

	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPSocketSender TCPdrivePosController;
	TCPSocketSender TCPrightSpeedController;
	TCPSocketSender TCPleftSpeedController;
	TCPSocketSender TCProtateController;
	TCPSocketSender TCProtateCameraController;
	
	
	private static Drivetrain instance = null;
	

	
	/**
	 * Default constructors for the Drivetrain subsystem
	 */
	private Drivetrain(){
		
		if(Robot.isPracticeRobot())
		{
		leftMotor1 = new Victor(RobotMap.LEFT_DRIVE_TRAIN_1_PBOT);
		((Victor) leftMotor1).setExpiration(0.1);
		((Victor) leftMotor1).setSafetyEnabled(true);
		
		leftMotor2 = new Victor(RobotMap.LEFT_DRIVE_TRAIN_2_PBOT);
		((Victor) leftMotor2).setExpiration(0.1);
		((Victor) leftMotor2).setSafetyEnabled(true);
		
		leftMotor3 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_3_PBOT);
		((Talon) leftMotor3).setExpiration(0.1);
		((Talon) leftMotor3).setSafetyEnabled(true);
		
		rightMotor1 = new Victor(RobotMap.RIGHT_DRIVE_TRAIN_1_PBOT);
		((Victor) rightMotor1).setExpiration(0.1);
		((Victor) rightMotor1).setSafetyEnabled(true);
		
		rightMotor2 = new Victor(RobotMap.RIGHT_DRIVE_TRAIN_2_PBOT);
		((Victor) rightMotor2).setExpiration(0.1);
		((Victor) rightMotor2).setSafetyEnabled(true);
		
		rightMotor3 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_3_PBOT);
		((Talon) rightMotor3).setExpiration(0.1);
		((Talon) rightMotor3).setSafetyEnabled(true);
		}
		else
		{
			leftMotor1 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_1);
			((Talon) leftMotor1).setExpiration(0.1);
			((Talon) leftMotor1).setSafetyEnabled(true);
			
			leftMotor2 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_2);
			((Talon) leftMotor2).setExpiration(0.1);
			((Talon) leftMotor2).setSafetyEnabled(true);
			
			leftMotor3 = new Talon(RobotMap.LEFT_DRIVE_TRAIN_3);
			((Talon) leftMotor3).setExpiration(0.1);
			((Talon) leftMotor3).setSafetyEnabled(true);
			
			rightMotor1 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_1);
			((Talon) rightMotor1).setExpiration(0.1);
			((Talon) rightMotor1).setSafetyEnabled(true);
			
			rightMotor2 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_2);
			((Talon) rightMotor2).setExpiration(0.1);
			((Talon) rightMotor2).setSafetyEnabled(true);
			
			rightMotor3 = new Talon(RobotMap.RIGHT_DRIVE_TRAIN_3);
			((Talon) rightMotor3).setExpiration(0.1);
			((Talon) rightMotor3).setSafetyEnabled(true);
		}
		
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
				RobotMap.DRIVE_TRAIN_RIGHT_ENCODER_A,
				RobotMap.DRIVE_TRAIN_RIGHT_ENCODER_B,
				RobotMap.DRIVE_ENCODER_PULSE_PER_ROT,
				RobotMap.DRIVE_ENCODER_DIST_PER_TICK,
				RobotMap.RIGHT_DRIVE_TRAIN_ENCODER_REVERSE,
				RobotMap.DRIVE_ENCODING_TYPE,
				RobotMap.DRIVE_SPEED_RETURN_TYPE,
				RobotMap.DRIVE_POS_RETURN_TYPE,
				RobotMap.DRIVE_AVG_ENCODER_VAL);
		
		gyroSPI = new ADXRS453Gyro();
		gyroSPI.startThread();
		
		gyro = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
				BNO055.vector_type_t.VECTOR_EULER);
		stupidPIDSensorGyro = new BNOHeading(gyro);
		
		imu = new IMU(drivetrainLeftEncoder,drivetrainRightEncoder,RobotMap.WHEEL_BASE);

		tcpCamSensor = new TCPCamSensor(41234, 100);

		//enable shifting solenoids
		gearShifter = new DoubleSolenoid(RobotMap.DRIVETRAIN_LOW_GEAR, RobotMap.DRIVETRAIN_HIGH_GEAR);
		
		//DriveStraight Controller
				rotateController = new PIDPosition(
						"RotationController",
						RobotMap.ROTATE_POSITION_P,
						RobotMap.ROTATE_POSITION_I,
						RobotMap.ROTATE_POSITION_D,
						gyroSPI,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);
				
				rotateCameraController = new PIDPosition(
						"RotationCameraController",
						RobotMap.ROTATE_POSITION_P,
						RobotMap.ROTATE_POSITION_I,
						RobotMap.ROTATE_POSITION_D,
						tcpCamSensor,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);
				
				rotateDriveStraightController = new PIDPosition(
						"RotationStraightController",
						RobotMap.ROTATE_POSITION_P_Drive_Straight,
						RobotMap.ROTATE_POSITION_I_Drive_Straight,
						RobotMap.ROTATE_POSITION_D_Drive_Straight,
						gyroSPI,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);

				driveTrainPosController = new PIDPosition(
						"driveTrainPosController",
						RobotMap.DRIVE_TRAIN_RIGHT_POSITION_P,
						RobotMap.DRIVE_TRAIN_RIGHT_POSITION_I,
						RobotMap.DRIVE_TRAIN_RIGHT_POSITION_D,
						imu,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);

				//Spawn new PID Controller
				rightSpeedController = new PIDSpeed(
						"RightSpeedController",
						RobotMap.DRIVE_TRAIN_RIGHT_SPEED_P,
						RobotMap.DRIVE_TRAIN_RIGHT_SPEED_I,
						RobotMap.DRIVE_TRAIN_RIGHT_SPEED_D, 1,
						drivetrainRightEncoder,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);

				leftSpeedController = new PIDSpeed(
						"LeftSpeedController",
						RobotMap.DRIVE_TRAIN_LEFT_SPEED_P,
						RobotMap.DRIVE_TRAIN_LEFT_SPEED_I,
						RobotMap.DRIVE_TRAIN_LEFT_SPEED_D, 1,
						drivetrainLeftEncoder,
						RobotMap.DRIVE_TRAIN_PID_PERIOD);


				//add min and max output defaults and set array size
				rightSpeedController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				leftSpeedController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				driveTrainPosController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				rotateController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				rotateDriveStraightController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);
				rotateCameraController.setSIZE(RobotMap.DRIVE_TRAIN_PID_ARRAY_SIZE);

				//start controller threads
				rightSpeedController.startThread();
				leftSpeedController.startThread();
				driveTrainPosController.startThread();
				rotateController.startThread();
				rotateDriveStraightController.startThread();
				rotateCameraController.startThread();

				
				
				
				
				//start TCP Servers for DEBUGING ONLY
				TCPdrivePosController = new TCPSocketSender(RobotMap.TCP_SERVER_DRIVE_TRAIN_POS, driveTrainPosController);
				TCPdrivePosController.start();

				TCPrightSpeedController = new TCPSocketSender(RobotMap.TCO_SERVER_RIGHT_DRIVE_TRAIN_SPEED, rightSpeedController);
				TCPrightSpeedController.start();

				TCPleftSpeedController = new TCPSocketSender(RobotMap.TCP_SERVER_LEFT_DRIVE_TRAIN_SPEED, leftSpeedController);
				TCPleftSpeedController.start();

				TCProtateController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_CONTROLLER, rotateController);
				TCProtateController.start();
				
				TCProtateController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_CONTROLLER_STRAIGHT, rotateDriveStraightController);
				TCProtateController.start();
				
				TCProtateCameraController = new TCPSocketSender(RobotMap.TCP_SERVER_ROTATE_CAMERA_CONTROLLER, rotateCameraController);
				TCProtateCameraController.start();
				
				leftMotor1Voltage = 0;
				leftMotor2Voltage = 0;
				leftMotor3Voltage = 0;
				rightMotor1Voltage = 0;
				rightMotor2Voltage = 0;
				rightMotor3Voltage = 0;
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
		if(RobotMap.DT_REVERSE_LEFT)
			speed = -speed;
		leftMotor1.set(speed);
		leftMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	/**
	 * Takes in a double speed and sets it to the second left motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setLeftMotor2(double speed){
		if(RobotMap.DT_REVERSE_LEFT)
			speed = -speed;
		leftMotor2.set(speed);
		leftMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	/**
	 * Takes in a double speed and sets it to the third left motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setLeftMotor3(double speed){
		if(RobotMap.DT_REVERSE_LEFT)
			speed = -speed;
		leftMotor3.set(speed);
		leftMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	/**
	 * Takes in a double speed and sets it to the first right motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setRightMotor1(double speed){
		if(RobotMap.DT_REVERSE_RIGHT)
			speed = -speed;
		rightMotor1.set(speed);
		rightMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	/**
	 * Takes in a double speed and sets it to the second right motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setRightMotor2(double speed){
		if(RobotMap.DT_REVERSE_RIGHT)
			speed = -speed;
		rightMotor2.set(speed);
		rightMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}
	
	/**
	 * Takes in a double speed and sets it to the third right motor
	 * @param speed is a double from 1 to -1,
	 * with positive values being forward, negative values being backward, and zero being stationary
	 */
	public void setRightMotor3(double speed){
		if(RobotMap.DT_REVERSE_RIGHT)
			speed = -speed;
		rightMotor3.set(speed);
		rightMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
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
    public double getHeading() {
    	return gyroSPI.getPos();
    }
    
    /**
     * Reset the heading of the IMU to zero degrees
     */
    public  void reset() {
    	gyro.resetHeading();
    }
    
    /**
     * 
     * @return the robots pitch +/- 180 degrees
     */
    public  double getPitchAngle() {
    	return gyro.getVector()[2];
    }
    
    /**
     * 
     * @return the robots roll +/- 90 degrees
     */
    public double getRollAngle() {
    	return gyro.getVector()[1];
    }
    
	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getLeft1MotorVoltage() {
		return leftMotor1Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getLeft2MotorVoltage() {
		return leftMotor2Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getLeft3MotorVoltage() {
		return leftMotor3Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getRight1MotorVoltage() {
		return rightMotor1Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getRight2MotorVoltage() {
		return rightMotor2Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getRight3MotorVoltage() {
		return rightMotor3Voltage;
	}

	/**
	 * Shifts the Drivetrain from High to Low Gear
	 */
    public void shiftGearsHighToLow(){
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }
    
	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean gearIsHigh()
    {
    	return gearShifter.get()==DoubleSolenoid.Value.kReverse;
    }
    
	/**
	 * Shifts the Drivetrain from Low to High Gear
	 */
    public void shiftGearsLowToHigh(){
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
    
	/**
	 * Returns true if last commanded shift was Low Gear
	 */
    public boolean gearIsLow()
    {
    	return gearShifter.get()==DoubleSolenoid.Value.kForward;
    }
    
    /**
	 * Reset robot heading to zero.
	 */
	public void resetGyro() {
		gyroSPI.reset();
	}

	/**
	 * Calibrate gyro.
	 * This should only be called if the robot will be stationary for the calibration period.
	 */
	public void calibrateGyro() {
		gyroSPI.calibrate();
	}

	/**
	 * @return true if the gyro completed its previous calibration sequence.
	 */
	public boolean isGyroCalibrated() {
		return gyroSPI.hasCompletedCalibration();
	}

	/**
	 * @return true if the gyro is being calibrated.
	 */
	public boolean isGyroCalibrating() {
		return gyroSPI.isCalibrating();
	}

	/**
	 * Call to stop an active gyro calibration sequence.
	 */
	public void stopGyroCalibrating() {
		gyroSPI.stopCalibrating();
	}

}

