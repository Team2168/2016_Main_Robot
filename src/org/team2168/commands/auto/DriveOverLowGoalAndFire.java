package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooterPneumatics.ShooterHoodRetract;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Drives Robot over defense
 */
public class DriveOverLowGoalAndFire extends CommandGroup {
    
    public  DriveOverLowGoalAndFire() {

    	addParallel(new ShooterHoodRetract());
    	addSequential(new IntakeExtend(), 3);

    	addParallel(new DriveShooterPIDSpeed(6300));
    	addSequential(new DriveXDistance(16, 0.6),10);
    	
    	addSequential(new RotateXDistancePIDZZZ(30));
    	
    	
    }
}
