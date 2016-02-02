package org.team2168.commands.calibration;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs through all motors on the robot forwards and backwards, printing
 * out the distance they turned to the SmartDashboard
 */
public class CalibrateAllMotors extends CommandGroup {
	private final static boolean FORWARD = true;
	private final static boolean REVERSE = false;
    
    public  CalibrateAllMotors() {
    	
    	//Drivetrain calibration
    	addSequential(new CalibrateMotor(RobotMap.LEFT_DRIVE_TRAIN_1, FORWARD));
    	addSequential(new CalibrateMotor(RobotMap.LEFT_DRIVE_TRAIN_1, REVERSE));
    	addSequential(new CalibrateMotor(RobotMap.LEFT_DRIVE_TRAIN_2, FORWARD));
    	addSequential(new CalibrateMotor(RobotMap.LEFT_DRIVE_TRAIN_2, REVERSE));
    	addSequential(new CalibrateMotor(RobotMap.LEFT_DRIVE_TRAIN_3, FORWARD));
    	addSequential(new CalibrateMotor(RobotMap.LEFT_DRIVE_TRAIN_3, REVERSE));
    	addSequential(new CalibrateMotor(RobotMap.RIGHT_DRIVE_TRAIN_1, FORWARD));
    	addSequential(new CalibrateMotor(RobotMap.RIGHT_DRIVE_TRAIN_1, REVERSE));
    	addSequential(new CalibrateMotor(RobotMap.RIGHT_DRIVE_TRAIN_2, FORWARD));
    	addSequential(new CalibrateMotor(RobotMap.RIGHT_DRIVE_TRAIN_2, REVERSE));
    	addSequential(new CalibrateMotor(RobotMap.RIGHT_DRIVE_TRAIN_3, FORWARD));
    	addSequential(new CalibrateMotor(RobotMap.RIGHT_DRIVE_TRAIN_3, REVERSE));
    	
    	//Shooter calibration
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_1, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_1, REVERSE));
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_2, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_2, REVERSE));
    	
    	//Intake calibration
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_1, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_1, REVERSE));
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_2, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_2, REVERSE));
    	
    	//Index calibration
//    	addSequential(new CalibrateMotor(RobotMap.INDEX_WHEEL, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.INDEX_WHEEL, REVERSE));
    	
    }
}
