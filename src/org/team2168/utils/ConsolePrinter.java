package org.team2168.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.subsystems.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConsolePrinter {
	private java.util.Timer executor;
	private long period;

	PrintWriter log;

	public ConsolePrinter(long period) {
		this.period = period;
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new ConsolePrintTask(this), 0L, this.period);

		try {
			
			File file = new File("/home/lvuser/Logs");
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Log Directory is created!");
				} else {
					System.out.println("Failed to create Log directory!");
				}
			}
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
			this.log = new PrintWriter("/home/lvuser/Logs/" + dateFormat.format(date) + "-Log.txt", "UTF-8");
			log.println(FileHeading());
			log.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void print() {
		if (RobotMap.PRINT_SD_DEBUG_DATA) {
			
			
			SmartDashboard.putData("Autonomous Mode Chooser", Robot.autoChooser);
			SmartDashboard.putString("AutoName", Robot.getAutoName());
			SmartDashboard.putNumber("gameClock", (int)DriverStation.getInstance().getMatchTime());
			SmartDashboard.putBoolean("isPracticeBot", Robot.isPracticeRobot());
			
			
			SmartDashboard.putBoolean("Camera Status", Robot.drivetrain.tcpCamSensor.isCameraConnected());
			SmartDashboard.putBoolean("Bone Status", Robot.drivetrain.tcpCamSensor.isClientConnected());
			SmartDashboard.putBoolean("Processing Status", Robot.drivetrain.tcpCamSensor.isProcessingTreadRunning());
			SmartDashboard.putBoolean("MJPEG Status", Robot.drivetrain.tcpCamSensor.isMJPEGConnected());
			SmartDashboard.putNumber("Vision Target Dist", Robot.drivetrain.tcpCamSensor.getTargetDistance());
			SmartDashboard.putNumber("Vision Target Bearing", Robot.drivetrain.tcpCamSensor.getRotationAngle());
			
			SmartDashboard.putBoolean("isBallPresent", Robot.ballPresent);
			
			SmartDashboard.putBoolean("Is Target Detected", Robot.drivetrain.tcpCamSensor.isTargetDetected());
			SmartDashboard.putBoolean("Is Target Scorable", Robot.drivetrain.tcpCamSensor.isTargetScorable());
			
			SmartDashboard.putBoolean("Is Portcullus Down", Robot.portCullus.isPortCullusExtended());
			SmartDashboard.putBoolean("Is PortCullus Up", Robot.portCullus.isPortCulluseRetracted());
			
			SmartDashboard.putNumber("Left Encoder Distance",Robot.drivetrain.getLeftPosition());
			SmartDashboard.putNumber("Right Encoder Distance:",Robot.drivetrain.getRightPosition());

			SmartDashboard.putNumber("Gyro Angle:", Robot.drivetrain.getHeading());
			SmartDashboard.putNumber("Gyro Pitch", Robot.drivetrain.getPitchAngle());
			SmartDashboard.putNumber("Gyro Roll", Robot.drivetrain.getRollAngle());
			
			
			SmartDashboard.putNumber("GYRO Driftrate:", Robot.drivetrain.gyroSPI.driftRate);
			SmartDashboard.putNumber("GYRO Rate:", Robot.drivetrain.gyroSPI.getRate());
			SmartDashboard.putNumber("GYRO Angle SPI:", Robot.drivetrain.gyroSPI.getAngle());
			SmartDashboard.putNumber("GYRO reInits:", Robot.gyroReinits);
			SmartDashboard.putBoolean("Gyro Cal Status", !Robot.gyroCalibrating);
			SmartDashboard.putNumber("GYRO Status:", Robot.drivetrain.gyroSPI.getStatus());
			SmartDashboard.putNumber("GYRO Temp:", Robot.drivetrain.gyroSPI.getTemp());


			
			
			SmartDashboard.putNumber("Battery Voltage", Robot.pdp.getBatteryVoltage());
			SmartDashboard.putNumber("totalCurrent", Robot.pdp.getTotalCurrent());
			SmartDashboard.putNumber("pcmCurrent", Robot.pdp.getChannelCurrent(RobotMap.PCM_POWER));

			SmartDashboard.putNumber("Raw Shooter IR", Robot.shooter.getRawBoulderDistance());
			SmartDashboard.putNumber("Raw Indexer IR", Robot.indexer.getRawBoulderDistance());
			SmartDashboard.putNumber("Raw Intake IR", Robot.intakeRoller.getRawBoulderDistance());

			
			SmartDashboard.putNumber("DTRight1MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP));
			SmartDashboard.putNumber("DTRight2MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP));
			SmartDashboard.putNumber("DTRight3MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP));

			SmartDashboard.putNumber("DTLeft1MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP));
			SmartDashboard.putNumber("DTLeft2MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP));
			SmartDashboard.putNumber("DTLeft3MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP));
			
			SmartDashboard.putNumber("IntakeMotor1Current", Robot.pdp.getChannelCurrent(RobotMap.INTAKE_MOTOR_1_PDP));
			SmartDashboard.putNumber("IntakeMotor2Current", Robot.pdp.getChannelCurrent(RobotMap.INTAKE_MOTOR_2_PDP));

			SmartDashboard.putNumber("IndexerMotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.INDEXER_MOTOR_PDP));
			
			SmartDashboard.putNumber("ShooterMotor1Current", Robot.pdp.getChannelCurrent(RobotMap.SHOOTER_MOTOR_FWD_PDP));
			SmartDashboard.putNumber("ShooterMotor2Current", Robot.pdp.getChannelCurrent(RobotMap.SHOOTER_MOTOR_AFT_PDP));
			
			SmartDashboard.putNumber("DTLeft1MotorVoltage",Robot.drivetrain.getLeft1MotorVoltage());
					SmartDashboard.putNumber("DTLeft2MotorVoltage",Robot.drivetrain.getLeft2MotorVoltage());
							SmartDashboard.putNumber("DTLeft3MotorVoltage",Robot.drivetrain.getLeft3MotorVoltage());

SmartDashboard.putNumber("DTRight1MotorVoltage",Robot.drivetrain.getRight1MotorVoltage());
		SmartDashboard.putNumber("DTRight2MotorVoltage",Robot.drivetrain.getRight2MotorVoltage());
				SmartDashboard.putNumber("DTRight3MotorVoltage",Robot.drivetrain.getRight3MotorVoltage());


			SmartDashboard.putNumber("Left Stick Raw Value", Robot.oi.driverJoystick.getLeftStickRaw_Y());
			SmartDashboard.putNumber("Right Stick Raw Value", Robot.oi.driverJoystick.getRightStickRaw_Y());

			SmartDashboard.putNumber("Left Trigger Raw Value", Robot.oi.driverJoystick.getLeftTriggerAxisRaw());
			SmartDashboard.putNumber("Right Trigger Raw Value", Robot.oi.driverJoystick.getRightTriggerAxisRaw());

			SmartDashboard.putNumber("Operator Left Stick Raw Value", Robot.oi.operatorJoystick.getLeftStickRaw_Y());
			SmartDashboard.putNumber("Operator Right Stick Raw Value", Robot.oi.operatorJoystick.getRightStickRaw_Y());

			SmartDashboard.putNumber("Operator Left Trigger Raw Value", Robot.oi.operatorJoystick.getLeftTriggerAxisRaw());
			SmartDashboard.putNumber("Operator Right Trigger Raw Value", Robot.oi.operatorJoystick.getRightTriggerAxisRaw());

			//TODO: FIX PDP Class Current Monitoring functions
			SmartDashboard.putBoolean("Left Motor One Trip", !Robot.pdp.isLeftMotorOneTrip());
			SmartDashboard.putBoolean("Left Motor Two Trip", !Robot.pdp.isLeftMotorTwoTrip());
			SmartDashboard.putBoolean("Left Motor Three Trip", !Robot.pdp.isLeftMotorThreeTrip());

			SmartDashboard.putBoolean("Right Motor One Trip", !Robot.pdp.isRightMotorOneTrip());
			SmartDashboard.putBoolean("Right Motor Two Trip", !Robot.pdp.isRightMotorTwoTrip());
			SmartDashboard.putBoolean("Right Motor Three Trip", !Robot.pdp.isRightMotorThreeTrip());

			SmartDashboard.putBoolean("Shooter Fwd Motor Trip", !Robot.pdp.isShooterMotorOneTrip());
			SmartDashboard.putBoolean("Shooter Aft Motor Trip", !Robot.pdp.isShooterMotorTwoTrip());
			SmartDashboard.putBoolean("Intake Motor Trip", !Robot.pdp.isIntakeMotorTrip());
			SmartDashboard.putBoolean("Index Motor Trip", !Robot.pdp.isIndexMotorTrip());

			SmartDashboard.putBoolean("Intake Extended", Robot.intakePosition.isIntakeExtended());
			SmartDashboard.putBoolean("Intake Retracted", Robot.intakePosition.isIntakeRetracted());
			SmartDashboard.putBoolean("Shooter Hood FWD Extended", Robot.shooterPneumatics.isShooterFWDExtended());
			SmartDashboard.putBoolean("Shooter Hood FWD Retracted", Robot.shooterPneumatics.isShooterFWDRetracted());
			SmartDashboard.putBoolean("Shooter Hood AFT Extended", Robot.shooterPneumatics.isShooterAFTExtended());
			SmartDashboard.putBoolean("Shooter Hood AFT Retracted", Robot.shooterPneumatics.isShooterAFTRetracted());
			
			SmartDashboard.putBoolean("Shooter Close Shot Position", Robot.drivetrain.isCloseShot());
			
			//TODO: Make methods to return proper test values
//			SmartDashboard.putBoolean("Left Drive Motor 1 Pass", Robot.drivetrain.leftMotor1Pass);
//			SmartDashboard.putBoolean("Left Drive Motor 2 Pass", Robot.drivetrain.leftMotor2Pass);
//			SmartDashboard.putBoolean("Left Drive Motor 3 Pass", Robot.drivetrain.leftMotor3Pass);
//			SmartDashboard.putBoolean("Right Drive Motor 1 Pass", Robot.drivetrain.rightMotor1Pass);
//			SmartDashboard.putBoolean("Right Drive Motor 2 Pass", Robot.drivetrain.rightMotor2Pass);
//			SmartDashboard.putBoolean("Right Drive Motor 3 Pass", Robot.drivetrain.rightMotor3Pass);
//			SmartDashboard.putBoolean("Indexer Motor Pass", Indexer.indexerPass);
//			SmartDashboard.putBoolean("Intake Left Motor Pass", IntakeRoller.intakeLeftPass);
//			SmartDashboard.putBoolean("Intake Right Motor Pass", IntakeRoller.intakeRightPass);
//			SmartDashboard.putBoolean("Shooter FWD Motor Pass", Shooter.shooterFWDPass);
//			SmartDashboard.putBoolean("Shooter AFT Motor Pass", Shooter.shooterAFTPass);
//			

	       	SmartDashboard.putBoolean("Boulder in Shooter", Robot.shooter.isBoulderPresent());
	        SmartDashboard.putBoolean("Boulder in Indexer", Robot.indexer.isBoulderPresent());
	        SmartDashboard.putBoolean("Boulder in Intake", Robot.intakeRoller.isBoulderPresent());
	        SmartDashboard.putNumber("Shooter_rpm", Robot.shooter.getSpeed());
	        SmartDashboard.putBoolean("Shooter_atspeed_status", Robot.shooter.shooterSpeedController.isFinished());
	        SmartDashboard.putNumber("Shooter Position", Robot.shooter.getPosition());

	        SmartDashboard.putNumber("Boulder Distance Shooter", Robot.shooter.getAveragedRawBoulderDistance());
	        SmartDashboard.putNumber("Boulder Distance Indexer", Robot.indexer.getAveragedRawBoulderDistance());
	        SmartDashboard.putNumber("Boulder Distance Intake", Robot.intakeRoller.getAveragedRawBoulderDistance());
	        
	        SmartDashboard.putNumber("Robot Pressure", Robot.pneumatics.getPSI());
	        
	        SmartDashboard.putNumber("Hood Servo Angle", Robot.shooterhood.getAngle());
	        
	        
			
	        
	   
			
			//file log
			log.println(
					
					//Time
					Timer.getFPGATimestamp() + "\t" +
					System.currentTimeMillis() + "\t" +
					
					//Robot
					Robot.driverstation.isDisabled() + "\t" +
					Robot.driverstation.isEnabled() + "\t" +
					Robot.driverstation.isAutonomous() + "\t" +
					Robot.getAutoName() + "\t" + 
					Robot.driverstation.isOperatorControl() + "\t" +
					Robot.driverstation.isFMSAttached() + "\t" +
					Robot.driverstation.getMatchTime() + "\t" +
					Robot.driverstation.getBatteryVoltage() + "\t" +
					Robot.driverstation.isBrownedOut() + "\t" +  
					Robot.isPracticeRobot() + "\t" +  
					
					Robot.drivetrain.getHeading() + "\t" +
					Robot.drivetrain.getPitchAngle() + "\t" +
					Robot.drivetrain.getRollAngle() + "\t" +
					Robot.pneumatics.getPSI() + "\t" +
					
					
					//Drivetrain
					Robot.drivetrain.getLeft1MotorVoltage() + "\t" +
					Robot.drivetrain.getLeft2MotorVoltage() + "\t" +
					Robot.drivetrain.getLeft3MotorVoltage() + "\t" +

					Robot.drivetrain.getRight1MotorVoltage() + "\t" +
					Robot.drivetrain.getRight2MotorVoltage() + "\t" +
					Robot.drivetrain.getRight3MotorVoltage() + "\t" +

					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP) + "\t" +

					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP) + "\t" +


					Robot.drivetrain.drivetrainLeftEncoder.getPos() + "\t" +
					Robot.drivetrain.drivetrainLeftEncoder.getRate() + "\t" +

					Robot.drivetrain.drivetrainRightEncoder.getPos() + "\t" +
					Robot.drivetrain.drivetrainRightEncoder.getRate()  + "\t" +
					
					//Shooter
					Robot.shooter.getFWDMotorVoltage() + "\t" +
					Robot.shooter.getAFTMotorVoltage() + "\t" +
					
					Robot.pdp.getChannelCurrent(RobotMap.SHOOTER_MOTOR_FWD_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.SHOOTER_MOTOR_AFT_PDP) + "\t" +
					
					Robot.shooter.getSpeed() + "\t" +
					
					//Hood
					Robot.shooterhood.getAngle()

					);
				
			log.flush();


		}
	}
	
	private String FileHeading()
	{
		return "Time \t TimeofDay \t Disabled \t Enabled \t Auto \t AutoName \t Teleop \t FMS \t MatchTime \t Batt Volt \t isBrownOut\t isPBot \t "
				+ "Gyro Angle \t Gyro Pitch \t Gyro Roll \t Pneumatics PSI \t"
				+ "VoltageL1 \t VoltageL2 \t VoltageL3 \t VoltageR1 \t VoltageR2 \t VoltageR3 \t CurrentL1 \t CurrentL2 \t CurrentL3 \t CurrentR1 \t CurrentR2 \t CurrentR3 \t " 
				+ "Left Encoder Position \t Left Encoder Rate \t Right Encoder Position \t Right Encoder Rate \t "
				+ "Shooter FWD Voltage \t Shooter AFT Voltage \t Shooter FWD Current \t Shooter AFT Current \t Shooter Rate \t Hood Servo Angle";
	}

	private class ConsolePrintTask extends TimerTask {
		private ConsolePrinter console;

		private ConsolePrintTask(ConsolePrinter printer) {
			if (printer == null) {
				throw new NullPointerException("printer was null");
			}
			this.console = printer;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			console.print();
		}
	}
}

