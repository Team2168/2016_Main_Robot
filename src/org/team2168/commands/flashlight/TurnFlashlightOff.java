package org.team2168.commands.flashlight;

import org.team2168.Robot;
import org.team2168.subsystems.Flashlight;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnFlashlightOff extends Command {

	Flashlight flashlight;
	
    public TurnFlashlightOff() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.flashlight);
    	flashlight = Robot.flashlight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	flashlight.setFlashlightOff();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
