package org.team2168.commands.shooterhood;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveShooterHoodWithJoysticks extends Command {

    public DriveShooterHoodWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterhood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Do not Initialize on Startup, leave hood where it is, but we should always make sure
    	//it is physically installed at one end of travel.
    	//Shooter is installed such that 180 deg is hood down position, and decreasing the angle raises hood. 
    	Robot.shooterhood.setAngle(180);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Takes current angle of servo and adds to it based on how far
    	//the right joystick is pushed
    	//TODO calibrate HOOD_JOYSTICK_MULTIPLIER value
    	if (Math.abs(Robot.oi.operatorJoystick.getRightStickRaw_Y())>0.1)
    		Robot.shooterhood.setAngle(Robot.shooterhood.getAngle() - (RobotMap.HOOD_JOYSTICK_MULTIPLIER*(Robot.oi.operatorJoystick.getRightStickRaw_Y())));
    	//System.out.println("Shooter Hood Angle: " + Robot.shooterhood.getAngle());
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
