package org.team2168;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	/*************************************************************************
	 *                              ROBORIO WIRING MAP
	 *************************************************************************/
	// Joysticks/////////////////////////////////////////////////////////////
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;
	
	// PWM (0 to 9)
	public static final int RIGHT_DRIVE_TRAIN_1 = 0;
	public static final int RIGHT_DRIVE_TRAIN_2 = 1;
	public static final int RIGHT_DRIVE_TRAIN_3 = 2;
	public static final int LEFT_DRIVE_TRAIN_1 = 3;
	public static final int LEFT_DRIVE_TRAIN_2 = 4;
	public static final int LEFT_DRIVE_TRAIN_3 = 5;
	public static final int SHOOTER_WHEEL_1= 6;
	public static final int SHOOTER_WHEEL_2= 7;
	
	//PDP Channels/////////////////////////////////////////////////////////////
	
	//Solenoid Channels////////////////////////////////////////////////////////
	
	//Relay Channels///////////////////////////////////////////////////////////
	
	//Digital IO Channels//////////////////////////////////////////////////////
	//Channels 0-9 on RoboRio
	
	
	//Channels 10-25 on MXP
	
	
	
	//Analog Input Channels////////////////////////////////////////////////////
	
	
	//Channels 4-7 on MXP
	
	
	//CAN Device IDs///////////////////////////////////////////////////////////
	
	
	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/
	public static boolean reverseRight = true;
	public static boolean reverseLeft = false;
	
	/*************************************************************************
	 *                         Shooter PARAMETERS
	 *************************************************************************/
	public static final boolean reverseShooterWheel1= false;
	public static final boolean reverseShooterWheel2= false;

	/*************************************************************************
	 *                         Intake PARAMETERS
	 *************************************************************************/
	
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
