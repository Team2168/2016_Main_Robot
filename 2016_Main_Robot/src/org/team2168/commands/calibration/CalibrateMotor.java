package org.team2168.commands.calibration;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs a motor forward and backwards and prints results to the SmartDashboard
 * for controls testing
 * @author Ben Waid
 */
public class CalibrateMotor extends Command {

	private double motorSpeed;
	
	private double runTime = 3.0;
	
	private double direction = 1.0;
	
	private double startPosition;
	private double startTime;
	
	private int motorNumber;
	
	private String status;
	
	//TODO find change is position desired
	private double minPositionChange = 1.0;
	
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
    		//TODO set this to the speed we want
    		motorSpeed = 1.0;
    		this.motorNumber = motorPWMPort;
    	}
    	
//    	if(motorPWMPort == RobotMap.SHOOTER_WHEEL_FWD ||
//    	   motorPWMPort == RobotMap.SHOOTER_WHEEL_AFT) {
//    		requires(Robot.shooter);
//    		//TODO set this to the speed we want
//			motorSpeed = 1.0;
//    		this.motorNumber = motorPWMPort;
//    	}
//    	
//    	if(motorPWMPort == RobotMap.INTAKE_WHEEL_LEFT ||
//    	   motorPWMPort == RobotMap.INTAKE_WHEEL_RIGHT) {
//    		requires(Robot.intake)
//			//TODO set this to the speed we want
//    		motorSpeed = 1.0;
//    		this.motorNumber = motorPWMPort;
//    	}
//    	
//    	if(motorPWMPort == RobotMap.INDEX_WHEEL) {
//    		requires(Robot.index);
//    		//TODO set this to the speed we want
//    		motorSpeed = 1.0;
//    		this.motorNumber = motorPWMPort;
//    	}
    	
    	if(!forward) {
    		direction = -direction;
    	}
    	
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
//    		case RobotMap.SHOOTER_WHEEL_LEFT:
//    		case RobotMap.SHOOTER_WHEEL_AFT:
//    			motorPosition = Robot.shooter.getPosition();
//				break;
//    		case RobotMap.INTAKE_WHEEL_RIGHT:
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
    
    /**
     * When the command is started, the position of the motor and
     * the current time are determined  
     */
    protected void initialize() {
    	startPosition = getPosition();
    	startTime = Timer.getFPGATimestamp();
    }

    /**
     * Runs the motor selected at the given speed
     * @param double speed from 1 to -1
     */
    protected void setSpeed(double speed) {
    	switch(motorNumber) {
    		case RobotMap.LEFT_DRIVE_TRAIN_1:
    			 Robot.drivetrain.left1Drive(speed);
    			 break;
    		case RobotMap.LEFT_DRIVE_TRAIN_2:
    			 Robot.drivetrain.left1Drive(speed);
    			 break;
    		case RobotMap.LEFT_DRIVE_TRAIN_3:
    			 Robot.drivetrain.left1Drive(speed);
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_1:
   			 	 Robot.drivetrain.right1Drive(speed);
   			 	 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_2:
    			 Robot.drivetrain.right1Drive(speed);
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_3:
    			 Robot.drivetrain.right1Drive(speed);
    			 break;
//    		case RobotMap.SHOOTER_WHEEL_FWD:
//   			 Robot.shooter.driveShooter1(speed);
//   			 break;
//   		case RobotMap.SHOOTER_WHEEL_AFT:
//   			 Robot.shooter.driveShooter2(speed);
//				 break;
//   		case RobotMap.INTAKE_WHEEL_LEFT:
//   			 Robot.intake.driveIntake1(speed);
//   			 break;
//   		case RobotMap.INTAKE_WHEEL_RIGHT:
//   			 Robot.intake.driveIntake2(speed);
//				 break;
//   		case RobotMap.INDEX_WHEEL:
//   			 Robot.index.driveIndex(speed);
//	    		 break;
    		default:
    			 break;
    	}
    }
    
    /**
     * Runs the motor at the appropriate speed in the direction given
     */
    protected void execute() {
    	setSpeed(motorSpeed * direction);
    }

    /**
     * Returns true when the motor has run for the alloted time
     */
    protected boolean isFinished() {
    	return (Timer.getFPGATimestamp() - startTime) > runTime;
    }

    /**
     * Determines the distance the motor turned and prints to the
     * SmartDashboard how far it turned after isFinished returns true
     */
    protected void end() {
    	
    	String motorName = null;
    	
    	double positionChange;
    	
    	positionChange = getPosition() - startPosition;
    	if(positionChange >= minPositionChange) {
    		status = "Pass";
    	}
    	
    	if(positionChange < minPositionChange) {
    		status = "Fail";
    	}
    	
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
//    		case RobotMap.SHOOTER_WHEEL_FWD:
//    			 motorName = "Shooter Wheel 1";
//    			 break;
//    		case RobotMap.SHOOTER_WHEEL_AFT:
//    			 motorName = "Shooter Wheel 2";
//				 break;
//    		case RobotMap.INTAKE_WHEEL_LEFT:
//    			 motorName = "Intake Wheel 1";
//    			 break;
//    		case RobotMap.INTAKE_WHEEL_RIGHT:
//    			 motorName = "Intake Wheel 2";
//				break;
//    		case RobotMap.INDEX_WHEEL:
//    			 motorName = "Index Wheel";
//	    		break;
    		default:
    			break;
    	}
    	
    	//TODO Change to SmartDashboard output
    	System.out.println(motorName + "finished with a " + status);
    	
    }

    /**
     * Stops the motor if the command is interrupted
     */
    protected void interrupted() {
    	setSpeed(0.0);
    }
    
}
