package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearsHighToLow extends Command {

	public ShiftGearsHighToLow()
	{
		//requires the driveTrain subsystems 
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() 
	{
		Robot.drivetrain.shiftGearsHighToLow();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.drivetrain.gearIsLow();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
