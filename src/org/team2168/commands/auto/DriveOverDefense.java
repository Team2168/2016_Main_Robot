package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives Robot over defense
 */
public class DriveOverDefense extends CommandGroup {
    
    public  DriveOverDefense() {
    	addSequential(new IntakeExtend(),3);
    	addSequential(new Sleep(), 6);
    	addSequential(new ShooterHoodStowPosition());
    	addSequential(new DriveXDistance(18, 0.7),10);
    }
}
