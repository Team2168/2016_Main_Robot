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
import org.team2168.PID.sensors.IMU;
import org.team2168.subsystems.Drivetrain;

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
			log.println("Time \t TimeofDay \t Disabled \t Enabled \t Auto \t AutoName \t Teleop \t FMS \t MatchTime \t Batt Volt \t isBrownOut\t VoltageL1 \t VoltageL2 \t VoltageL3 \t VoltageR1 \t VoltageR2 \t VoltageR3 \t CurrentL1 \t CurrentL2 \t CurrentL3 \t CurrentR1 \t CurrentR2 \t CurrentR3 \t Gyrot SPI Gyro Angle \t SPI Gyro Rate \t Left Encoder Position \t Left Encoder Rate \t Right Encoder Position \t Right Encoder Rate \t Lift Voltage \t Lift Left Current \t Lift Right Current \t Lift Position \t Lift Raw Rate \t Lift Rate \t Accel X \t Accel Y \t Accel Z \t Accel Pitch \t Accel Roll \t Lift Stall IR \t Raw RC Distance");
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
//			SmartDashboard.putData("Autonomous Mode Chooser", Robot.autoChooser);
			SmartDashboard.putNumber("gameClock", (int)DriverStation.getInstance().getMatchTime());
			
			SmartDashboard.putNumber("Left Encoder Distance",Drivetrain.getInstance().getLeftPosition());
			SmartDashboard.putNumber("Right Encoder Distance:",Drivetrain.getInstance().getRightPosition());
//			SmartDashboard.putBoolean("isPracticeBot", Robot.isPracticeRobot());

//			SmartDashboard.putNumber("GYRO Driftrate:", Robot.drivetrain.gyroSPI.driftRate);
//			SmartDashboard.putNumber("GYRO Rate:", Robot.drivetrain.gyroSPI.getRate());
			SmartDashboard.putNumber("GYRO Angle:", Robot.drivetrain.getHeading());
//			SmartDashboard.putNumber("GYRO reInits:", Robot.gyroReinits);
//			SmartDashboard.putBoolean("Gyro Cal Status", !Robot.gyroCalibrating);
//			SmartDashboard.putNumber("GYRO Status:", Robot.drivetrain.gyroSPI.getStatus());
//			SmartDashboard.putNumber("GYRO Temp:", Robot.drivetrain.gyroSPI.getTemp());
//
//
//			SmartDashboard.putNumber("Accel X:", Robot.accel.getX());
//			SmartDashboard.putNumber("Accel Y:", Robot.accel.getY());
//			SmartDashboard.putNumber("Accel Z:", Robot.accel.getZ());

//			SmartDashboard.putNumber("DTRight1MotorVoltage", Robot.drivetrain.getRight1MotorVoltage());
//			SmartDashboard.putNumber("DTRight2MotorVoltage", Robot.drivetrain.getRight2MotorVoltage());
//			SmartDashboard.putNumber("DTRight3MotorVoltage", Robot.drivetrain.getRight3MotorVoltage());
//
//			SmartDashboard.putNumber("DTLeft1MotorVoltage", Robot.drivetrain.getLeft1MotorVoltage());
//			SmartDashboard.putNumber("DTLeft2MotorVoltage", Robot.drivetrain.getLeft2MotorVoltage());
//			SmartDashboard.putNumber("DTLeft3MotorVoltage", Robot.drivetrain.getLeft3MotorVoltage());

			SmartDashboard.putNumber("Battery Voltage", Robot.pdp.getBatteryVoltage());
			SmartDashboard.putNumber("totalCurrent", Robot.pdp.getTotalCurrent());
			SmartDashboard.putNumber("pcmCurrent", Robot.pdp.getChannelCurrent(RobotMap.PCM_POWER));

			SmartDashboard.putNumber("Raw Intake IR", Robot.intakeRoller.getRawBoulderDistance());
			SmartDashboard.putNumber("Raw Indexer IR", Robot.indexer.getRawBoulderDistance());

			SmartDashboard.putNumber("DTRight1MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP));
			SmartDashboard.putNumber("DTRight2MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP));
			SmartDashboard.putNumber("DTRight3MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP));

			SmartDashboard.putNumber("DTLeft1MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP));
			SmartDashboard.putNumber("DTLeft2MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP));
			SmartDashboard.putNumber("DTLeft3MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP));
			
			SmartDashboard.putNumber("IntakeMotor1Current", Robot.pdp.getChannelCurrent(RobotMap.INTAKE_MOTOR_1_PDP));
			SmartDashboard.putNumber("IntakeMotor2Current", Robot.pdp.getChannelCurrent(RobotMap.INTAKE_MOTOR_2_PDP));

			SmartDashboard.putNumber("IndexerMotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.INDEXER_MOTOR_PDP));
			
			SmartDashboard.putNumber("ShooterMotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.SHOOTER_MOTOR_PDP));

//			SmartDashboard.putString("AutoName", Robot.getAutoName());

			SmartDashboard.putNumber("Left Stick Raw Value", OI.getInstance().driverJoystick.getLeftStickRaw_Y());
			SmartDashboard.putNumber("Right Stick Raw Value", OI.getInstance().driverJoystick.getRightStickRaw_Y());

			SmartDashboard.putNumber("Left Trigger Raw Value", OI.getInstance().driverJoystick.getLeftTriggerAxisRaw());
			SmartDashboard.putNumber("Right Trigger Raw Value", OI.getInstance().driverJoystick.getRightTriggerAxisRaw());

			SmartDashboard.putNumber("Operator Left Stick Raw Value", OI.getInstance().operatorJoystick.getLeftStickRaw_Y());
			SmartDashboard.putNumber("Operator Right Stick Raw Value", OI.getInstance().operatorJoystick.getRightStickRaw_Y());

			SmartDashboard.putNumber("Operator Left Trigger Raw Value", OI.getInstance().operatorJoystick.getLeftTriggerAxisRaw());
			SmartDashboard.putNumber("Operator Right Trigger Raw Value", OI.getInstance().operatorJoystick.getRightTriggerAxisRaw());

			SmartDashboard.putBoolean("Left Motor One Trip", !Robot.pdp.isLeftMotorOneTrip());
			SmartDashboard.putBoolean("Left Motor Two Trip", !Robot.pdp.isLeftMotorTwoTrip());
			SmartDashboard.putBoolean("Left Motor Three Trip", !Robot.pdp.isLeftMotorThreeTrip());

			SmartDashboard.putBoolean("Right Motor One Trip", !Robot.pdp.isRightMotorOneTrip());
			SmartDashboard.putBoolean("Right Motor Two Trip", !Robot.pdp.isRightMotorTwoTrip());
			SmartDashboard.putBoolean("Right Motor Three Trip", !Robot.pdp.isRightMotorThreeTrip());

			SmartDashboard.putBoolean("Left Lift Motor Trip", !Robot.pdp.isLiftLeftMotorTrip());
			SmartDashboard.putBoolean("Right Lift Motor Trip", !Robot.pdp.isLiftRightMotorTrip());
			SmartDashboard.putBoolean("Intake Left Motor Trip", !Robot.pdp.isIntakeLeftMotorTrip());
			SmartDashboard.putBoolean("Intake Right Motor Trip", !Robot.pdp.isIntakeLeftMotorTrip());

			SmartDashboard.putBoolean("Intake Exetended", Robot.intakePosition.isIntakeExtended());
			SmartDashboard.putBoolean("Intake REtracted", Robot.intakePosition.isIntakeRetracted());
			
//			SmartDashboard.putBoolean("autoMode", Robot.isAutoMode());
			
			// Open Intake LED Input 1
			// Close Intake LED Input 2
			
			// Gripper Open LED Input 3
			// Gripper Close LED Input 4
			
			// Open Retainer LED Input 5
			// Close Retainer LED Input 6
			
//			SmartDashboard.putBoolean("Left Drive Motor 1 Pass", Robot.drivetrain.leftMotor1Pass);
//			SmartDashboard.putBoolean("Left Drive Motor 2 Pass", Robot.drivetrain.leftMotor2Pass);
//			SmartDashboard.putBoolean("Left Drive Motor 3 Pass", Robot.drivetrain.leftMotor3Pass);
//			SmartDashboard.putBoolean("Right Drive Motor 1 Pass", Robot.drivetrain.rightMotor1Pass);
//			SmartDashboard.putBoolean("Right Drive Motor 2 Pass", Robot.drivetrain.rightMotor2Pass);
//			SmartDashboard.putBoolean("Right Drive Motor 3 Pass", Robot.drivetrain.rightMotor3Pass);
//			SmartDashboard.putBoolean("Indexer Motor Pass", Robot.indexer.indexerPass);
//			SmartDashboard.putBoolean("Intake Left Motor Pass", Robot.intakeRoller.intakeLeftPass);
//			SmartDashboard.putBoolean("Intake Right Motor Pass", Robot.intakeRoller.intakeRightPass);
//			SmartDashboard.putBoolean("Shooter FWD Motor Pass", Robot.shooter.shooterFWDPass);
//			SmartDashboard.putBoolean("Shooter AFT Motor Pass", Robot.shooter.shooterAFTPass);
			
			
			//file log
			log.println(Timer.getFPGATimestamp() + "\t" +
					System.currentTimeMillis() + "\t" +
					
					Robot.driverstation.isAutonomous() + "\t" +
//					Robot.getAutoName() + "\t" + 
					Robot.driverstation.isOperatorControl() + "\t" +
					Robot.driverstation.isFMSAttached() + "\t" +
					Robot.driverstation.getMatchTime() + "\t" +
					Robot.driverstation.getBatteryVoltage() + "\t" +
					Robot.driverstation.isBrownedOut() + "\t" +  
					
//					Robot.drivetrain.getLeft1MotorVoltage() + "\t" +
//					Robot.drivetrain.getLeft2MotorVoltage() + "\t" +
//					Robot.drivetrain.getLeft3MotorVoltage() + "\t" +
//
//					Robot.drivetrain.getRight1MotorVoltage() + "\t" +
//					Robot.drivetrain.getRight2MotorVoltage() + "\t" +
//					Robot.drivetrain.getRight3MotorVoltage() + "\t" +

					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP) + "\t" +

					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP) + "\t" +
					Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP) + "\t" +

//					Robot.drivetrain.gyroSPI.getRate() + "\t" +
//					Robot.drivetrain.gyroSPI.getPos() + "\t" +

					Robot.drivetrain.drivetrainLeftEncoder.getPos() + "\t" +
					Robot.drivetrain.drivetrainLeftEncoder.getRate() + "\t" +

					Robot.drivetrain.drivetrainRightEncoder.getPos() + "\t" +
					Robot.drivetrain.drivetrainRightEncoder.getRate() + "\t" //+

//					Robot.accel.getX() + "\t" +
//					Robot.accel.getY() + "\t" +
//					Robot.accel.getZ() + "\t" +
//					IMU.getAccPitch() + "\t" +
//					IMU.getAccRoll() + "\t" +
					);
			log.flush();


		}
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
