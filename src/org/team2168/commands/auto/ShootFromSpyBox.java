package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.DriveShooterWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots boulder into high goal from spy box
 */
public class ShootFromSpyBox extends CommandGroup {
    
    public  ShootFromSpyBox() {

    	addParallel(new IntakeExtend(),2);
    	addParallel(new DriveShooterHoodToAngle(140));
    	addParallel(new DriveShooterPIDSpeed(0));

    	
    }
}
