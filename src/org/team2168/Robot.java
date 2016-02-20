
package org.team2168;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.team2168.PID.sensors.TCPCamSensor;
import org.team2168.commands.pneumatics.StartCompressor;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Shooter;
import org.team2168.subsystems.ShooterHood;
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

	public static Drivetrain drivetrain;
	public static Indexer indexer;
	public static Shooter shooter;
	public static ShooterHood shooterhood;
	public static IntakeRoller intakeRoller;
	public static IntakePosition intakePosition;
	public static TCPCamSensor tcpCamSensor;
	public static Pneumatics pneumatics;
	public static I2C i2c;
	
    Command autonomousCommand;
    SendableChooser chooser;
    
    Compressor comp;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
    	drivetrain = Drivetrain.getInstance();
    	shooter = Shooter.getInstance();
    	shooterhood = ShooterHood.getInstance();
    	indexer = Indexer.getInstance();
    	intakeRoller = IntakeRoller.getInstance();
        intakePosition = IntakePosition.getInstance();
        pneumatics = Pneumatics.getInstance();
        
        i2c = new I2C(RobotMap.i2cPort, RobotMap.i2cAddress);

        tcpCamSensor = new TCPCamSensor(41234, 0);
        
        oi = OI.getInstance();
        
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        
        new StartCompressor();
        
        
        System.out.println("Robot Done Loading");
    }
	
	/**
     * This method is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
    /**
     * This method is called repeatedly while the robot is disabled.
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

        SmartDashboard.putNumber("Drivetrain Left Position", drivetrain.getLeftPosition());
        SmartDashboard.putNumber("Drivetrain Right Position", drivetrain.getRightPosition());
        SmartDashboard.putBoolean("Boulder in Intake", intakeRoller.isBoulderPresent());
        SmartDashboard.putBoolean("Boulder in Indexer", indexer.isBoulderPresent());
        SmartDashboard.putNumber("Shooter Speed", shooter.getSpeed());
        SmartDashboard.putBoolean("Intake up", intakePosition.isIntakeRetracted());
        SmartDashboard.putBoolean("Intake down", intakePosition.isIntakeExtended());
        SmartDashboard.putNumber("Boulder Distance Intake", intakeRoller.getAveragedRawBoulderDistance());
        SmartDashboard.putNumber("Boulder Distance Indexer", indexer.getAveragedRawBoulderDistance());
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
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This method is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Called once when the robot enters the teloperated mode.
     */
    public void teleopInit() {
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
        Scheduler.getInstance().run();
        updateLED();
    }
    
    /**
     * This method is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private static void updateLED() {
    	byte[] data = new byte[8];
    	if (intakePosition.isIntakeRetracted()) {
    		data[0] = (byte) 255; //red
    		data[1] = (byte) 0; //green
    		data[2] = (byte) 0; //blue
    		data[3] = (byte) 1; //pattern
    	}
    	else {
    		data[0] = (byte) 0; //red
    		data[1] = (byte) 0; //green
    		data[2] = (byte) 255; //blue
    		data[3] = (byte) 1; //pattern
    	}
    	i2c.writeBulk(data);
    }
}
