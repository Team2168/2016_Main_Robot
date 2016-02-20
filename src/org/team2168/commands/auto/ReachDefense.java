package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive up to a defense
 */
public class ReachDefense extends CommandGroup {
    
    public  ReachDefense() {
    	addSequential(new IntakeExtend());
        addSequential(new DriveWithConstant(0.3, 0.3), 3.0);
        addSequential(new DriveWithConstant(0, 0));
    }
}
