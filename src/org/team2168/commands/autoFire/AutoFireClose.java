package org.team2168.commands.autoFire;

import org.team2168.RobotMap;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooterPneumatics.ShooterExtend;
import org.team2168.commands.shooterPneumatics.ShooterRetract;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFireClose extends CommandGroup {
    
    public  AutoFireClose() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new ShooterRetract());
    	addParallel(new DriveShooterPIDSpeed(4250));
    }
}
