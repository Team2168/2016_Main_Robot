package org.team2168.commands.shooterPneumatics;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterHoodBothExtend extends Command {

    public ShooterHoodBothExtend() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterPneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterPneumatics.extendShooterFWD();
    	Robot.shooterPneumatics.extendShooterAFT();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.shooterPneumatics.isShooterAFTExtended() && Robot.shooterPneumatics.isShooterFWDExtended());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
