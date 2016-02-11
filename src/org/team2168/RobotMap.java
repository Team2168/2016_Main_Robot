package org.team2168;

import org.team2168.PID.sensors.AverageEncoder;
import edu.wpi.first.wpilibj.CounterBase;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/*************************************************************************
	 *                              ROBORIO WIRING MAP
	 *************************************************************************/
	// Joysticks/////////////////////////////////////////////////////////////
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;

	
	// PWM (0 to 9) on RoboRio//////////////////////////////////////////////////
	public static final int RIGHT_DRIVE_TRAIN_1 = 0;
	public static final int RIGHT_DRIVE_TRAIN_2 = 1;
	public static final int RIGHT_DRIVE_TRAIN_3 = 2;
	public static final int LEFT_DRIVE_TRAIN_1 = 3;
	public static final int LEFT_DRIVE_TRAIN_2 = 4;
	public static final int LEFT_DRIVE_TRAIN_3 = 5;
	public static final int SHOOTER_WHEEL_FWD= 6;
	public static final int SHOOTER_WHEEL_AFT= 7;
	public static final int INDEX_WHEEL = 8;
	public static final int SHOOTER_HOOD_SERVO = 9;

	//Channels 10-25 on MXP
	public static final int INTAKE_WHEEL_LEFT = 10;
	public static final int INTAKE_WHEEL_RIGHT = 11;


	//PDP Channels/////////////////////////////////////////////////////////////


	//Solenoid Channels////////////////////////////////////////////////////////
	public static final int INTAKE_EXTEND = 0;
	public static final int INTAKE_RETRACT = 1;


	//Digital IO Channels//////////////////////////////////////////////////////
	//Channels 0-9 on RoboRio

	public static final int SHOOTER_AFT_ENCODER_A = 0;
	public static final int SHOOTER_AFT_ENCODER_B = 1;
	public static final int SHOOTER_FWD_ENCODER_A = 2;
	public static final int SHOOTER_FWD_ENCODER_B = 3;
	public static final int DRIVE_TRAIN_RIGHT_ENCODER_A = 6;
	public static final int DRIVE_TRAIN_RIGHT_ENCODER_B = 7;
	public static final int DRIVE_TRAIN_LEFT_ENCODER_A = 8;
	public static final int DRIVE_TRAIN_LEFT_ENCODER_B = 9;
	
	
	//Analog Input Channels////////////////////////////////////////////////////
	//Channels 0-1 on Roborio
	public static final int SHOOTER_DISTANCE_SENSOR = 0;
	
	//Channels 4-7 on MXP
	
	
	//CAN Device IDs///////////////////////////////////////////////////////////
	
	
	//Relay Channels///////////////////////////////////////////////////////////
	
	
	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/
	public static final boolean DT_REVERSE_RIGHT = true;
	public static final boolean DT_REVERSE_LEFT = false;

	private static final int DRIVE_PULSE_PER_ROTATION = 256; //encoder ticks per rotation
	private static final double DRIVE_GEAR_RATIO = 24.0/15.0; //ratio between wheel
	private static final double DRIVE_WHEEL_DIAMETER = 6;
	public static final int DRIVE_ENCODER_PULSE_PER_ROT = (int) (DRIVE_PULSE_PER_ROTATION * DRIVE_GEAR_RATIO); //pulse per rotation * gear ratio
	public static final double DRIVE_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_DIAMETER / DRIVE_ENCODER_PULSE_PER_ROT);
	public static final CounterBase.EncodingType DRIVE_ENCODING_TYPE = CounterBase.EncodingType.k4X; //count rising and falling edges on
	public static final AverageEncoder.PositionReturnType DRIVE_POS_RETURN_TYPE = AverageEncoder.PositionReturnType.FEET;
	public static final AverageEncoder.SpeedReturnType DRIVE_SPEED_RETURN_TYPE = AverageEncoder.SpeedReturnType.FPS;
	public static final int DRIVE_ENCODER_MIN_RATE = 0;
	public static final int DRIVE_ENCODER_MIN_PERIOD = 1;
	public static final boolean LEFT_DRIVE_TRAIN_ENCODER_REVERSE = false;
	public static final boolean RIGHT_DRIVE_TRAIN_ENCODER_REVERSE = true;
	public static final int DRIVE_AVG_ENCODER_VAL = 5;
	public static final double MIN_DRIVE_SPEED = 0.2;
	public static final double AUTO_NORMAL_SPEED = 0.5;
	public static final double WHEEL_BASE = 2; //units must match PositionReturnType (feet)


	/*************************************************************************
	 *                         Shooter PARAMETERS
	 *************************************************************************/
	public static final boolean REVERSE_SHOOTER_WHEEL_FWD= false;
	public static final boolean REVERSE_SHOOTER_WHEEL_AFT= false;private static final int SHOOTER_PULSE_PER_ROTATION = 256; //encoder ticks per rotation
	private static final double SHOOTER_GEAR_RATIO = 24.0/15.0; //ratio between wheel
	private static final double SHOOTER_WHEEL_DIAMETER = 6;
	public static final int SHOOTER_ENCODER_PULSE_PER_ROT = (int) (SHOOTER_PULSE_PER_ROTATION * SHOOTER_GEAR_RATIO); //pulse per rotation * gear ratio
	public static final double SHOOTER_ENCODER_DIST_PER_TICK = (Math.PI * SHOOTER_WHEEL_DIAMETER / SHOOTER_ENCODER_PULSE_PER_ROT);
	public static final CounterBase.EncodingType SHOOTER_ENCODING_TYPE = CounterBase.EncodingType.k4X; //count rising and falling edges on
	public static final AverageEncoder.PositionReturnType SHOOTER_POS_RETURN_TYPE = AverageEncoder.PositionReturnType.FEET;
	public static final AverageEncoder.SpeedReturnType SHOOTER_SPEED_RETURN_TYPE = AverageEncoder.SpeedReturnType.FPS;
	public static final int SHOOTER_ENCODER_MIN_RATE = 0;
	public static final int SHOOTER_ENCODER_MIN_PERIOD = 1;
	public static final boolean AFT_SHOOTER_ENCODER_REVERSE = false;
	public static final boolean FWD_SHOOTER_ENCODER_REVERSE = true;
	public static final int SHOOTER_AVG_ENCODER_VAL = 5;
	public static final double MIN_SHOOTER_SPEED = 0.2;
	public static final double SHOOTER_AUTO_NORMAL_SPEED = 0.5;
	public static final double SHOOTER_WHEEL_BASE = 2; //units must match PositionReturnType (feet)
	//TODO get correct values
	public static final double SHOOTER_BOULDER_STOP_VOLTAGE = 1.0;
	public static final double SHOOTER_CONSTANT_SPEED = 1.0;


	/*************************************************************************
	 *                         Intake PARAMETERS
	 *************************************************************************/
	public static final double INTAKE_SPEED_CONSTANT = 0.5;
	public static final boolean REVERSE_INTAKE_WHEEL_RIGHT = true;
	public static final boolean REVERSE_INTAKE_WHEEL_LEFT = false;


	/*************************************************************************
	 *                         Indexer PARAMETERS
	 *************************************************************************/


	/*************************************************************************
	 *                         Hood PARAMETERS
	 *************************************************************************/
	
	
	
	/*************************************************************************
	 *                         Vision PARAMETERS
	 *************************************************************************/


	/*************************************************************************
	 *                         PID PARAMETERS
	 *************************************************************************/


	/****************************************************************
	 *                TCP Servers  (ONLY FOR DEBUGGING)             *
	 ****************************************************************/
	public static final int TCPServerDrivetrainPos = 1180;
	public static final int TCPServerRotateController = 1181;
	public static final int TCPServerRightDrivetrainSpeed = 1182;
	public static final int TCPServerLeftDrivetrainSpeed = 1183;
}