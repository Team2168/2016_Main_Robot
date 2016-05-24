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
    	Robot.drivetrain.tankDrive(Robot.oi.driverJoystick.getRightTriggerAxisRaw(), Robot.oi.driverJoystick.getRightTriggerAxisRaw());
    	Robot.drivetrain.tankDrive(-Robot.oi.driverJoystick.getLeftTriggerAxisRaw(), -Robot.oi.driverJoystick.getLeftTriggerAxisRaw());
    	Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getRightStickRaw_X());
    	Robot.drivetrain.driveRight(Robot.oi.driverJoystick.getRightStickRaw_X());
    	
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
