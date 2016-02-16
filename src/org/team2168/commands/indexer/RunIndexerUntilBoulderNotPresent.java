package org.team2168.commands.indexer;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the indexer until a boulder is detected to not be present
 * @author Ben Waid
 */
public class RunIndexerUntilBoulderNotPresent extends Command {

    public RunIndexerUntilBoulderNotPresent() {
        requires(Robot.indexer);
        requires(Robot.intakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.indexer.isBoulderPresent())
    		Robot.indexer.setSpeed(RobotMap.INDEXER_SPEED_CONSTANT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        	return !Robot.indexer.isBoulderPresent();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
