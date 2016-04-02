package org.team2168.commands.intakeroller;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Waits until the Cheval de frise is present in front of the robot.
 * This command will only work reliably when the intake is raised.
 * @author james
 */
public class WaitUntilCDFPresent extends Command {

    public WaitUntilCDFPresent() {
        requires(Robot.intakeRoller);
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
		//Do nothing
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return Robot.intakeRoller.getAveragedRawBoulderDistance()
        		>= RobotMap.CHEVAL_DE_FRISE_DISTANCE_VOLTAGE;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    	//Do nothing
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}