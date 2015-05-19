package com.onk.grp2.Enviroment.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.onk.grp2.dds.Publisher;
import com.onk.grp2.dds.Subscriber;
import com.onk.grp2.dds.WindTurbine;

public class WindTurbineControlImpl implements WindTurbineControl {
	
	private Publisher publisher = null;
	private Subscriber subscriber = null;
	
	public WindTurbineControlImpl(Publisher publisher, Subscriber subscriber) {
		if(publisher == null) {
			throw new IllegalArgumentException("Specify a publisher for the controller to use");
		}
		
		if(subscriber == null) {
			throw new IllegalArgumentException("Specify a subscriber for the controller to use");
		}
		
		this.publisher = publisher;
		this.subscriber = subscriber;
	}

	@Override
	public void run() {
		
		
	}

	@Override
	public void SendEvent(WindTurbine status) {
		publisher.notify(status);
		
	}

}
