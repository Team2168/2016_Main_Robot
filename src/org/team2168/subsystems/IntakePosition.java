package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the double solenoids
 */
public class IntakePosition extends Subsystem {
    
	private DoubleSolenoid intakePiston;
	private DigitalInput intakePositionSensor1;
	private DigitalInput intakePositionSensor2;
	
	private static IntakePosition instance = null;
	
	/**
	 * Private constructor for the Singleton Intake subsystem
	 */
	private IntakePosition()
	{
		intakePiston = new DoubleSolenoid(RobotMap.INTAKE_RETRACT, RobotMap.INTAKE_EXTEND);
		intakePositionSensor1 = new DigitalInput(RobotMap.INTAKE_POSITION_SENSOR_1);
		intakePositionSensor2 = new DigitalInput(RobotMap.INTAKE_POSITION_SENSOR_2);
	}
	
	/**
	 * Returns a singleton object of the Intake Subsystem
	 * @return returns the current intake object
	 */
	public static IntakePosition getInstance()
	{
		if(instance == null)
			instance = new IntakePosition();
		
		return instance;
	}
	
	/**
	 * Extends the intake piston to the down position
	 */
	public void extendIntake()
	{
		intakePiston.set(Value.kForward);
	}
	
	/**
	 * Retracts the intake piston to the up position
	 */
	public void retractIntake()
	{
		intakePiston.set(Value.kReverse);
	}
	
	/**
	 * Returns true if the intake piston has been extended
	 * @return true when kForward, false otherwise
	 */
	public boolean isIntakeExtended()
	{
		return !intakePositionSensor1.get() && !intakePositionSensor2.get();
	}
	
	/**
	 * Returns true if the intake piston has been retracted 
	 * @return true when kReverse, false otherwise
	 */
	public boolean isIntakeRetracted()
	{
		return intakePositionSensor1.get() && intakePositionSensor2.get();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
