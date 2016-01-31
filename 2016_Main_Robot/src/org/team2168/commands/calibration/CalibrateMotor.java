package org.team2168.commands.calibration;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs a motor forward and backwards and prints results to the SmartDashboard
 * for controls testing
 * 
 */
public class CalibrateMotor extends Command {

	private double startPosition;
	
	private int motorNumber;
	
	/**
	 * This constructor takes in the port number of any motor on the
	 * Robot and whether to run it forward or backward, allowing us to
	 * test any motor needed
	 * @param int motorPWMPort
	 * @param boolean forward
	 */
    public CalibrateMotor(int motorPWMPort, boolean forward) {
    	if(motorPWMPort == RobotMap.LEFT_DRIVE_TRAIN_1 ||
    	   motorPWMPort == RobotMap.LEFT_DRIVE_TRAIN_2 ||
    	   motorPWMPort == RobotMap.LEFT_DRIVE_TRAIN_3 ||
    	   motorPWMPort == RobotMap.RIGHT_DRIVE_TRAIN_1 ||
    	   motorPWMPort == RobotMap.RIGHT_DRIVE_TRAIN_2 ||
    	   motorPWMPort == RobotMap.RIGHT_DRIVE_TRAIN_3) {
    		requires(Robot.drivetrain);
    		this.motorNumber = motorPWMPort;
    	}
    	
//    	if(motorPWMPort == RobotMap.SHOOTER_WHEEL_1 ||
//    	   motorPWMPort == RobotMap.SHOOTER_WHEEL_2) {
//    		requires(Robot.shooter);
//    		this.motorNumber = motorPWMPort;
//    	}
//    	
//    	if(motorPWMPort == RobotMap.INTAKE_WHEEL_1 ||
//    	   motorPWMPort == RobotMap.INTAKE_WHEEL_2) {
//    		requires(Robot.intake)
//    		this.motorNumber = motorPWMPort;
//    	}
//    	
//    	if(motorPWMPort == RobotMap.INDEX_WHEEL) {
//    		requires(Robot.index);
//    		this.motorNumber = motorPWMPort;
//    	}
    	
    }

    /**
     * Returns the current position of the motor
     * @return double
     */
    private double getPosition(){
    	double motorPosition = 0.0;
    	
    	//TODO Change returnValue to 0.0 for subsystems without encoders
//    	switch(motorNumber) {
//    		case RobotMap.LEFT_DRIVE_TRAIN_1:
//    		case RobotMap.LEFT_DRIVE_TRAIN_2:
//    		case RobotMap.LEFT_DRIVE_TRAIN_3:
//    		 	motorPosition = Robot.drivetrain.getLeftPosition();
//    			 break;
//    		case RobotMap.RIGHT_DRIVE_TRAIN_1:
//    		case RobotMap.RIGHT_DRIVE_TRAIN_2:
//    		case RobotMap.RIGHT_DRIVE_TRAIN_3:
//    			motorPosition = Robot.drivetrain.getRightPosition();
//    			break;
//    		case RobotMap.SHOOTER_WHEEL_1:
//    		case RobotMap.SHOOTER_WHEEL_2:
//    			motorPosition = Robot.shooter.getPosition();
//				break;
//    		case RobotMap.INTAKE_WHEEL_1:
//    		case RobotMap.INTAKE_WHEEL2:
//    			motorPosition = Robot.intake.getPosition();
//				break;
//    		case RobotMap.INDEX_WHEEL:
//    			motorPosition = Robot.index.getPosition();
//	    		break;
//    		default:
//    			break;
//    	}
    	
    	return motorPosition;
    }
    
    protected void initialize() {
    	startPosition = getPosition();
    }

    
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	String motorName = null;
    	
    	double positionChange;
    	
    	positionChange = getPosition() - startPosition;
    	
    	switch(motorNumber){
    		case RobotMap.LEFT_DRIVE_TRAIN_1:
    			 motorName = "Left Drivetrain 1";
    			 break;
    		case RobotMap.LEFT_DRIVE_TRAIN_2:
    			 motorName = "Left Drivetrain 2";
    			 break;
    		case RobotMap.LEFT_DRIVE_TRAIN_3:
    			 motorName = "Left Drivetrain 3";
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_1:
    			 motorName = "Right Drivetrain 1";
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_2:
    			 motorName = "Right Drivetrain 2";
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_3:
    			 motorName = "Right Drivetrain 3";
    			 break;
//    		case RobotMap.SHOOTER_WHEEL_1:
//    			 motorName = "Shooter Wheel 1";
//    			 break;
//    		case RobotMap.SHOOTER_WHEEL_2:
//    			 motorName = "Shooter Wheel 2";
//				 break;
//    		case RobotMap.INTAKE_WHEEL_1:
//    			 motorName = "Intake Wheel 1";
//    			 break;
//    		case RobotMap.INTAKE_WHEEL2:
//    			 motorName = "Intake Wheel 2";
//				break;
//    		case RobotMap.INDEX_WHEEL:
//    			 motorName = "Index Wheel";
//	    		break;
    		default:
    			break;
    	}
    	
    	//TODO Change to SmartDashboard output
    	System.out.println(motorName + "motor moved a distance of" + positionChange);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
