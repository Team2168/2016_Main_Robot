package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Moves shooter hood using pneumatics
 */
public class ShooterPneumatics extends Subsystem {
    
	private DoubleSolenoid shooterPiston;
	
	private static ShooterPneumatics instance = null;
	
	/**
	 * Private constructor for ShooterPnematics subsystem
	 */
	private ShooterPneumatics() {
		shooterPiston = new DoubleSolenoid(RobotMap.SHOOTER_RETRACT, RobotMap.SHOOTER_EXTEND);
	}
	
	/**
	 * Returns singleton instance of ShooterPneumatics subsystem
	 * @return the current ShooterPneumatics object
	 */
	public static ShooterPneumatics getInstance(){
		if(instance == null)
			instance = new ShooterPneumatics();
		
		return instance;
	}
	
	/**
	 * Extends the shooter piston to the down position
	 */
	public void extendShooter()
	{
		shooterPiston.set(Value.kForward);
	}
	
	/**
	 * Retracts the shooter piston to the up position
	 */
	public void retractShooter()
	{
		shooterPiston.set(Value.kReverse);
	}
	
	/**
	 * Returns true if shooter piston is extended
	 * @return
	 */
	public boolean isShooterExtended(){
		return shooterPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	/**
	 * Returns true if shooter piston is retracted
	 * @return
	 */
	public boolean isShooterRetracted(){
		return shooterPiston.get() == DoubleSolenoid.Value.kReverse;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

