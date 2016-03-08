package org.team2168.commands.indexer;

import org.team2168.RobotMap;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IndexSingleBall extends CommandGroup {
    
    public  IndexSingleBall() {
    	//addSequential(new IntakeExtend());
    	addParallel(new IntakeWithConstant(-RobotMap.INTAKE_SPEED_CONSTANT));
    	addSequential(new RunIndexerUntilBoulderPresent());
    	addSequential(new IntakeWithConstant(0.0));
    	

    }
}
