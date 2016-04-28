package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Port_de_cleullus extends Subsystem {
   
	public static Port_de_cleullus instance;
	
	DoubleSolenoid portCullusPiston;
	DigitalInput portCullusUp;
	DigitalInput portCullusDown;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Port_de_cleullus() {
		portCullusPiston = new DoubleSolenoid(RobotMap.PORTCULLUS_EXTEND, RobotMap.PORTCULLUS_REVERSE);
		portCullusUp = new DigitalInput(RobotMap.PORTICULLUS_POSITION_SENSOR_UP);
		portCullusDown = new DigitalInput(RobotMap.PORTICULLUS_POSITION_SENSOR_DOWN);
	}
	
	
	/**
	 * Returns a singleton object of the Intake Subsystem
	 * @return returns the current intake object
	 */
	public static Port_de_cleullus getInstance()
	{
		if(instance == null)
			instance = new Port_de_cleullus();
		
		return instance;
	}
	
	public void extendPortCullus()
	{
		portCullusPiston.set(Value.kForward);
	}
	
	public void retractPortCullus()
	{
		portCullusPiston.set(Value.kReverse);
	}
	
	public boolean isPortCullusExtended()
	{
		return !portCullusDown.get();
	}
	
	public boolean isPortCulluseRetracted()
	{
		return !portCullusUp.get();
	}

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

