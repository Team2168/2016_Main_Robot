package org.team2168.commands.intakeroller;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the intake until a boulder is detected to be present
 * @author Ben Waid
 */
public class RunIntakeUntilBoulderPresent extends Command {
	
	double speed;
	
    public RunIntakeUntilBoulderPresent(double speed) {
    	requires(Robot.shooter);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.intakeRoller.driveIntake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.intakeRoller.isBoulderPresent();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeRoller.driveIntake(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
