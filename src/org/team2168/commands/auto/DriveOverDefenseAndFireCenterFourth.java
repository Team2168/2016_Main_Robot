package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPause;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCamera;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooter.PIDCommands.WaitForShooterPIDToFinish;
import org.team2168.commands.shooterPneumatics.ShooterHoodCloseShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodFarShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Drives Robot over defense
 */
public class DriveOverDefenseAndFireCenterFourth extends CommandGroup {
    
    public  DriveOverDefenseAndFireCenterFourth() {

    	//stow hood, lower intake and spin up wheel
    	addParallel(new ShooterHoodFarShotPosition());
    	addSequential(new IntakeExtend(), 3);
    	//addParallel(new DriveShooterPIDSpeed(6700));
    	
    	
    	//Drive over defense
    	addSequential(new DriveXDistance(17.5, 0.8, 0.5));
    	addSequential(new DriveXDistance(-1.5, 0.8, 0.5));
    	
    	//Put up hood and DO NOT Rotate)
    	addParallel(new DriveShooterPIDSpeed(6500));
    	
    	addSequential(new Sleep(), 1.3); // camera lag
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, 0.42, 0.25, 0.5));

    	addSequential(new WaitForShooterPIDToFinish());
    	
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
