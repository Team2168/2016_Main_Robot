package org.team2168.subsystems;

import org.team2168.RobotMap;

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
    * Takes in a given angle and moves motor to that angle
    * @param degrees is a double from 0 to 180
    */
   public void setAngle(double degrees)
   {   
	   hoodMotor1.setAngle(degrees);
   }
   
   /**
    * Finds the motor's current angle 
    * @return is the value of the angle
    */
   public double getAngle()
   {
	   return hoodMotor1.getAngle();
   }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
}

