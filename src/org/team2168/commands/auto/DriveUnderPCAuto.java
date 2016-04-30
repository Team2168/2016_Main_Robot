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
import org.team2168.commands.portCullus.PCManipulatorExtend;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooter.PIDCommands.ShooterPIDPause;
import org.team2168.commands.shooter.PIDCommands.WaitForShooterPIDToFinish;
import org.team2168.commands.shooterPneumatics.ShooterHoodCloseShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodFarShotPosition;
import org.team2168.commands.shooterPneumatics.ShooterHoodStowPosition;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Drives Robot under the Portcullis
 * 
 * In this auto mode, the robot starts with the intake facing away from the defenses.
 * The PC manipulator is starting facing the PC
 */
public class DriveUnderPCAuto extends CommandGroup {
    
    public  DriveUnderPCAuto() {
    	//Lower everything
    	addParallel(new PCManipulatorExtend());
    	addParallel(new ShooterHoodStowPosition());
    	addSequential(new IntakeExtend());  //no timeout, if it  doesn't lower, don't proceed
    	
    	//Drive under the PC backwards
    	addSequential(new DriveXDistance(-17.5, 0.5, 0.5));
    	addSequential(new DriveXDistance(+1.5, 0.5, 0.5));
    }
}
