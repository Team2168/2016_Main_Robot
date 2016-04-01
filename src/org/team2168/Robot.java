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
import org.team2168.commands.auto.DriveOverDefense;
import org.team2168.commands.auto.ReachDefense;
import org.team2168.commands.auto.ShootFromSpyBox;
import org.team2168.commands.pneumatics.StartCompressor;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Shooter;
import org.team2168.subsystems.ShooterHood;
import org.team2168.subsystems.ShooterPneumatics;
import org.team2168.utils.ConsolePrinter;
import org.team2168.utils.I2CLights;
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
		if(indexer.TurnFlashlightOn()) {
    		flashlight.set(Relay.Value.kForward);
    	} else {
    		flashlight.set(Relay.Value.kOff);
    	}
	}
	
	/**
	 * Update the patterns on the light stip over I2C. 
	 */
	private static void updateLED() {
    	if (isAutoMode()) {
    		//Lights ALL strips in a rainbow pattern
    		lights.Rainbow();
    	} else if(intakePosition.isIntakeExtended() && shooterPneumatics.isShooterFarShotPosition()
    			&& shooter.shooterSpeedController.isFinished()) {
    		//we are shooting and the shooter is at speed
    		lights.Solid(0, 255, 0, I2CLights.Range.Intake);
    	} else if(intakePosition.isIntakeExtended() && shooterPneumatics.isShooterStowPosition()) {
    		//intake and shooter hood are in non-interfering (stowed) position for low bar
    		lights.SlowBlink(0, 255, 0, I2CLights.Range.Intake);
    	} else if(intakePosition.isIntakeExtended() || shooterPneumatics.isShooterCloseShotPosition()) {
    		//chase red if the shooter or intake are in interfering positions w/ the low bar.
    		lights.ChaseIn(255, 0, 0, I2CLights.Range.Intake);
    	} else {
    		//We shouldn't get here...
    		lights.Fade(80, 80, 80, I2CLights.Range.Intake);
    	}
    	
    }

}
