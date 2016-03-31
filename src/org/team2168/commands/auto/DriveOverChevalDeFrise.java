package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.WaitUntilCDFPresent;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives Robot over defense
 */
public class DriveOverChevalDeFrise extends CommandGroup {
    
    public  DriveOverChevalDeFrise() {
    	//Drive slow until we see the CDF
    	addParallel(new DriveXDistance(3, 0.4, 0.1), 4);
    	addSequential(new WaitUntilCDFPresent(), 15); //If we don't detect the CDF, waste the clock.
    	
    	//CDF detected. Stop driving, lower intake
    	addSequential(new DriveWithConstant(0.0, 0.0));
    	addSequential(new IntakeExtend(), 3);
    	
    	//Drive remaining distance over CDF
    	addSequential(new DriveXDistance(8, 0.5, 0.1), 5);
    }
}
