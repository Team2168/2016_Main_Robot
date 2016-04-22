package org.team2168.commands.drivetrain;

import javax.swing.text.Utilities;

import org.team2168.Robot;
import org.team2168.utils.NewFalconClaw;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {
	
	NewFalconClaw falconClaw;
	
	double joystickRawRight;
	double joystickInterpolatedRight;
	
	double joystickRawLeft;
	double joystickInterpolatedLeft;

    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        falconClaw = new NewFalconClaw();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.oi.driverJoystick.getLeftTriggerAxisRaw() > 0.5) {
//    		Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getLeftStickRaw_Y() * 0.5);
//        	Robot.drivetrain.driveRight(Robot.oi.driverJoystick.getRightStickRaw_Y() * 0.5);
//    	}

    	Robot.drivetrain.driveLeft(falconClaw.getAdjustedJoystickValue(Robot.oi.driverJoystick.getLeftStickRaw_Y(), Robot.oi.driverJoystick.getLeftTriggerAxisRaw()));
    	Robot.drivetrain.driveRight(falconClaw.getAdjustedJoystickValue(Robot.oi.driverJoystick.getRightStickRaw_Y(), Robot.oi.driverJoystick.getLeftTriggerAxisRaw()));

    	joystickRawLeft = Robot.oi.driverJoystick.getLeftStickRaw_Y();
    	joystickInterpolatedLeft = falconClaw.getAdjustedJoystickValue(joystickRawLeft, 0);
    	
    	joystickRawRight = Robot.oi.driverJoystick.getRightStickRaw_Y();
    	joystickInterpolatedRight = falconClaw.getAdjustedJoystickValue(joystickRawRight, 0);
    	
    	System.out.println("Joystick Raw Left: \t" + joystickRawLeft + " Joystick Interpolated Left: \t " + joystickInterpolatedLeft);
    	
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
