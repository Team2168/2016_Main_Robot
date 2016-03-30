package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveWithConstant;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZCamera;
import org.team2168.commands.intakeposition.IntakeExtend;
import org.team2168.commands.shooter.PIDCommands.DriveShooterPIDSpeed;
import org.team2168.commands.shooterPneumatics.ShooterHoodExtend;
import org.team2168.commands.shooterPneumatics.ShooterHoodRetract;
import org.team2168.commands.shooterhood.DriveShooterHoodToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Drives Robot over defense
 */
public class DriveOverLowGoalAndFire extends CommandGroup {
    
    public  DriveOverLowGoalAndFire() {

    	addParallel(new ShooterHoodExtend());
    	//addSequential(new IntakeExtend(), 3);

    	addParallel(new DriveShooterPIDSpeed(6300));
    	addSequential(new DriveXDistance(8.5, 0.5, 0.1));
    	addSequential(new Sleep(),2);
    	addSequential(new RotateXDistancePIDZZZ(57, 0.7, 0.25, 1));
    	
    	addSequential(new Sleep(),5); // camera lag
    	addSequential(new RotateXDistancePIDZZZCamera(0, 0.725, 0.35, 0.4));
    	addSequential(new Sleep(),2); // camera lag
    	//addSequential(new RotateXDistancePIDZZZCamera(0, 0.725, 0.35, 0.4));
    	
    	
    	
    }
}
