package org.team2168.commands.shooter;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the shooter until a boulder is detected to be present
 * @author Ben Waid
 */
public class RunShooterUntilBoulderPresent extends Command {

    public RunShooterUntilBoulderPresent() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.shooter.driveShooter(RobotMap.SHOOTER_CONSTANT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        	return Robot.shooter.isBoulderPresent();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
