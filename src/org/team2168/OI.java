package org.team2168;

import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeposition.IntakeRetract;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.intakeroller.IntakeWithJoystick;
import org.team2168.commands.shooter.DriveShooterWithConstant;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.buttons.Button;

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
	
	public  F310 driverJoystick;
	public  F310 operatorJoystick;
		
	private static OI instance = null;
	
	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI(){
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);

		/********************************************
		 *         Driver Joystick Buttons          *
		 ********************************************/
		//TODO create commands for commented out buttons
		//operatorJoystick.ButtonLeftTrigger().whileActive(new FalconClawLeft());
		//operatorJoystick.ButtonRightTrigger().whileActive(new FalconClawRight());
		//operatorJoystick.ButtonLeftBumper().whileActive(new LowGear());
		//operatorJoystick.ButtonRightBumper().whileActive(new HighGear());

		/********************************************
		 *        Operator Joystick Buttons         *
		 ********************************************/
		//TODO calibrate values and create commands for commented out buttons
		operatorJoystick.ButtonA().whileHeld(new DriveShooterWithConstant(RobotMap.SHOOTER_CONSTANT_SPEED));
		//operatorJoystick.ButtonB().whenPressed(new HoodTowerPreset());
		//operatorJoystick.ButtonX().whenPressed(new HoodDefensePreset());
		//operatorJoystick.ButtonY().whenPressed(new Hood???Preset());
		operatorJoystick.ButtonLeftDPad().whenPressed(new IntakeRetract());
		operatorJoystick.ButtonRightDPad().whenPressed(new IntakeExtend());
		//operatorJoystick.ButtonBack().whenPressed(new StowForLowBar());
		//operatorJoystick.ButtonStart().whenPressed(new VisionPosition());
		operatorJoystick.ButtonLeftTrigger().whileHeld(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
		operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeWithConstant(-RobotMap.INTAKE_SPEED_CONSTANT));
		//operatorJoystick.ButtonLeftBumper().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
		//operatorJoystick.ButtonRightBumper().whileHeld(new DriveIndexerWithConstant(-RobotMap.INDEXER_SPEED_CONSTANT));
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
	
	///////////////////////////////////////////////////////////////
	// Driver Joystick
	///////////////////////////////////////////////////////////////
	
	public static final double MIN_DRIVE_SPEED = 0.222;
	
	/**
	 * A function to modify the joystick values using exponential growth.
	 * Its purpose is to augment the driver joystick value going to the motor controllers
	 * for better control and smoother acceleration while approaching full speed
	 * @param input the value to augment
	 * @return the adjusted value
	 */
	public static double exponentialize(double input){
		
		double retVal = 0.0;
		
		// making sure the input is between 1 and -1 (the range of the joystick output)
		if(input > 1)
			input = 1;
		if(input < -1)
			input = -1;
		
		// Accounting for the joystick input values that wouldn't normally start the motor,
		// making it so that the minimum output of the joystick (after zero) will set the motors to their minimum speed
		if(input < 0.06 && input > -0.06)
			retVal = input;
		
		// Though in the function when x = 0.06 y = 0.222 (the minimum drive speed of the motors),
		// when x = 1 y = 0.995 (close enough to 1, the maximum drive speed of the motors),
		// so it is justifiable to simply set the output to the input when the input is 1 or -1
		else if(Math.abs(input) == 1)
			retVal = input;
		
		// Plugging in the input to the equation, using the absolute value of the input because
		// only the positive section of the equation applies to the intended input values
		else retVal = (1.3285 * Math.pow(Math.abs(input), 3))
				- (0.6665 * Math.pow(Math.abs(input), 2))
				+ (0.1337 * Math.abs(input))
				+ 0.2161;
		
		// Sets the output to negative if the input is negative and the output is not already set to -1 or 0,
		// effectively reflecting the line of the equation over both the x and y axes to use for the negative inputs
		if(input < 0.00 && (retVal != -1.00 && input != 0.00))
			retVal = -retVal;
		
		return retVal;
	}
	
	/**
	 * Electronic braking - a.k.a. "Falcon Claw"
	 * The more the "brake" is pulled, the slower the output speed
	 * @param inputSpeed The input value to scale back based on the brake input (1 to -1)
	 * @param brake The brake input value (0 to -1)
	 * @return The adjusted value
	 */
	private static double falconClaw(double inputSpeed, double brake){
		return ((1 - ((-MIN_DRIVE_SPEED + 1) * Math.abs(brake))) * inputSpeed);
	}
	
	/**
	 * Get the adjusted left joystick value when braking
	 * @return The driver's adjusted left joystick value
	 */
	public double getLeftBrakingSpeed(){
		
		double leftSpeed = exponentialize(driverJoystick.getLeftStickRaw_Y());
		
		// If the trigger (brake) is pressed, use falcon claw method
		if(driverJoystick.getLeftTriggerAxisRaw() > 0.00)
			leftSpeed = falconClaw(leftSpeed, driverJoystick.getLeftTriggerAxisRaw());
		 
		return leftSpeed;
	}
	
	/**
	 * Get the adjusted right joystick value when braking
	 * @return The driver's adjusted right joystick value
	 */
	public double getRightBrakingSpeed(){
		
		double rightSpeed = exponentialize(driverJoystick.getRightStickRaw_Y());
		
		// If the trigger (brake) is pressed, use falcon claw method
		if(driverJoystick.getRightTriggerAxisRaw() > 0.00)
			rightSpeed = falconClaw(rightSpeed, driverJoystick.getRightTriggerAxisRaw());
		
		return rightSpeed;
	}
	
}
