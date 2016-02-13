package org.team2168.commands.shooterhood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This command increments the driver hood by 10 degrees in the direction given
 *true is forward, false is backwards
 *@author Ben Waid
 */
public class IncrementShooterHood extends Command {

	private double direction = 10.0;
	private double endAngle;
	
	/**
	 * This is the constructor which sets inputAngle to the double angle
	 * @param takes in inputAngle
	 */
	public IncrementShooterHood(boolean forward) {
        requires(Robot.shooterhood);
        
        if(!forward) {
          direction = -direction;
          
          endAngle = Robot.shooterhood.getAngle() + direction;
        }
    }

    protected void initialize() {
    }
    
    /**
     * increments the servo by 10 degrees in the direction given
     */
    protected void execute() {
    	Robot.shooterhood.setAngle(endAngle);
    }

    /**
     * When the angle of the motor matches the given angle the command ends
     */
    protected boolean isFinished() {
        if (Robot.shooterhood.getAngle() == endAngle)
        	return true;
        else
        	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
