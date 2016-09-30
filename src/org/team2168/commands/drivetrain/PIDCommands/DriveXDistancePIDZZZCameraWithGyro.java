
package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Vittorio
 */
public class DriveXDistancePIDZZZCameraWithGyro extends Command {

	private double setPoint;
	private double maxSpeed;
	private double minSpeed;
	private double error = 0.5;  // Rotational degree error, default 0 never ends.
	private boolean absolute = false;
	private double driveSpeed;
	
    public DriveXDistancePIDZZZCameraWithGyro() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	this.setPoint = Robot.drivetrain.driveTrainPosController.getSetPoint();
    	this.maxSpeed = 1;
    	this.minSpeed = 0;
    }

//    public DriveXDistancePIDZZZCameraWithGyro(double setPoint){
// 	   this();
// 	   this.setPoint = setPoint;
//    }
    
    public DriveXDistancePIDZZZCameraWithGyro(double driveSpeed){
  	   this();
  	   this.driveSpeed = driveSpeed;
     }

    public DriveXDistancePIDZZZCameraWithGyro(double setPoint, double maxSpeed){
  	   this(setPoint);
  	   this.maxSpeed = maxSpeed;
     }
    
    public DriveXDistancePIDZZZCameraWithGyro(double setPoint, double maxSpeed, double minSpeed){
   	   this(setPoint, maxSpeed);
   	   this.minSpeed = minSpeed;
      }    

    public DriveXDistancePIDZZZCameraWithGyro(double setPoint, double maxSpeed, double minSpeed, double error) {
    	this(setPoint, maxSpeed, minSpeed);
    	this.error = error;
    }
    
    public DriveXDistancePIDZZZCameraWithGyro(double setPoint, double maxSpeed, double minSpeed, double error, boolean absolute) {
    	this(setPoint, maxSpeed, minSpeed, error);
    	this.absolute = absolute;
    }
    // Called just before this Command runs the first time
    
	protected void initialize() {
//		if (!absolute)
//			this.setPoint = Robot.drivetrain.getHeading() - Robot.drivetrain.tcpCamSensor.getRotationAngle() + RobotMap.CAMERA_OFFSET_ANGLE;
//		Robot.drivetrain.driveTrainPosController.reset();
//		Robot.drivetrain.driveTrainPosController.setSetPoint(setPoint);
//		Robot.drivetrain.driveTrainPosController.setMaxPosOutput(maxSpeed);
//		Robot.drivetrain.driveTrainPosController.setMaxNegOutput(-maxSpeed);
//		Robot.drivetrain.driveTrainPosController.setMinPosOutput(minSpeed);
//		Robot.drivetrain.driveTrainPosController.setMinNegOutput(-minSpeed);
//		//Robot.drivetrain.driveTrainPosController.setAcceptErrorDiff(error);
//		//Robot.drivetrain.gyroSPI.reset();
//		Robot.drivetrain.driveTrainPosController.Enable();
		
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		
		Robot.flashlight.disableFlashlight();
		//keep robot moving until we are at center
//		Robot.drivetrain.driveTrainPosController.setSetPoint(Robot.drivetrain.getHeading() - Robot.drivetrain.tcpCamSensor.getRotationAngle());
//		Robot.drivetrain.tankDrive(Robot.drivetrain.driveTrainPosController.getControlOutput(),-Robot.drivetrain.driveTrainPosController.getControlOutput());
		
		if(Robot.drivetrain.tcpCamSensor.getVerticalAngle() > 3){
			Robot.drivetrain.tankDrive(-driveSpeed, -driveSpeed);
		}
		else if(Robot.drivetrain.tcpCamSensor.getVerticalAngle() < -0.5){
			Robot.drivetrain.tankDrive(driveSpeed, driveSpeed);
		}
		else{
			//do nothing
		}
		
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
		//TODO Should the command be stopped????????!?!?!?!?!? after PID is tuned
//    	if( Robot.drivetrain.driveTrainPosController.isFinished())
//    	{
//    		setPoint = Robot.drivetrain.getHeading() - Robot.tcpCamSensor.getRotationAngle();
//    		Robot.drivetrain.driveTrainPosController.setSetPoint(setPoint);
//    	}
//    	
//		
		
//		return Robot.drivetrain.driveTrainPosController.isFinished() || Math.abs(Robot.drivetrain.tcpCamSensor.getPos()) < error;
		return (Robot.drivetrain.tcpCamSensor.getVerticalAngle() > -0.5 && Robot.drivetrain.tcpCamSensor.getVerticalAngle() < 3);
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.flashlight.enableFlashlight();
		Robot.drivetrain.driveTrainPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    	//    /\
    	//   /  \
    	//  /([])\
    	// /______\
    	//ILLUMINATI COMFIRMED
    }
}