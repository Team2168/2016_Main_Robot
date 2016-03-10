package org.team2168.commands.calibration;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Indexer;
import org.team2168.subsystems.IntakeRoller;
import org.team2168.subsystems.Shooter;

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
    		motorSpeed = 0.2;
    		this.motorNumber = motorPWMPort;
    	}
    	
    	if(motorPWMPort == RobotMap.SHOOTER_WHEEL_FWD ||
    	   motorPWMPort == RobotMap.SHOOTER_WHEEL_AFT) {
    		requires(Robot.shooter);
    		//TODO set this to the speed we want
			motorSpeed = 0.2;
    		this.motorNumber = motorPWMPort;
    	}
    	
    	if(motorPWMPort == RobotMap.INTAKE_WHEEL_1 ||
    	   motorPWMPort == RobotMap.INTAKE_WHEEL_2) {
    		requires(Robot.intakeRoller);
			//TODO set this to the speed we want
    		motorSpeed = 0.2;
    		this.motorNumber = motorPWMPort;
    	}
    	
    	if(motorPWMPort == RobotMap.INDEXER_WHEEL) {
    		requires(Robot.indexer);
    		//TODO set this to the speed we want
    		motorSpeed = 0.2;
    		this.motorNumber = motorPWMPort;
    	}
    	
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

    	//Subsystems without encoders are set to to 0.0
    	switch(motorNumber) {
    		case RobotMap.LEFT_DRIVE_TRAIN_1:
    		case RobotMap.LEFT_DRIVE_TRAIN_2:
    		case RobotMap.LEFT_DRIVE_TRAIN_3:
    		 	motorPosition = Robot.drivetrain.getLeftPosition();
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_1:
    		case RobotMap.RIGHT_DRIVE_TRAIN_2:
    		case RobotMap.RIGHT_DRIVE_TRAIN_3:
    			motorPosition = Robot.drivetrain.getRightPosition();
    			break;
    		case RobotMap.SHOOTER_WHEEL_FWD:
    		case RobotMap.SHOOTER_WHEEL_AFT:
    			motorPosition = Robot.shooter.getPosition();
				break;
    		case RobotMap.INTAKE_WHEEL_1:
    		case RobotMap.INTAKE_WHEEL_2:
    			motorPosition = 0.0;
				break;
    		case RobotMap.INDEXER_WHEEL:
    			motorPosition = 0.0;
	    		break;
    		default:
    			break;
    	}
    	
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
    			 Robot.drivetrain.setLeftMotor1(speed);
    			 break;
    		case RobotMap.LEFT_DRIVE_TRAIN_2:
    			 Robot.drivetrain.setLeftMotor2(speed);
    			 break;
    		case RobotMap.LEFT_DRIVE_TRAIN_3:
    			 Robot.drivetrain.setLeftMotor3(speed);
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_1:
   			 	 Robot.drivetrain.setRightMotor1(speed);
   			 	 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_2:
    			 Robot.drivetrain.setRightMotor2(speed);
    			 break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_3:
    			 Robot.drivetrain.setRightMotor3(speed);
    			 break;
    		case RobotMap.SHOOTER_WHEEL_FWD:
    			 Robot.shooter.driveFWDShooterWheel(speed);
    			 break;
    		case RobotMap.SHOOTER_WHEEL_AFT:
    			 Robot.shooter.driveAFTShooterWheel(speed);
				 break;
    		case RobotMap.INTAKE_WHEEL_1:
    			 Robot.intakeRoller.driveIntakeWheel1(speed);
    			 break;
    		case RobotMap.INTAKE_WHEEL_2:
    			 Robot.intakeRoller.driveIntakeWheel2(speed);
				 break;
    		case RobotMap.INDEXER_WHEEL:
    			 Robot.indexer.setSpeed(speed);
	    		 break;
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
    	
    	boolean pass;
    	double positionChange;
    	
    	positionChange = getPosition() - startPosition;
    	
    	switch(motorNumber){
    		case RobotMap.LEFT_DRIVE_TRAIN_1:
    		    pass = (positionChange >= minPositionChange);
    		    Drivetrain.leftMotor1Pass = pass;
    			break;
    		case RobotMap.LEFT_DRIVE_TRAIN_2:
    			pass = (positionChange >= minPositionChange);
    			Drivetrain.leftMotor2Pass = pass;
    			break;
    		case RobotMap.LEFT_DRIVE_TRAIN_3:
    			pass = (positionChange >= minPositionChange);
    			Drivetrain.leftMotor3Pass = pass;
    			break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_1:
    			pass = (positionChange >= minPositionChange);
    			Drivetrain.rightMotor1Pass = pass;
    			break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_2:
    			pass = (positionChange >= minPositionChange);
    			Drivetrain.rightMotor2Pass = pass;
    			break;
    		case RobotMap.RIGHT_DRIVE_TRAIN_3:
    			pass = (positionChange >= minPositionChange);
    			Drivetrain.rightMotor3Pass = pass;
    			break;
    		case RobotMap.SHOOTER_WHEEL_FWD:
    			pass = (positionChange >= minPositionChange);
    			Shooter.shooterFWDPass = pass;
    			break;
    		case RobotMap.SHOOTER_WHEEL_AFT:
    			pass = (positionChange >= minPositionChange);
    			Shooter.shooterAFTPass = pass;
				break;
    		case RobotMap.INTAKE_WHEEL_1:
    			pass = (positionChange >= minPositionChange);
    			IntakeRoller.intakeLeftPass = pass;
    			break;
    		case RobotMap.INTAKE_WHEEL_2:
    			pass = (positionChange >= minPositionChange);
    			IntakeRoller.intakeRightPass = pass;
				break;
    		case RobotMap.INDEXER_WHEEL:
    			pass = (positionChange >= minPositionChange);
    			Indexer.indexerPass = pass;
	    		break;
    		default:
    			break;
    	}
    	
    }

    /**
     * Stops the motor if the command is interrupted
     */
    protected void interrupted() {
    	setSpeed(0.0);
    }
    
}
