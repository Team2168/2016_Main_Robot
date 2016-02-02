package org.team2168.commands.shooterhood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetHoodWheelPosition extends Command {

    public SetHoodWheelPosition() {
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
    	Robot.shooterhood.setPosition(RobotMap.DRIVE_HOOD_MOTOR);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
