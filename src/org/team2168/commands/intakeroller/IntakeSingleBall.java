package org.team2168.commands.intakeroller;

import org.team2168.RobotMap;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeSingleBall extends CommandGroup {
    
    public  IntakeSingleBall() {
    	addSequential(new IntakeExtend());
    	addSequential(new RunIntakeUntilBoulderPresent(RobotMap.INTAKE_SPEED_CONSTANT));
    	addSequential(new RunIntakeUntilBoulderNotPresent(RobotMap.INTAKE_SPEED_CONSTANT));
    	addSequential(new IntakeWithConstant(0.0));
    	
    	//Jog the indexer to engage the ball
    	addSequential(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT), 0.1);
    	addSequential(new DriveIndexerWithConstant(0.0));
    	addSequential(new DriveIndexerWithConstant(-RobotMap.INDEXER_SPEED_CONSTANT), 0.1);
    	addSequential(new DriveIndexerWithConstant(0.0));
    	
    }
    
}
