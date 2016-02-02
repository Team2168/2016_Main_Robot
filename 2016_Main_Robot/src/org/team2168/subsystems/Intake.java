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
public class Intake extends Subsystem {
    
	private Victor intakeWheel;
	private Victor intakeIndex;
	
	private DoubleSolenoid intakePiston;
	
	private static Intake instance = null;
	
	/**
	 * private constructor for the Singleton intake subsystem
	 * @author jkaroul
	 */
	private Intake()
	{
		intakePiston = new DoubleSolenoid(RobotMap.intakeWheel2, RobotMap.intakeWheel1);
		intakeWheel = new Victor(RobotMap.intakeWheel1);
		intakeIndex = new Victor(RobotMap.intakeWheel2);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motors
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntake(double speed)
	{
		intakeWheel.set(speed);
		intakeIndex.set(speed);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motor 1
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntakeWheel1(double speed)
	{
		intakeWheel.set(speed);
	}
	
	/**
	 * takes in a double as a speed and sets it too the intake wheel motor 2
	 * @param speed is of type double from 1 to -1
	 * @author jkaroul
	 */
	public void driveIntakeWheel2(double speed)
	{
		intakeIndex.set(speed);
	}
	
	/**
	 * returns a singleton object of the intake subsystem
	 * @return returns the current intake object
	 * @author jkaroul
	 */
	
	public static Intake getInstance()
	{
		if(instance == null)
			instance = new Intake();
		
		return instance;
	}
	
	/**
	 * extends the intake piston to the down position
	 * @author jkaroul
	 */
	
	public void extendIntake()
	{
		intakePiston.set(Value.kForward);
	}
	
	/**
	 * retracts the intake piston to the up position
	 * @author jkaroul
	 */
	public void retractIntake()
	{
		intakePiston.set(Value.kReverse);
	}
	
	/**
	 * returns true if the intake piston has been extended
	 * @return true when kForward false otherwise
	 * @author jkaroul
	 */
	
	public boolean isIntakeExtended()
	{
		return intakePiston.get() == Value.kForward;
	}
	
	/**
	 * Returns true if the intake piston has been retracted 
	 * @return true when kReverse, false otherwise
	 * @author jkaroul
	 */
	
	public boolean isIntakeRetracted()
	{
		return intakePiston.get() ==  Value.kReverse;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeWithJoystick());
    }
}

