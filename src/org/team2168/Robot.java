package org.team2168;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.team2168.PID.sensors.TCPCamSensor;
import org.team2168.commands.auto.DoNothing;
import org.team2168.commands.auto.DriveOverChevalDeFrise;
import org.team2168.commands.auto.DriveOverChevalDeFriseAndFire;
import org.team2168.commands.auto.DriveOverDefense;
import org.team2168.commands.auto.DriveOverDefenseAndFireCenter;
import org.team2168.commands.auto.DriveOverDefenseAndRotateFromDifferentPosition;
import org.team2168.commands.auto.DriveOverLowGoalAndFire;
import org.team2168.commands.auto.ReachDefense;
import org.team2168.commands.auto.ShootFromSpyBox;
import org.team2168.commands.pneumatics.StartCompressor;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Shooter;
import org.team2168.subsystems.ShooterHood;
import org.team2168.subsystems.ShooterPneumatics;
import org.team2168.utils.ConsolePrinter;
import org.team2168.utils.Debouncer;
import org.team2168.utils.I2CLights;
import org.team2168.utils.I2CLights.Range;
import org.team2168.utils.PowerDistribution;
import org.team2168.subsystems.IntakePosition;
import org.team2168.subsystems.IntakeRoller;
import org.team2168.subsystems.Pneumatics;
import org.team2168.subsystems.Indexer;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;

	private static DigitalInput practiceBot;
	
	public static Drivetrain drivetrain;
	public static Indexer indexer;
	public static Shooter shooter;
	public static ShooterHood shooterhood;
	public static IntakeRoller intakeRoller;
	public static IntakePosition intakePosition;
	public static Pneumatics pneumatics;
	public static ShooterPneumatics shooterPneumatics;
	
    static Command autonomousCommand;
    public static SendableChooser autoChooser;
    
    static boolean autoMode;
    private static boolean matchStarted = false;
    public static int gyroReinits;
	private double lastAngle;
	private Debouncer gyroDriftDetector = new Debouncer(1.0);
	public static boolean gyroCalibrating = false;
	private boolean lastGyroCalibrating = false;
	private double curAngle = 0.0;
    
    public static DriverStation driverstation;
	public static PowerDistribution pdp;
	
	private static I2CLights lights;
	Compressor comp;
    ConsolePrinter printer; // SmartDash printer
    
    Relay flashlight;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	practiceBot = new DigitalInput(RobotMap.PRACTICE_BOT_JUMPER);
    	
    	//create subsystems
    	drivetrain = Drivetrain.getInstance();
    	shooter = Shooter.getInstance();
    	shooterhood = ShooterHood.getInstance();
    	indexer = Indexer.getInstance();
    	intakeRoller = IntakeRoller.getInstance();
        intakePosition = IntakePosition.getInstance();
        pneumatics = Pneumatics.getInstance();
        shooterPneumatics = ShooterPneumatics.getInstance();
        lights = I2CLights.getInstance();

        //create controls
        oi = OI.getInstance();
    
        //choose auto
        autoSelectInit();
        
        //run compressor
        new StartCompressor();
      
        
        driverstation = DriverStation.getInstance();

		pdp = new PowerDistribution(RobotMap.PDPThreadPeriod);
		pdp.startThread();
        
        printer = new ConsolePrinter(RobotMap.SmartDashThreadPeriod);
		printer.startThread();
		
		flashlight = new Relay(RobotMap.FLASHLIGHT_RELAY);
        flashlight.set(Relay.Value.kOff);
        
        drivetrain.calibrateGyro();
        
        System.out.println("Robot Done Loading");
    }
    

    
    /**
	 * Get the name of an autonomous mode command.
	 * @return the name of the auto command.
	 */
	public static String getAutoName() {
		if (autonomousCommand != null) {
			return autonomousCommand.getName();
		} else {
			return "None";
		}
	}
    
	/**
     * This method is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    		autoMode = false;
    		matchStarted = false;
    }
	
    /**
     * This method is called repeatedly while the robot is disabled.
     */
	public void disabledPeriodic() {
		autonomousCommand = (Command) autoChooser.getSelected();
		// Kill all active commands
		Scheduler.getInstance().run();
		updateLED();
		autoMode = false;
		
		// Check to see if the gyro is drifting, if it is re-initialize it.
		gyroReinit();
     }

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	autoMode = true;
    	
		matchStarted = true;
		drivetrain.stopGyroCalibrating();
		drivetrain.gyroSPI.reset();
    	
        autonomousCommand = (Command) autoChooser.getSelected();
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This method is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	autoMode = true;
    	setFlashlight();
    	updateLED();
        Scheduler.getInstance().run();
    }

    /**
     * Called once when the robot enters the teloperated mode.
     */
    public void teleopInit() {
    	autoMode = false;
    	
		matchStarted = true;
		drivetrain.stopGyroCalibrating();
    	
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This method is called periodically during operator control
     */
    public void teleopPeriodic() {
    	autoMode = false;
    	setFlashlight();
    	updateLED();
        Scheduler.getInstance().run();
    }
    
    /**
     * This method is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        updateLED();
    }
    
    /**
     * Adds the autos to the selector
     */
    public void autoSelectInit() {
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Default: Shoot From Spy Box", new ShootFromSpyBox());
        autoChooser.addObject("Do Nothing", new DoNothing());
        autoChooser.addObject("Drive Over Defense", new DriveOverDefense());
        autoChooser.addObject("Drive over CDF", new DriveOverChevalDeFrise());
        autoChooser.addObject("Drive over CDF and Fire", new DriveOverChevalDeFriseAndFire());
        autoChooser.addObject("Driver Over Defense and Shoot Center", new DriveOverDefenseAndFireCenter());
        autoChooser.addObject("Drive Over Defense and Shoot From Fourth", new DriveOverDefenseAndRotateFromDifferentPosition(-5));
        autoChooser.addObject("Drive Under Low Bar and Fire", new DriveOverLowGoalAndFire());
        //  autoChooser.addObject("Reach Defense", new ReachDefense());
    }
	
    /**
	 *
	 * @return true if the robot is in auto mode
	 */
	public static boolean isAutoMode() {
		return autoMode;
	}
	

	
	/**
	 * Returns the status of DIO pin 24 on the MXP. Place a jumper between pin
	 * pin 32 and 30 on the MXP to indicate this RoboRio is installed on the
	 * practice bot.
	 *
	 * @return true if this is the practice robot
	 */
	public static boolean isPracticeRobot() {
		return !practiceBot.get();
	}
	
	private void setFlashlight() {
		if(indexer.TurnFlashlightOn() || shooter.isBoulderPresent()) {
    		flashlight.set(Relay.Value.kForward);
    	} else {
    		flashlight.set(Relay.Value.kOff);
    	}
	}
	
	/**
	 * Update the patterns on the light stip over I2C. 
	 */
	private static void updateLED() {
    	//if (isAutoMode()) {
    		//Lights ALL strips in a rainbow pattern
    		//elights.Rainbow();
    	if(Robot.driverstation.isDisabled()) {
    		//Robot is disabled
    		lights.ChaseAll(I2CLights.Range.Intake);
		} else if((shooter.getSpeed() > 2000) && shooter.shooterSpeedController.isFinished()) {
    		//shooter is up to speed - slash green
    		lights.FastBlink(0, 255, 0, I2CLights.Range.Intake);
    	} else if((shooter.getSpeed() > 2000) && !shooter.shooterSpeedController.isFinished()) {
    		//shooter is running, but not at speed yet
    		lights.FastBlink(255, 0, 0, I2CLights.Range.Intake);
    	} else if(indexer.getAveragedRawBoulderDistance() > 1.5
    			|| shooter.getAveragedRawBoulderDistance() > 1.5) {
    		//Ball in the robot
    		lights.Solid(255, 0, 0, I2CLights.Range.Intake);
    	} else if(indexer.getAveragedRawBoulderDistance() < 1.5
    			&& shooter.getAveragedRawBoulderDistance() < 1.5) {
    		//No ball in the robot, yellow lights solid
    		lights.Solid(75, 75, 0, I2CLights.Range.Intake);
    	} else {
    		//We shouldn't get here...
    		lights.Fade(80, 80, 80, I2CLights.Range.Intake);
    	}
    
    }
	
	/**
	 * Method which checks to see if gyro drifts and resets the gyro. Call this
	 * in a loop.
	 */
	private void gyroReinit() {
		//Check to see if the gyro is drifting, if it is re-initialize it.
		//Thanks FRC254 for orig. idea.
		curAngle = drivetrain.getHeading();
		gyroCalibrating = drivetrain.isGyroCalibrating();

		if (lastGyroCalibrating && !gyroCalibrating) {
			//if we've just finished calibrating the gyro, reset
			gyroDriftDetector.reset();
			curAngle = drivetrain.getHeading();
			System.out.println("Finished auto-reinit gyro");
		} else if (gyroDriftDetector.update(Math.abs(curAngle - lastAngle) > (0.75 / 50.0))
				&& !matchStarted && !gyroCalibrating) {
			//&& gyroReinits < 3) {
			gyroReinits++;
			System.out.println("!!! Sensed drift, about to auto-reinit gyro ("+ gyroReinits + ")");
			drivetrain.calibrateGyro();
		}

		lastAngle = curAngle;
		lastGyroCalibrating = gyroCalibrating;
	}
	
	

}
