package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAlignV2 extends CommandGroup {
    
    public  DriveAlignV2() {
    	
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
    	addSequential(new DriveXDistancePIDZZZCameraWithGyroV2(100, 0.4));
//    	addSequential(new DriveXDistancePIDZZZCameraWithGyro(0.33));
    	addSequential(new RotateXDistancePIDZZZCameraWithGyro(0, RobotMap.ROTATE_POSITION_CAMERA_MAX, RobotMap.ROTATE_POSITION_CAMERA_MIN, 0.5));
    }
}
