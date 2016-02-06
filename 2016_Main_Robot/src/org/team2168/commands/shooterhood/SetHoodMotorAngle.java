package org.team2168.commands.shooterhood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetHoodMotorAngle extends Command {

    public SetHoodMotorAngle() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterhood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    /**
     * takes in a value for the motor's position and moves motor to said position
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterhood.setAngle(RobotMap.SHOOTER_HOOD_ANGLE);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.shooterhood.getAngle() == RobotMap.SHOOTER_HOOD_ANGLE)
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
