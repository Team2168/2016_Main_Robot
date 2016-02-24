package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intakeposition.IntakeExtend;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots into the high goal from the spy box and then drives through the low bar
 */
public class ShootFromSpyBoxDriveOverDefenses extends CommandGroup {
    
    public  ShootFromSpyBoxDriveOverDefenses() {
    	addSequential(new ShootFromSpyBox());
    	addSequential(new RotateXDistancePIDZZZ(90));
    	addSequential(new IntakeExtend());
    	//TODO test the distance to cross the defenses
    	addSequential(new DriveXDistance(20));
    }
}
