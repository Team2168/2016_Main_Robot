package org.team2168.utils;

/**
 * A class to perform lagrange interpolation
 * given 4 points to produce a 3rd degree polynomial.
 * @author Peter Dentch
 */
public class LagrangeInterpolator {
	
	static final double MIN_DRIVE_SPEED = 0.22;
	static final double JOYSTICK_DEADBAND = 0.06;
	
	//This array should strictly be only 4 points in ascending order,
	//forming a curve with positive values to also be reflected to use 
	//for negative input values
	static double curvePoints[][] = {
			{JOYSTICK_DEADBAND , MIN_DRIVE_SPEED},
			{0.53 , 0.4},
			{0.765, 0.6},
			{1 , 1},
		};
	
	/**
	 * Third degree lagrange interpolation given 4 points.
	 * @param input is the given X Value for the function
	 * @param pts is the array of points to calculate the line
	 * @return the resulting Y Value of the function
	 */
	private double lagrInterpolate(double input, double[][] pts){
		
		//taking the values from the array to plug into the interpolation,
		//also now made more simple for less space and a more math-like form
		double x1, x2, x3, x4;
		x1 = pts[0][0];
		x2 = pts[1][0];
		x3 = pts[2][0];
		x4 = pts[3][0];
		
		//The standard notation for lagrange interpolation given 4 points
		double L1, L2, L3, L4;
		L1 = (((input - x2)*(input - x3)*(input - x4))/((x1 - x2)*(x1 - x3)*(x1 - x4))); 
		L2 = (((input - x1)*(input - x3)*(input - x4))/((x2 - x1)*(x2 - x3)*(x2 - x4)));
		L3 = (((input - x1)*(input - x2)*(input - x4))/((x3 - x1)*(x3 - x2)*(x3 - x4)));
		L4 = (((input - x1)*(input - x2)*(input - x3))/((x4 - x1)*(x4 - x2)*(x4 - x3)));
		
		double retVal = (L1*pts[0][1]) + (L2*pts[1][1]) + (L3*pts[2][1]) + (L4*pts[3][1]);
		return retVal;
	}
	
	/**
	 * Method intended to receive driver controller joystick inputs
	 * and will calculate the value to output to the motors from the
	 * equation for a curved line formed by given points and lagrange interpolation.
	 * @param input is the value to plug into the calculated line
	 * @return is the final value from the function modified for motor driving 
	 */
	public double interpolatedJoystickVal(double input){
		
		//Instantiating the return value
		double retVal = -0.0;
		
		//Making sure the input is between 1 and -1
		if(input > 1)
			input = 1;
		else if(input < -1)
			input = -1;
				
		//Accounting for the joystick values that wouldn't normally start the motors,
		//making the minimum output of the joystick after 0 set the motors to their minimum speed
		if(input < JOYSTICK_DEADBAND && input > -JOYSTICK_DEADBAND)
			retVal = 0.0;
				
		//Using lagrange interpolation to then find the needed value, using the absolute value
		//of the input because only the positive section of the graph applies to the desired curve
		else retVal = lagrInterpolate(Math.abs(input), curvePoints);
				
		//Reflecting the curve for backwards driving from negative controller values
		if(input < 0.0 && retVal != 0.0)
			retVal = -retVal;
				
		return retVal;
	}

}
