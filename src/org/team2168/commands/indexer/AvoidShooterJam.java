package org.team2168.commands.indexer;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the indexer until a boulder is detected to be present
 * @author Ben Waid
 */
public class AvoidShooterJam extends Command {

    public AvoidShooterJam() {
        requires(Robot.indexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.shooter.isBoulderPresent())
    		Robot.indexer.setSpeed(-RobotMap.INDEXER_SPEED_REVERSE_CONSTANT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        	return !Robot.shooter.isBoulderPresent();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.indexer.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
