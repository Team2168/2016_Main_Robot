package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAlign extends CommandGroup {
    
    public  DriveAlign() {
    	
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
    	//addSequential(new DriveXDistancePIDZZZCameraWithGyroV2(10, 0.7));
    	addSequential(new DriveXDistancePIDZZZCameraWithGyro(0.27));
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
    }
}
