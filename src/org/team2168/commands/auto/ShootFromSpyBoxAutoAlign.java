package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.autoFire.AutoFireFar;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPause;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCamera;
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
public class ShootFromSpyBoxAutoAlign extends CommandGroup {
    
    public  ShootFromSpyBoxAutoAlign() {

    	
    	addParallel(new AutoFireFar());
    	addSequential(new IntakeExtend(), 3);
    	
    	
    	addSequential(new RotateXDistancePIDZZZCamera(0, 0.725, 0.22, 0.4), 1);
    	addSequential(new RotateXDistancePIDZZZCamera(0, 0.725, 0.22, 0.4), 1);
    	addSequential(new Sleep(), 6.7);
    	
    	//TODO: Add PID Setpoint Stop Command
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT), 1);
    	addParallel(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT), 1);
    	
    	addSequential(new Sleep(), 2);
    	
    	addParallel(new ShooterHoodRetract());
    	addParallel(new DrivePIDPause());
    	addParallel(new ShooterPIDPause());
    	addParallel(new DriveIndexerWithConstant(0));
    	addParallel(new IntakeWithConstant(0));
    	
    	
    }
}
