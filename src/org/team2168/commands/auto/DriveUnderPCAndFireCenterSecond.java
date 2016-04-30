package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPause;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCameraWithGyro;
import org.team2168.commands.indexer.DriveIndexerWithConstant;
import org.team2168.commands.intakeroller.IntakeWithConstant;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooter.PIDCommands.WaitForShooterPIDToFinish;
import org.team2168.commands.shooterPneumatics.ShooterHoodFarShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Drives Robot under the pc from the 2nd defense position
 */
public class DriveUnderPCAndFireCenterSecond extends CommandGroup {
    
    public  DriveUnderPCAndFireCenterSecond() {
    	addSequential(new DriveUnderPCAuto());
    	addSequential(new RotateXDistancePIDZZZ(-180 + 30, 0.8, 0.25, 1));
    	
    	//THE REST IS THE SAME AS THE VANILLA CenterSecond AUTO
    	
    	//Put up hood and rotate
    	addParallel(new ShooterHoodFarShotPosition());
    	addParallel(new DriveShooterPIDSpeed(6500));
    	
    	addSequential(new Sleep(), 0.7); // camera lag
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
//    	addSequential(new Sleep(),2);
    	
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
