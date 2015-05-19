package com.onk.grp2.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.onk.grp2.dds.WindTurbine;

public enum WindTurbineMeasurereImpl implements WindTurbineMeasurere,
		ActionListener {

	INSTANCE;

	private ArrayList<WindTurbineEvent> windTurbineEventListeners = new ArrayList<>();
	private WindTurbine status = new WindTurbine();

	@Override
	public void run() {
		status.bladePitch = (float) 60.2;
		status.id = 2;
		status.orientation = (float) 200.2;
		status.production = (float) 500.0;
		status.RPM = (float) 30.2;
		status.temperature = (float) 30.2;
		
		int delay = 1000; // milliseconds
		ActionListener taskPerformer = this;

		new Timer(delay, taskPerformer).start();

	}

	@Override
	public void addWindTurbineEventListener(WindTurbineEvent event) {
		if (event != null) {
			this.windTurbineEventListeners.add(event);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		status.bladePitch += 2;
		status.orientation += 3;
		status.RPM += 20;
		status.production += 5;
		status.temperature += 6;
		
		for (WindTurbineEvent windTurbineEvent : windTurbineEventListeners) {
			windTurbineEvent.SendEvent(status);
		}

	}

}
