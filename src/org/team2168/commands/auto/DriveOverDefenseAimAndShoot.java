package org.team2168.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives over the outer works, aims at the high goal and shoots
 */
public class DriveOverDefenseAimAndShoot extends CommandGroup {
    
    public  DriveOverDefenseAimAndShoot() {
    	addSequential(new DriveOverDefense());
    	//TODO implement vision to aim
    	addSequential(new ShootSingleBall());
    }
}
