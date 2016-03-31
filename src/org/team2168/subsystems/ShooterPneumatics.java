package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Moves shooter hood using pneumatics
 */
public class ShooterPneumatics extends Subsystem {
    
	private DoubleSolenoid shooterPistonFWD;
	private DoubleSolenoid shooterPistonAFT;
	
	private static ShooterPneumatics instance = null;
	
	/**
	 * Private constructor for ShooterPnematics subsystem
	 */
	private ShooterPneumatics() {
		shooterPistonFWD = new DoubleSolenoid(RobotMap.SHOOTER_FWD_RETRACT, RobotMap.SHOOTER_FWD_EXTEND);
		shooterPistonAFT = new DoubleSolenoid(RobotMap.SHOOTER_AFT_RETRACT, RobotMap.SHOOTER_AFT_RETRACT);
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
	 * Extends the shooter FWD piston to the down position
	 */
	public void extendShooterFWD()
	{
		shooterPistonFWD.set(Value.kForward);
	}
	
	/**
	 * Retracts the shooter FWD piston to the up position
	 */
	public void retractShooterFWD()
	{
		shooterPistonFWD.set(Value.kReverse);
	}
	
	/**
	 * Extends the shooter AFT piston to the down position
	 */
	public void extendShooterAFT()
	{
		shooterPistonAFT.set(Value.kForward);
	}
	
	/**
	 * Extends the shooter AFT piston to the up position
	 */
	public void retractShooterAFT()
	{
		shooterPistonAFT.set(Value.kReverse);
	}
	
	/**
	 * Returns true if shooter FWD piston is extended
	 * @return
	 */
	public boolean isShooterFWDExtended(){
		return shooterPistonFWD.get() == DoubleSolenoid.Value.kForward;
	}
	
	/**
	 * Returns true if shooter FWD piston is retracted
	 * @return
	 */
	public boolean isShooterFWDRetracted(){
		return shooterPistonFWD.get() == DoubleSolenoid.Value.kReverse;
	}
	
	/**
	 * Returns true if shooter AFT piston is extended
	 * @return
	 */
	public boolean isShooterAFTExtended(){
		return shooterPistonAFT.get() == DoubleSolenoid.Value.kForward;
	}
	
	/**
	 * Returns true is shooter AFT piston is retracted
	 * @return
	 */
	public boolean isShooterAFTRetracted(){
		return shooterPistonAFT.get() == DoubleSolenoid.Value.kReverse;
	}

	public boolean isShooterCloseShotPosition() {
		return isShooterFWDExtended() && isShooterAFTRetracted();
	}
	
	public boolean isShooterFarShotPosition() {
		return isShooterFWDExtended() && isShooterAFTExtended();
	}
	
	public boolean isShooterStowPosition() {
		return isShooterFWDRetracted() && isShooterAFTRetracted();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

