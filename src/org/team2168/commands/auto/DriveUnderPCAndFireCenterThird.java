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
 * Drives Robot under the PC from the third defensive position
 */
public class DriveUnderPCAndFireCenterThird extends CommandGroup {
    
    public  DriveUnderPCAndFireCenterThird() {

    	addSequential(new DriveUnderPCAuto());
    	addSequential(new RotateXDistancePIDZZZ(-180 + 13, 0.8, 0.25, 1));
    	
    	//THE REST IS THE SAME AS THE VANILLA CenterThird AUTO
    	
    	addParallel(new DriveShooterPIDSpeed(6500));
    	
    	addSequential(new Sleep(), 0.7); // camera lag
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));

    	//TYBG
    	//TTWR
    	//DON'T SLOB THE KNOB
    	//WIN THE CONTROLS AWARD
    	//DON'T FUCK UP
    	//BE GOOD
    	//STARBUX IS LIFE
    
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
