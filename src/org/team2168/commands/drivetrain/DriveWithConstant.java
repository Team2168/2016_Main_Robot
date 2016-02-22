package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives drivetrain with a constant
 * @author Ben Waid
 */
public class DriveWithConstant extends Command {

	double speedLeft;
	double speedRight;
	
    public DriveWithConstant(double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        speedLeft = leftSpeed;
        speedRight = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.drivetrain.driveLeft(speedLeft);
    	Robot.drivetrain.driveRight(speedRight);
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
