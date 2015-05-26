package com.onk.grp2.WindTurbine.driver;

import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import com.darkprograms.picasoserial.LCD;
import com.darkprograms.picasoserial.screengraphics.TextAndString;

public class FourDSystemsLCD implements Display {
	private LCD lcd = new LCD("/dev/ttyAMA0");
	private TextAndString textAndString = lcd.textAndString();
	
	public FourDSystemsLCD()  {	
		clear();
	}
	
	/* (non-Javadoc)
	 * @see com.onk.grp2.WindTurbine.driver.Display#clear()
	 */
	@Override
	public void clear() {	
		
		try {
			lcd.graphics().clearScreen();
		} catch (SerialPortException | SerialPortTimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.onk.grp2.WindTurbine.driver.Display#putText(java.lang.String)
	 */
	@Override
	public void putText(String text) {
		try {
			clear();
			textAndString.putString(text);
		} catch (SerialPortException | SerialPortTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
