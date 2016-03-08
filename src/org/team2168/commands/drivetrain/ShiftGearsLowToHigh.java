package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearsLowToHigh extends Command {

	public ShiftGearsLowToHigh()
	{
		//requires the driveTrain subsystems 
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() 
	{
		Robot.drivetrain.shiftGearsLowToHigh();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.drivetrain.gearIsHigh();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
