package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.shooterhood.SetHoodWheelPosition;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *ShooterHood subsystem
 *@author Harris Jilani
 */
public class ShooterHood extends Subsystem {
   
	/**
	 * ShooterHood member variable
	 */
   private Servo hoodMotor1;
   
   private static ShooterHood instance = null;
   
   
   /**
    * Default constructor for ShooterHood subsystem
    */
   private ShooterHood(){
	   
	   hoodMotor1 = new Servo(RobotMap.SHOOTER_HOOD_1);
   }

   /**
	 * Returns ShooterHood singleton object
	 * @return is the current shooterhood object
	 */
   public static ShooterHood getInstance()
   {
	   if(instance == null)
		   instance = new ShooterHood();
	   
	   return instance;
   }
   
   /**
    * Takes in a given position and moves motor to that position
    * @param pos is a double from 1 to -1
    */
   public void setPosition(double pos)
   {   
	   hoodMotor1.setPosition(pos);
   }
   
   /**
    * Gets the servo motor's current position
    * @param pos is a double from 1 to -1
    */
   public void getPosition(double pos)
   {
	   hoodMotor1.getPosition();
   }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new SetHoodWheelPosition());
    }
}

