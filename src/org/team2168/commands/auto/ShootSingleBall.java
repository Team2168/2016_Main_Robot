package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.DriveShooterWithConstant;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots boulder into high goal
 */
public class ShootSingleBall extends CommandGroup {
    
    public  ShootSingleBall() {

    	//TODO add method to set shooter hood to correct angle once we test for it
    	addParallel(new DriveShooterWithConstant(1.0));
    	addSequential(new IntakeExtend());
    	addSequential(new Sleep(), 6.0);
    	addParallel(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT));
    	addSequential(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT), 3.0);
    	addSequential(new DriveShooterWithConstant(0));
    	addSequential(new IntakeWithConstant(0));  
    	addSequential(new DriveIndexerWithConstant(0));
    	
    	
    }
}
