package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPause;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooterPneumatics.ShooterHoodCloseShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodFarShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Drives Robot over defense
 */
public class DriveOverDefenseAndRotateFromDifferentPosition extends CommandGroup {
    
    public  DriveOverDefenseAndRotateFromDifferentPosition(double absolutePosition) {

    	//stow hood, lower intake and spin up wheel
    	addParallel(new ShooterHoodFarShotPosition());
    	addSequential(new IntakeExtend(), 3);
    	addParallel(new DriveShooterPIDSpeed(6700));
    	
    	
    	//Drive over defense
    	addSequential(new DriveXDistance(15, 0.75, 0.1));
    	addSequential(new DriveXDistance(-1.5, 0.5, 0.1));
    	
    	//Put up hood and rotate
    	addSequential(new RotateXDistancePIDZZZ(absolutePosition, 0.5, 0.25, 1, true));
    	
    	addSequential(new Sleep(),2); // camera lag
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, 0.4, 0.22, 0.1));
    	addSequential(new Sleep(),2);
    	
    	
//    	//Fire for 3 seconds
    	addParallel(new DriveIndexerWithConstant(RobotMap.INDEXER_SPEED_CONSTANT_SHOOT), 1);
    	addParallel(new IntakeWithConstant(RobotMap.INTAKE_SPEED_CONSTANT_SHOOT), 1);
    	addSequential(new Sleep(), 3);
    	
//    	//Clean Up Robot
    	addParallel(new ShooterHoodStowPosition());
    	addParallel(new ShooterPIDPause());
    	addParallel(new DrivePIDPause());
    	addParallel(new DriveIndexerWithConstant(0));
    	addParallel(new IntakeWithConstant(0));
    	
    	
    	
    }
}
