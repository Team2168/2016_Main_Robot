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
		operatorJoystick.ButtonLeftDPad().whenPressed(new IntakeExtend());
		operatorJoystick.ButtonRightDPad().whenPressed(new IntakeRetract());
		operatorJoystick.ButtonBack().whenPressed(new StowForLowBar());
		
		//operatorJoystick.ButtonStart().whenPressed(new VisionPosition());
		operatorJoystick.ButtonLeftTrigger().whileHeld(new IntakeWithConstant(-RobotMap.INTAKE_SPEED_CONSTANT));
		operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
//		operatorJoystick.ButtonRightTrigger().whileHeld(new IntakeSingleBall());
		operatorJoystick.ButtonLeftBumper().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT));
		operatorJoystick.ButtonRightBumper().whileHeld(new DriveIndexerWithConstant(-RobotMap.INDEXER_SPEED_CONSTANT));

		
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
}
