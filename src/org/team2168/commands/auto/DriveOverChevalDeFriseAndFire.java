package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.autoFire.AutoFireFar;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives Robot over CDF and fires ball at high goal
 */
public class DriveOverChevalDeFriseAndFire extends CommandGroup {
    
    public  DriveOverChevalDeFriseAndFire() {
	    	//Drive over CDF
	    	addSequential(new DriveOverChevalDeFrise());
	    		    	
	    	//Get shooter up to speed
	    	addSequential(new AutoFireFar());
	    	
	    	//Rotate toward goal
	    	addSequential(new RotateXDistancePIDZZZ(-30.0, 0.7, 0.25, 1), 2);
	    	
	    	//Fire - why isn't this in some command group we can call from all auto modes - ugly
	    	//ADD CODE FOR FIRING THE BALL
//	    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT));
//	    	addParallel(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
//	    	addSequential(new Sleep(), 2);
//	    	addSequential(new DriveIndexerWithConstant(0));
//	    	addSequential(new IntakeWithConstant(0));
//	    	addSequential(new ShooterHoodStowPosition());
//	    	addSequential(new ShooterPIDPause());
    }
}
