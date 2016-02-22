package org.team2168;

import org.team2168.commands.drivetrain.*;
import org.team2168.commands.drivetrain.PIDCommands.*;
import org.team2168.commands.indexer.*;
import org.team2168.commands.intakeposition.*;
import org.team2168.commands.intakeroller.*;
import org.team2168.commands.pneumatics.*;
import org.team2168.commands.shooter.*;
import org.team2168.utils.F310;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public F310 driverJoystick;
	public F310 operatorJoystick;
	//public F310 commandsTestJoystick;

	private static OI instance = null;
	
	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI(){
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
		//commandsTestJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);

		/********************************************
		 *         Driver Joystick Buttons          *
		 ********************************************/
		//TODO create commands for commented out buttons
		//operatorJoystick.ButtonLeftBumper().whileActive(new LowGear());
		//operatorJoystick.ButtonRightBumper().whileActive(new HighGear());


		/********************************************
		 *        Operator Joystick Buttons         *
		 ********************************************/
		//TODO calibrate values and create commands for commented out buttons
		operatorJoystick.ButtonA().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
		operatorJoystick.ButtonA().whileHeld(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
		//operatorJoystick.ButtonB().whenPressed(new HoodTowerPreset());
		//operatorJoystick.ButtonX().whenPressed(new HoodDefensePreset());
		//operatorJoystick.ButtonY().whenPressed(new Hood???Preset());
		operatorJoystick.ButtonLeftDPad().whenPressed(new IntakeRetract());
		operatorJoystick.ButtonRightDPad().whenPressed(new IntakeExtend());
		operatorJoystick.ButtonBack().whenPressed(new StowForLowBar());
		
		//operatorJoystick.ButtonStart().whenPressed(new VisionPosition());
		operatorJoystick.ButtonLeftTrigger().whileHeld(new IntakeWithConstant(-RobotMap.INTAKE_SPEED_CONSTANT));
		operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
//		operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeSingleBall());
		operatorJoystick.ButtonLeftBumper().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPIT_SPEED_CONSTANT));
		operatorJoystick.ButtonRightBumper().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));

		
		/********************************************
		 *        Command Test  Joystick Buttons         *
		 ********************************************/
//		commandsTestJoystick.ButtonY().whenPressed(new RotateXDistancePIDZZZ(0, 0.325, 0.1, 4));
//		commandsTestJoystick.ButtonB().whenPressed(new DrivePIDPause());
		
	}
	
	/**
	 * Returns Operator Interface singleton object
	 * @return is the current OI object
	 */
	public static OI getInstance(){
		
		if(instance == null)
			instance = new OI();
		
		return instance;
	}
	
	// The minimum speed of the drivetrain, should be tweaked 
    // for using different individual drivetrains 
    public static final double MIN_DRIVE_SPEED = 0.222;
    
    // An array of speeds of the drivetrain for a given joystick input,
    // used to manipulate the line equations of the interpolate method
    public static double joystickScale[][] = {
    		{1.00, 1.00},
    		{0.90, 0.68},
    		{0.50, 0.32},
    		{0.06, MIN_DRIVE_SPEED},
    		{0.06, 0.00},
    		{0.00, 0.00},
    		{-0.06, 0.00},
    		{-0.06, -MIN_DRIVE_SPEED},
    		{-0.50, -0.32},
    		{-0.90, -0.68},
    		{-1.00, -1.00},
    };
    
    /**
     * A method to modify the joystick values using linear interpolation.
     * The objective is to augment the joystick value going to the motor controllers
     * to improve acceleration and control while still allowing full speed
     * @param input The value to augment
     * @return The augmented value
     */
    private double interpolate(double input){
    	double retVal = 0.0;
    	boolean done = false;
    	double m, b;
    	
    	// Making sure the input is between 1 and -1
    	if(input > 1.0)
    		input = 1.0;
    	else if(input < -1.0)
    		input = -1.0;
    	
    	// Find the two points in the array between which the input falls,
    	// Starts with i = 1 since we can't have a point fall outside the array
    	for(int i = 1; !done && i < joystickScale.length; i++){
    		if(input >= joystickScale[i][0]){
    			// if this returns true, points in the array between which the input falls were found. 
    			// Now calculate the equation for the line that connects those points, and plug the input in to get retVal
    			m = (joystickScale[i][1] - joystickScale[i - 1][1])/(joystickScale[i][0] - joystickScale[i - 1][0]);
    			b = joystickScale[i][1] - (m * joystickScale[i][0]);
    			retVal = m * input + b;
    			
    			// retVal was found, break out of the loop
    			done = true;
    		}
    	}
    	
    	return retVal;
    }
    
    /**
     * Electronic braking, aka "Falcon Claw"
     * The more the "brake" is pulled, the slower the output speed
     * @param inputSpeed The input value to scale back based on brake input (1 to -1)
     * @param brake The brake input value (0 to -1)
     * @return The adjusted value
     */
    private double falconClaw(double inputSpeed, double brake){
    	return ((1 - ((-MIN_DRIVE_SPEED + 1) * Math.abs(brake))) * inputSpeed);
    }
    
    /**
     * Gets the left joystick value with braking adjustment
     * @return The driver's left joystick value
     */
    public double getLeftSpeed(){
    	 
    	double leftSpeed = interpolate(Robot.oi.driverJoystick.getLeftStickRaw_Y());
    	
    	// If the trigger (brake) is pressed, use falcon claw method
    	if(Math.abs(Robot.oi.driverJoystick.getRightTriggerAxisRaw() - Robot.oi.driverJoystick.getLeftTriggerAxisRaw()) > 0.05)
    		leftSpeed = falconClaw(leftSpeed, Robot.oi.driverJoystick.getLeftTriggerAxisRaw());
    	
    	return leftSpeed;
    }
    
    /**
     * Gets the right joystick value with braking adjustment
     * @return The driver's right joystick value
     */
    public double getRightSpeed(){
    	
    	double rightSpeed = interpolate(Robot.oi.driverJoystick.getRightStickRaw_Y());
    	
    	// if the trigger (brake) is pressed, use falcon claw method
    	if(Math.abs(Robot.oi.driverJoystick.getLeftTriggerAxisRaw() - Robot.oi.driverJoystick.getRightTriggerAxisRaw()) > 0.05)
    		rightSpeed = falconClaw(rightSpeed, Robot.oi.driverJoystick.getRightTriggerAxisRaw());
    	
    	return rightSpeed;
    }
      
}
