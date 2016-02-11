package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.Intake.IntakeWithJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is an intake system.
 * @author jkaroul
 */
public class IntakeRoller extends Subsystem {
    
	private Victor intakeWheel1;
	private Victor intakeWheel2;
	
	private DoubleSolenoid intakePiston;
	
	private static IntakeRoller instance = null;
	
	/**
	 * private constructor for the Singleton intake subsystem
	 * @author jkaroul
	 */
	private IntakeRoller()
	{
		intakePiston = new DoubleSolenoid(RobotMap.INTAKE_WHEEL_2, RobotMap.INTAKE_WHEEL_1);
		intakeWheel1 = new Victor(RobotMap.INTAKE_WHEEL_1);
		intakeWheel2 = new Victor(RobotMap.INTAKE_WHEEL_2);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motors
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntake(double speed)
	{
		intakeWheel1.set(speed);
		intakeWheel2.set(speed);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motor 1
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntakeWheel1(double speed)
	{
		if(RobotMap.reverseWheel1)
			speed = -speed;
			
		intakeWheel1.set(speed);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motor 2
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntakeWheel2(double speed)
	{
		if(RobotMap.reverseWheel2)
			speed = -speed;
			
		intakeWheel2.set(speed);
	
	}
	
	/**
	 * returns a singleton object of the intake subsystem
	 * @return returns the current intake object
	 * @author jkaroul
	 */
	
	public static IntakeRoller getInstance()
	{
		if(instance == null)
			instance = new IntakeRoller();
		
		return instance;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeWithJoystick());
    }
}
