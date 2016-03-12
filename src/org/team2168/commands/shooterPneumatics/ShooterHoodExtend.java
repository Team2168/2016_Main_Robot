package org.team2168.commands.shooterPneumatics;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Extends Shooter piston
 */
public class ShooterHoodExtend extends Command {

    public ShooterHoodExtend() {
    	requires(Robot.shooterPneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterPneumatics.extendShooter();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooterPneumatics.isShooterExtended();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
