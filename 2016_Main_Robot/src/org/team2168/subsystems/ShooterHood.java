package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterHood extends Subsystem {
    
   private Servo hoodMotor1;
   
   private static ShooterHood instance = null;
   
   private ShooterHood(){
	   
	   hoodMotor1 = new Servo(RobotMap.SHOOTER_HOOD_1);
   }

   public static ShooterHood getInstance()
   {
	   if(instance == null)
		   instance = new ShooterHood();
	   
	   return instance;
   }
   
   /**
    * Sets the servo motor to a assigned position
    * @param double pos
    */
   private void setPosition(double pos)
   {   
	   hoodMotor1.setPosition(pos);
   }
   
   /**
    * Gets the servo motor current position
    * @param double pos
    */
   private void getPosition(double pos)
   {
	   hoodMotor1.getPosition();
   }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

