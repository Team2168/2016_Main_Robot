package org.team2168.commands.indexer;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the indexer using the right joystick on the operator controller
 */
public class DriveIndexerWithJoysticks extends Command {

    public DriveIndexerWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.indexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.indexer.setSpeed(Robot.oi.operatorJoystick.getRightStickRaw_Y());
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
