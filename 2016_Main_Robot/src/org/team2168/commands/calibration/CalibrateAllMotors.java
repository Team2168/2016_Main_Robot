package org.team2168.commands.calibration;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs through all motors on the robot forwards and backwards, printing
 * out the distance they turned to the SmartDashboard
 * @author Ben Waid
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
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_FWD, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_FWD, REVERSE));
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_AFT, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.SHOOTER_WHEEL_AFT, REVERSE));
    	
    	//Intake calibration
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_LEFT, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_LEFT, REVERSE));
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_RIGHT, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.INTAKE_WHEEL_RIGHT, REVERSE));
    	
    	//Index calibration
//    	addSequential(new CalibrateMotor(RobotMap.INDEX_WHEEL, FORWARD));
//    	addSequential(new CalibrateMotor(RobotMap.INDEX_WHEEL, REVERSE));
    	
    }
}