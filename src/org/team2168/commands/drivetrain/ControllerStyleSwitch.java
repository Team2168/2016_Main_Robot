package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**Choose and use a certain control style.
 *@author Ben Waid, Elijah Reeds, Peter Dentch
 */
public class ControllerStyleSwitch extends Command {
	
	int ctrlStyle;
	
	/**
	 * Controller Styles
	 * 0 = Tank Drive
	 * 1 = Arcade Drive
	 * 2 = GTA
	 * @param inputStyle
	 */
    public ControllerStyleSwitch(int inputStyle) {
        requires(Robot.drivetrain);
        ctrlStyle = inputStyle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    //TODO NEEDS TESTING ON ACTUAL ROBOT
    protected void execute() {
    	/**
    	 *Tank Drive
    	 */
    	if(ctrlStyle == 0){
        	Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getLeftStickRaw_Y());
        	Robot.drivetrain.driveRight(Robot.oi.driverJoystick.getRightStickRaw_Y());
    	}
    	
    	/**
    	 * Arcade Drive
    	 */
    	else if(ctrlStyle == 1) {
    		Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getLeftStickRaw_Y() + Robot.oi.driverJoystick.getRightStickRaw_X());
    		Robot.drivetrain.driveRight(Robot.oi.driverJoystick.getLeftStickRaw_Y() - Robot.oi.driverJoystick.getRightStickRaw_X());
    	}
    	
    	/**
    	 * GTA Drive
    	 */
    	else if(ctrlStyle == 2) {
    		double fwdSpeed = Robot.oi.driverJoystick.getRightTriggerAxisRaw();
    		double revSpeed = Robot.oi.driverJoystick.getLeftTriggerAxisRaw();
    		double speed = fwdSpeed - revSpeed;
    		
    		//Allows Robot to spin in place without needing to press in triggers
    		if(speed != 0){
    			Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getRightStickRaw_X() * speed);
    			Robot.drivetrain.driveRight(-(Robot.oi.driverJoystick.getRightStickRaw_X()) * speed);
    		}
    		else if(speed == 0) {
    			Robot.drivetrain.driveLeft(Robot.oi.driverJoystick.getRightStickRaw_X());
    			Robot.drivetrain.driveRight(-(Robot.oi.driverJoystick.getRightStickRaw_X()));
    		}
    	}
    	
    	/**
    	 * Default back to tank drive if input is invalid
    	 */
    	else {
    		ctrlStyle = 0;
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
