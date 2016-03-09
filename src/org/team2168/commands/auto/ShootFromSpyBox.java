package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.autoFire.AutoFireFar;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.DriveShooterWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots boulder into high goal from spy box
 */
public class ShootFromSpyBox extends CommandGroup {
    
    public  ShootFromSpyBox() {

    	addSequential(new IntakeExtend(), 3);
    	addParallel(new AutoFireFar());
    	addSequential(new Sleep(), 5);
    	//TODO: Add PID Setpoint Stop Command
    	addSequential(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT), 1);
    	addParallel(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT), 1);
    	
    	addSequential(new ShooterPIDPause());
    	addSequential(new DriveIndexerWithConstant(0));
    	addSequential(new IntakeWithConstant(0));
    	
    }
}
