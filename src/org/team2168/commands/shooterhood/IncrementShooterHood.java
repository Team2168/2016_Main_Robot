package org.team2168.commands.shooterhood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This command increments the driver hood by 10 degrees in the direction given
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
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterhood);
        
        if(!forward) {
          direction = -direction;
          
          endAngle = Robot.shooterhood.getAngle() + direction;
        }
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    /**
     * increments the servo by 10 degrees in the direction given
     */
    protected void execute() {
    	Robot.shooterhood.setAngle(endAngle);
    }

    /**
     * when the angle of the motor matches the given angle
     */
    // Make this return true when this Command no longer needs to run execute()
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
