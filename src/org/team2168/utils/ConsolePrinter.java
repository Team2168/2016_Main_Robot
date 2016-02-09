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
			//SmartDashboard.putData("Autonomous Mode Chooser", Robot.autoChooser);
			SmartDashboard.putNumber("gameClock", (int)DriverStation.getInstance().getMatchTime());
			SmartDashboard.putBoolean("Left Drive Motor 1 Pass", Robot.drivetrain.leftMotor1Pass);
			SmartDashboard.putBoolean("Left Drive Motor 2 Pass", Robot.drivetrain.leftMotor2Pass);
			SmartDashboard.putBoolean("Left Drive Motor 3 Pass", Robot.drivetrain.leftMotor3Pass);
			SmartDashboard.putBoolean("Right Drive Motor 1 Pass", Robot.drivetrain.rightMotor1Pass);
			SmartDashboard.putBoolean("Right Drive Motor 2 Pass", Robot.drivetrain.rightMotor2Pass);
			SmartDashboard.putBoolean("Right Drive Motor 3 Pass", Robot.drivetrain.rightMotor3Pass);
			SmartDashboard.putBoolean("Indexer Motor Pass", Robot.indexer.indexerPass);
			SmartDashboard.putBoolean("Intake Left Motor Pass", Robot.intake.intakeLeftPass);
			SmartDashboard.putBoolean("Intake Right Motor Pass", Robot.intake.intakeRightPass);
			SmartDashboard.putBoolean("Shooter FWD Motor Pass", Robot.shooter.shooterFWDPass);
			SmartDashboard.putBoolean("Shooter AFT Motor Pass", Robot.shooter.shooterAFTPass);
			
			
			//file log
			log.println(Timer.getFPGATimestamp() + "\t" +
					System.currentTimeMillis() + "\t" +
					DriverStation.getInstance().isDisabled() + "\t" +
					DriverStation.getInstance().isEnabled() + "\t" +
					DriverStation.getInstance().isAutonomous() + "\t" + 
					DriverStation.getInstance().isOperatorControl() + "\t" +
					DriverStation.getInstance().isFMSAttached() + "\t" +
					DriverStation.getInstance().getMatchTime() + "\t" +
					DriverStation.getInstance().getBatteryVoltage() + "\t" +
					DriverStation.getInstance().isBrownedOut());
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
