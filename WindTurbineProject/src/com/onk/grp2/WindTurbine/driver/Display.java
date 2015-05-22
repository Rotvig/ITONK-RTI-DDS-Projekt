package com.onk.grp2.WindTurbine.driver;

import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import com.darkprograms.picasoserial.LCD;
import com.darkprograms.picasoserial.screengraphics.TextAndString;

public class Display {
	private LCD lcd = new LCD("/dev/ttyAMA0");
	
	public Display()  {
	
			TextAndString textAndString = lcd.textAndString();
			
			try{
				textAndString.putString("Hello World");
			} catch(SerialPortException e) {
				
			} catch (SerialPortTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
