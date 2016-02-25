/**
 * 
 */
package org.team2168.utils;

import edu.wpi.first.wpilibj.I2C;

/**
 * Class to interface with seven segment led driver module (SAA1064) over I2C.
 * Datasheet: http://lib.chipdip.ru/301/DOC000301604.pdf
 *
 * Original source code ported from arduino: http://cjparish.blogspot.com/2010/01/playing-with-7-segment-led-displays.html 
 * Created by Christopher Parish on 19/01/2010.
 * Copyright 2010 Chris Parish. All rights reserved.
 * 
 * @author James@team2168.org
 */
public class SAA1064 {
	// LED number lookup table
	private final static int[] numbers = {0x3F,0x06,0x5B,0x4F,0x66,0x6D,0x7D,0x07,0x7F,0x6F,0x77,0x7C,0x39,0x5E,0x79,0x71};

	public enum brightness_t {
		// Brightness values
		BRIGHT_HI  (0x40),
		BRIGHT_MED (0x20),
		BRIGHT_LOW (0x10);		
		
		private final int val;

		brightness_t(int val) {
			this.val = val;
		}

		public int getVal() {
			return val;
		}
	};

	// Multiplex value
	private final static byte MULTIPLEX  = 0x07;
	// Decimal point value
	private final static int POINT       = 0x80;
	
	private int _command;
	private int _digits;
	private int _dp;
	private boolean _reverse_digit_order;
	
	I2C i2c;
	
	/**
	 * Constructor
	 *
	 * @value	int addr	The address of the SAA1064 controller
	 */
	public SAA1064(I2C.Port port, int address) {
		_reverse_digit_order = false;
		_command = brightness_t.BRIGHT_MED.val & ~MULTIPLEX;
		_digits = 2;
		_dp = 0;
		i2c = new I2C(port, address);
	}
	
	/**
	 * Clears the display by sending zeros
	 */
	public void clear() {
		write((byte)1);
		for (int i=1; i <= _digits; i++) {
			write((byte)0x00);
		}
	}
	
	/**
	 * Reverses the order digits are written out to the display.
	 * Default is low to high, pass true in to output digits in high to low order.
	 */
	public void digit_order(boolean reversed) {
		_reverse_digit_order = reversed;
	}
	
	/**
	 * Turns on multiplexing
	 */
	public void multiplexOn() {
		_command |= MULTIPLEX;
		_digits = 4;
	}
	
	/**
	 * Turns off multiplexing
	 */
	public void multiplexOff() {
		_command &= ~MULTIPLEX;
		_digits = 2;
	}

	/**
	 * Sets the brightness of the display by changing the resistance of the sink
	 * @param brightness Can be "hi" "med" and "low"
	 */
	public void setBrightness(brightness_t brightness) {
		//clear all the brightness bits
		_command &= 0b10000111;
		_command = _command | brightness.val;
		sendCommand();
	}
	
	/**
	 * Sets the number of decimal places to use
	 */
	public void setDP(int point) {
		if (point <= 4 && point >= 0) {
			_dp = point;
		} else {
			_dp = 0;
		}
	}

	/**
	 * Sends an integer value to the display. Doesn't take into account the
	 * decimal point.
	 * @param num The number to display
	 */
	public void send(int num) {
		updateLED((long)num);
	}

	/**
	 * Sends a long integer number to the display. takes into account the decimal place
	 * and changes the value of num accordingly.
	 * 
	 * @param num The number to display
	 */
	public void send(long num) {
		num = num * (long) Math.pow(10, _dp);
		updateLED(num);
	}

	/**
	 * Sends a float (real) number to the display, taking into account the number of
	 * decimal places
	 * 
	 * @param num The number to display
	 */
	public void send(float num) {
		num = num * (float) Math.pow(10, _dp);
		updateLED((long)num);
	}

	/**
	 * Sends a hex value to the display. The passed value an be in any integer format
	 * @param numThe number to be displayed as hex
	 */
	public void sendHex(int num) {
		int[] a = new int[32];
		int i = 0;
		byte[] bytes = {0,0,0,0};
		
		num = num % 0x10000;

		while( num != 0 && i < 32 ) {
			a[i] = num % 16;
			num = num/16;
			i++;
		}
		if (i > 4) {
			i = 4;
		}

		for (int loop = i-1; loop >= 0; loop--) {
			bytes[loop] = (byte)numbers[a[loop]];
		}
		
		write((byte)1);
		if ((_command & MULTIPLEX) > 0) {
			if(_reverse_digit_order) {
				write(bytes[3]);
				write(bytes[2]);
				write(bytes[1]);
				write(bytes[0]);
			} else {
				write(bytes[0]);
				write(bytes[1]);
				write(bytes[2]);
				write(bytes[3]);
			}
		} else {
			if(_reverse_digit_order) {
				write(bytes[1]);
				write(bytes[0]);
			} else {
				write(bytes[0]);
				write(bytes[1]);
			}
		}
	}
	
	/**
	 * Updates the display with decimal numbers
	 */
	private void updateLED(long num) {
		int Thousands, Hundreds, Tens, Base;
		byte[] data;
		
		write((byte)1);
		num = num%10000; //kill every number bigger than 1000
		Thousands = (int)(num/1000);
	    Hundreds = (int)((num-(Thousands*1000))/100);
	    Tens = (int)((num-((Thousands*1000)+(Hundreds*100)))/10);
	    Base = (int)(num-((Thousands*1000)+(Hundreds*100)+(Tens*10)));
		
		Thousands = numbers[Thousands];
		Hundreds  = numbers[Hundreds];
		Tens	  = numbers[Tens];
		Base	  = numbers[Base];
			switch (_dp) {
				case 1:
					Tens |= POINT;
					break;
				case 2:
					Hundreds |= POINT;
					break;
				case 3:
					Thousands |= POINT;
					break;
				default:
					break;
			}
		if ((_command & MULTIPLEX) > 0) {
			data = new byte[4];
			if(_reverse_digit_order) {
				data[0] = (byte)Thousands;
				data[1] = (byte)Hundreds;
				data[2] = (byte)Tens;
				data[3] = (byte)Base;
			} else {
				data[0] = (byte)Base;
				data[1] = (byte)Tens;
				data[2] = (byte)Hundreds;
				data[3] = (byte)Thousands;
			}
		} else {
			data = new byte[2];
			if(_reverse_digit_order) {
				data[0] = (byte)Tens;
				data[1] = (byte)Base;
			} else {
				data[0] = (byte)Base;
				data[1] = (byte)Tens;
				
			}
		}
		write(data);
	}
	
	/**
	 * Sends the command byte to the display processor
	 */
	private void sendCommand() {
		write((byte)0);
		write((byte)_command);
	}
	
	/**
	 * Write a single byte out to the attached i2c device.
	 * @param data the byte to write
	 */
	private void write(byte data) {
		byte[] foo = new byte[1];
		foo[0] = data;

		write(foo);
	}
	
	/**
	 * Write a byte buffer to the attached i2c device.
	 * @param data the byte array to write
	 */
	private void write(byte[] data) {
		i2c.transaction(data, data.length, null, 0);
	}
}