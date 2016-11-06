package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**Choose and use a certain control style.
 *@author Ben Waid, Elijah Reeds, Peter Dentch
 */
public class ControllerStyleSwitch extends Command {
	
	private static int crtlStyle = 0;
	
	
    public ControllerStyleSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }
    
    /**Sets the current control style.
     * @author Ben Waid, Elijah Reeds, Peter Dentch
	 * @param Control style.
	 */
    public void setCrtlStyle(int style){
    	//Edit this as we make control styles with a case/if else 
    	//structure to make sure no invalid numbers are put in.
    	
    	crtlStyle = style;
    }

    /**Gets the current Control Style.
     * @author Elijah Reeds
     * @return Current Control Style.
     */
    public int getCrtlStyle(){
    	return(crtlStyle);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
