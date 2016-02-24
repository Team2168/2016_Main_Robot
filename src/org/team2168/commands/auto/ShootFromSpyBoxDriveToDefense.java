package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots into the high goal from the spy box and then drives to the outer works
 */
public class ShootFromSpyBoxDriveToDefense extends CommandGroup {
    
    public  ShootFromSpyBoxDriveToDefense() {
    	addSequential(new ShootFromSpyBox());
    	addSequential(new RotateXDistancePIDZZZ(90));
    	//TODO test the distance to the defenses
    	addSequential(new DriveXDistance(15));
    }
}
