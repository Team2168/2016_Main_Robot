package org.team2168.commands.intakeroller;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for the wheel intake
 * @author jkaroul
 */
public class IntakeWithJoystick extends Command {

    public IntakeWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeRoller.driveIntake(Robot.oi.operatorJoystick.getLeftStickRaw_Y());
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