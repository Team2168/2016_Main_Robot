package org.team2168;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.team2168.commands.auto.DriveOverLowGoalAndFire;
import org.team2168.commands.auto.ShootFromSpyBoxAutoAlign;
import org.team2168.commands.auto.ShootFromSpyBoxNewHood;
import org.team2168.commands.autoFire.AutoFireClose;
import org.team2168.commands.autoFire.AutoFireFar;
import org.team2168.commands.autoFire.AutoFireFarTeleop;
import org.team2168.commands.drivetrain.*;
import org.team2168.commands.drivetrain.PIDCommands.*;
import org.team2168.commands.flashlight.DisableFlashlight;
import org.team2168.commands.flashlight.EnableFlashlight;
import org.team2168.commands.indexer.*;
import org.team2168.commands.intakeposition.*;
import org.team2168.commands.intakeroller.*;
import org.team2168.commands.pneumatics.*;
import org.team2168.commands.shooter.*;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooterPneumatics.ShooterHoodBothExtend;
import org.team2168.commands.shooterPneumatics.ShooterHoodCloseShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodFarShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

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
	public F310 commandsTestJoystick;

	private static OI instance = null;
	
	/**
	 * Private constructor for singleton class which instantiates the OI object
	 */
	private OI(){
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
		commandsTestJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);

		/********************************************
		 *         Driver Joystick Buttons          *
		 ********************************************/
		//Left Bumper
		driverJoystick.ButtonLeftBumper().whenPressed(new DrivePIDPause());
		
		//Right Bumper
		driverJoystick.ButtonRightBumper().whenPressed(new DriveXDistance(-0.3, 0.7, 0.1));


		/********************************************
		 *        Operator Joystick Buttons         *
		 ********************************************/
		//TODO calibrate values and create commands for commented out buttons
		
		//D Pad
		operatorJoystick.ButtonLeftDPad().whenPressed(new IntakeRetract());
		operatorJoystick.ButtonRightDPad().whenPressed(new IntakeExtend());
		
		//Kill Drivetrain PID (Back Button)
		operatorJoystick.ButtonBack().whenPressed(new StowForLowBar());
		operatorJoystick.ButtonBack().whenPressed(new DrivePIDPause());
		
		//Camera Shot Align (Start Button)
		operatorJoystick.ButtonStart().whenPressed(new RotateXDistancePIDZZZCameraWithGyro(0, 0.4, 0.22, 0.5));
		//operatorJoystick.ButtonStart().whenPressed(new DriveShooterPIDSpeed(4300));
		//Shoot Ball (A)
		operatorJoystick.ButtonA().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT));
		operatorJoystick.ButtonA().whileHeld(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
		
		//Kill Shooter (B)
		operatorJoystick.ButtonB().whenPressed(new ShooterPIDPause());
		operatorJoystick.ButtonB().whenPressed(new ShooterHoodStowPosition());
		operatorJoystick.ButtonB().whenPressed(new DrivePIDPause());
    	
		
		//Shoot Far Preset (Y)
		operatorJoystick.ButtonY().whenPressed(new DriveShooterPIDSpeed(6700));
		operatorJoystick.ButtonY().whenPressed(new ShooterHoodFarShotPosition());
		
		//Shoot Close Preset (X)
		operatorJoystick.ButtonX().whenPressed(new DriveShooterPIDSpeed(3900));
		operatorJoystick.ButtonX().whenPressed(new ShooterHoodCloseShotPosition());
		
		//Intake Ball (Right Trigger)
		operatorJoystick.ButtonRightTrigger().whileHeld(new IndexSingleBall());
		
		
		//Outtake Ball (Left Trigger)
		operatorJoystick.ButtonLeftTrigger().whileHeld(new IntakeWithConstant(-RobotMap.INTAKE_SPEED_CONSTANT));
		operatorJoystick.ButtonLeftTrigger().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPIT_SPEED_CONSTANT));

		
		//Manual Hood Operations (Left and Right Bumpers)
		operatorJoystick.ButtonRightBumper().whileHeld(new ShooterHoodFarShotPosition());
		operatorJoystick.ButtonLeftBumper().whileHeld(new ShooterHoodStowPosition());
		
		
		
	
		/********************************************
		 *        Command Test  Joystick Buttons         *
		 ********************************************/
		//commandsTestJoystick.ButtonX().whenPressed(new RotateXDistancePIDZZZCamera(0, 0.725, 0.22, 0.4));
		//commandsTestJoystick.ButtonA().whenPressed(new DrivePIDPause());
		
		commandsTestJoystick.ButtonA().whileHeld(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT));
		commandsTestJoystick.ButtonA().whileHeld(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
	
		
		commandsTestJoystick.ButtonLeftBumper().whenPressed(new DriveShooterPIDSpeed(0));
		commandsTestJoystick.ButtonB().whenPressed(new ShooterPIDPause());
		commandsTestJoystick.ButtonB().whenPressed(new DrivePIDPause());

		commandsTestJoystick.ButtonX().whenPressed(new DriveXDistance(12, 0.5, 0.1));
		
		//commandsTestJoystick.ButtonY().whenPressed(new RotateXDistancePIDZZZ(-40, 0.7, 0.25, 1));
		commandsTestJoystick.ButtonY().whenPressed(new DriveOverLowGoalAndFire());
		commandsTestJoystick.ButtonRightBumper().whenPressed(new DriveOverLowGoalAndFire());
		
		commandsTestJoystick.ButtonStart().whenPressed(new ShooterHoodBothExtend());
		commandsTestJoystick.ButtonBack().whenPressed(new ShooterHoodStowPosition());
		commandsTestJoystick.ButtonRightTrigger().whenPressed(new ShooterHoodFarShotPosition());
		commandsTestJoystick.ButtonLeftTrigger().whenPressed(new ShooterHoodCloseShotPosition());
		
		//commandsTestJoystick.ButtonStart().whenPressed(new RotateXDistancePIDZZZCameraWithGyro(0, 0.4, 0.22, 0.1));
		
//		commandsTestJoystick.ButtonRightBumper().whenPressed(new ShootFromSpyBoxNewHood());
//		commandsTestJoystick.ButtonLeftBumper().whenPressed(new DriveOverLowGoalAndFire());
		
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
