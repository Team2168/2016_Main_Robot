package org.team2168.utils;

/**
 * Electronic breaking, A.K.A. Falcon Claw
 * now for adjusting a joystick output curve to dim the maximum
 * drive speed formed using lagrange interpolation from an array of points 
 * @author Peter Dentch
 */
public class NewFalconClaw {
	
	//The intended first points of the array
	static final double JOYSTICK_DEADBAND = 0.06;
	static final double MIN_DRIVE_SPEED = 0.22;
	
	//This array should strictly be only 4 points in ascending order,
	//with the first point being the minimum positive output of your joystick
	//and the minimum speed of your drivetrain.
	//The points form a curve with positive values to also be reflected to use 
	//for negative input values in reverse driving
	static final double curvePoints[][] = {
		{JOYSTICK_DEADBAND , MIN_DRIVE_SPEED},
		{0.53 , 0.4},
		{0.765, 0.6},
		{1 , 1},
	};
	
	/**
	 * The method for calculating the final value to be sent to the drive motors 
	 * using the electronic braking system "Falcon Claw"
	 * @param inputSpeed is the joystick value to be used for adjusted interpolation
	 * @param triggerBreak is the controller trigger value to set the dimming amount
	 * @return the adjusted value from the breaking to be sent to the drive motors 
	 */
	public static double FalconClaw(double inputSpeed, double triggerBreak){
		
		//TODO: effectively explain what is being done here...
		
		double divisor = triggerBreak + 1.00;
		
		double minus1, minus2, minus3;
		minus1 = curvePoints[1][1] - MIN_DRIVE_SPEED;
		minus2 = curvePoints[2][1] - MIN_DRIVE_SPEED;
		minus3 = curvePoints[3][1] - MIN_DRIVE_SPEED;
		
		double divVal1, divVal2, divVal3;
		divVal1 = minus1 / divisor;
		divVal2 = minus2 / divisor;
		divVal3 = minus3 / divisor;
		
		double finPt1, finPt2, finPt3;
		finPt1 = divVal1 + MIN_DRIVE_SPEED;
		finPt2 = divVal2 + MIN_DRIVE_SPEED;
		finPt3 = divVal3 + MIN_DRIVE_SPEED;
		
		//forming a new array for making a new adjusted line
		double newPts[][] = {
			{curvePoints[0][0], curvePoints[0][1]},
			{curvePoints[1][0], finPt1},
			{curvePoints[2][0], finPt2},
			{curvePoints[3][0], finPt3},
		};
		
		//interpolating using the new array and the joystick input value to get the final drive speed
		double retVal = LagrangeInterpolator.interpolatedJoystickVal(inputSpeed, newPts);
		
		return retVal;
	}

}
