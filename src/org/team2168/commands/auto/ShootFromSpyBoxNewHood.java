package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.autoFire.AutoFireClose;
import org.team2168.commands.autoFire.AutoFireFar;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.DriveShooterWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooterPneumatics.ShooterHoodRetract;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots boulder into high goal from spy box
 */
public class ShootFromSpyBoxNewHood extends CommandGroup {
    
    public  ShootFromSpyBoxNewHood() {

    	addSequential(new IntakeExtend(), 3);
    	
    	addSequential(new DriveXDistance(7, 0.4),10);
    	addParallel(new AutoFireClose());
    	
    	addSequential(new Sleep(), 6);
    	//TODO: Add PID Setpoint Stop Command
    	addSequential(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT), 1);
    	addParallel(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT), 1);
    	addSequential(new Sleep(), 2);
    	addSequential(new ShooterHoodRetract());
    	addSequential(new ShooterPIDPause());
    	addSequential(new DriveIndexerWithConstant(0));
    	addSequential(new IntakeWithConstant(0));
    	
    }
}
