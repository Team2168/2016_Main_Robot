package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.driverJoystick.getLeftTriggerAxisRaw() > 0.5) {
    		Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getLeftStickRaw_Y() * 0.5);
        	Robot.drivetrain.driveRight(Robot.oi.driverJoystick.getRightStickRaw_Y() * 0.5);
    	}
    	else {
    	Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getLeftStickRaw_Y());
    	Robot.drivetrain.driveRight(Robot.oi.driverJoystick.getRightStickRaw_Y());
    	}
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
