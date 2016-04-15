package org.team2168.utils;

import java.util.TimerTask;

import org.team2168.RobotMap;

//import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;



public class PowerDistribution {
	private java.util.Timer executor;
	private long period;

	private PowerDistributionPanel pdp;

	private volatile double[] channelCurrent;
	private volatile double[] channelVoltage;
	private volatile int[] channelError;

	private volatile double batteryVoltage;
	private volatile double mainBreakerTemp;

	private volatile double totalCurrent;
	private volatile double totalEnergy;
	private volatile double totalPower;
	private volatile double temp;

	public static final int NUM_OF_PDP_CHANNELS = 16;
	
	public PowerDistribution(long period) {
		this.period = period;
		pdp = new PowerDistributionPanel();

		channelCurrent = new double[NUM_OF_PDP_CHANNELS];
		channelVoltage = new double[NUM_OF_PDP_CHANNELS];
		channelError = new int[NUM_OF_PDP_CHANNELS];
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new PowerDistributionTask(this), 0L, this.period);

	}

	public int getChannelError(int channel)
	{
		return channelError[channel];
	}

	public double getChannelCurrent(int channel)
	{
		return channelCurrent[channel];
	}

	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	private void run() {

		for(int i=0; i<NUM_OF_PDP_CHANNELS; i++)
		{
			batteryVoltage = pdp.getVoltage();
			
			channelCurrent[i] = pdp.getCurrent(i);
			channelError[i] = 0; //no Error

			if(channelCurrent[i] > RobotMap.WARNING_CURRENT_LIMIT)
				channelError[i] = 1; //warning
			else if (channelCurrent[i] > RobotMap.STALL_CURRENT_LIMIT)
				channelError[i] = 2; //danger
			
			
		}

		totalCurrent = pdp.getTotalCurrent();
		totalEnergy = pdp.getTotalEnergy();
		temp = pdp.getTemperature();
		totalPower = pdp.getTotalPower();

	}

	private class PowerDistributionTask extends TimerTask {
		private PowerDistribution console;

		private PowerDistributionTask(PowerDistribution printer) {
			if (printer == null) {
				throw new NullPointerException("PDP was null");
			}
			this.console = printer;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			console.run();
		}
	}
	
	/**
	 * Gets total Current
	 * @return Total Current
	 */
	public double getTotalCurrent() {
		return totalCurrent;
	}
	
	/**
	 * Gets total Energy
	 * @return Total Energy
	 */
	public double totalEnergy() {
		return totalEnergy;
	}

	/**
	 * Gets total Power
	 * @return Total Power
	 */
	public double totalPower() {
		return totalPower;
	}
	
	public boolean isRightMotorThreeTrip() {
		if (channelError[RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isRightMotorTwoTrip() {
		if (channelError[RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isRightMotorOneTrip() {
		if (channelError[RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isIntakeMotorTrip() {
		if (channelError[RobotMap.INTAKE_MOTOR_1_PDP] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isIndexMotorTrip() {
		if (channelError[RobotMap.INDEXER_MOTOR_PDP] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isShooterMotorOneTrip() {
		
		if (channelError[RobotMap.SHOOTER_MOTOR_FWD_PDP] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isShooterMotorTwoTrip() {
		if (channelError[RobotMap.SHOOTER_MOTOR_AFT_PDP] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isLeftMotorOneTrip() {
		if (channelError[RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isLeftMotorTwoTrip() {
		if (channelError[RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isLeftMotorThreeTrip() {
		if (channelError[RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP] == 2) 
			return true;
		else
			return false;
	}
}
