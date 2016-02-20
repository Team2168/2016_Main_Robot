package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives Robot over defense
 */
public class DriveOverDefense extends CommandGroup {
    
    public  DriveOverDefense() {
    	addSequential(new IntakeExtend());
        addSequential(new DriveWithConstant(0.6, 0.6), 3.0);
        addSequential(new DriveWithConstant(0, 0));
    }
}
