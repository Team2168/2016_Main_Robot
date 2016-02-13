package org.team2168;

import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.intakeroller.IntakeWithJoystick;
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
	
	public static final double MIN_DRIVE_SPEED = 0.222;
	public static double joystickScale[][] = {
		/* Scaled output of the joystick from its input */	
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
		{-1.00, -1.00}
	};
	
	///////////////////
	// Driver Joystick
	///////////////////
	
	/**
	 * A method to modify the joystick values using linear interpolation
	 * It augments the joystick value going to the motor controllers to widen
	 * the region of "fine" control while still allowing full speed
	 * 
	 * @param input The value to augment
	 * @return The adjusted value
	 */
	private double interpolate(double input){
		double retVal = 0.0;
		boolean done = false;
		double m, b;
		
		// Keeping input between 1 and -1
		if(input > 1.0)
			input = 1.0;
		else if(input < -1.0)
			input = -1.0;
		
		// Find the two points in the array which the input falls between
		// Starts at i = 1 since points can't fall out of the array
		for(int i = 1; !done && i < joystickScale.length; i++){
			if(input >= joystickScale[i][0]){
				// Found the point that falls in the array, between index i and i - 1
				// Calculate the equation for the line y = m*x + b
				m = (joystickScale[i][0] - joystickScale[i - 1][1])/(joystickScale[i][0] - joystickScale[i - 1][0]);
				b = joystickScale[i][1] - (m * joystickScale[i][0]);
				retVal = m * input + b;
				
				// It's finished, don't continue to loop
				done = true;
			}
		}
		
		return retVal;
	}
	
	/**
	 * Electronic braking - code-named "Falcon Claw"
	 * The more the "brake" is pulled, the slower output speed
	 * 
	 * @param inputSpeed The input value to scale back based on brake input (1 to -1)
	 * @param brake The brake input value (0 to -1)
	 * @return The adjusted value
	 */
	private double falconClaw(double inputSpeed, double brake){
		return ((1 - ((-MIN_DRIVE_SPEED + 1) * Math.abs(brake))) * inputSpeed);
	}
	
	/**
	 * Get the adjusted left joystick value
	 * @return The driver's left joystick value
	 */
	public double getbaseDriverLeftAxis(){
		
		double leftSpeed = interpolate(Robot.oi.driverJoystick.getLeftStickRaw_Y());
		
		// If the trigger (brake) is pressed, use falcon claw method
		if(Robot.oi.driverJoystick.getLeftStickRaw_Y() < -0.01)
			leftSpeed = falconClaw(leftSpeed, Robot.oi.driverJoystick.getLeftStickRaw_Y());
		
		// Flip sign so up on joystick is a positive value (forward motion) 
		return -leftSpeed;
	}
	

	private static OI instance = null;
	
	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI(){
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);

		//Driver Joystick Buttons


		//Operator Joystick Buttons
		//TODO calibrate value
		operatorJoystick.ButtonA().whenPressed(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
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
}
