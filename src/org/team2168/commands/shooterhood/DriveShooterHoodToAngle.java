package org.team2168.commands.shooterhood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This command sets the angle of the DriveShooterHood motor
 *@author Harris Jilani
 */
public class DriveShooterHoodToAngle extends Command {

	double angle;
	
	/**
	 * This is the constructor which sets inputAngle to the double angle
	 * @param takes in inputAngle
	 */
	public DriveShooterHoodToAngle(double inputAngle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterhood);
        
        angle = inputAngle;
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterhood.setAngle(angle);
    }
    
    /**
     * takes in a double angle for the motor's position and moves motor to said position
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    /**
     * when the angle of the motor equals the set angle, the command terminates
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.shooterhood.getAngle() == angle)
        	return true;
        else
        	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
