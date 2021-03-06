package com.onk.grp2.WindTurbine.driver;

import java.util.ArrayList;
import java.util.Random;

import com.onk.grp2.Common.autoGenerated.Environment;
import com.onk.grp2.Common.autoGenerated.WindTurbine;
import com.onk.grp2.WindTurbine.control.WindTurbineEvent;

public enum WindTurbineMeasurereImpl implements WindTurbineMeasurere {

	INSTANCE;

	private ArrayList<WindTurbineEvent> windTurbineEventListeners = new ArrayList<>();
	private WindTurbine status = new WindTurbine();

	public void setId(int id) {
		this.status.id = id;
	}

	@Override
	public void updateEnvironment(Environment windData) {
		if (windData == null) {
			throw new IllegalArgumentException(
					"Please specify some valid Wind Data");
		}

		setOrientation(windData);
		setBladePitch(windData);
		setRPM();
		setProduction();
		measureTemperature();

		for (WindTurbineEvent windTurbineEvent : windTurbineEventListeners) {
			windTurbineEvent.SendEvent(status);

		}

	}

	@Override
	public void run(int id) {
		status.bladePitch = (float) 0.0;
		status.id = id;
		status.orientation = (float) 0.0;
		status.production = (float) 0.0;
		status.RPM = (float) 0.0;
		status.temperature = (float) 0.0;

	}

	@Override
	public void addWindTurbineEventListener(WindTurbineEvent event) {
		if (event != null) {
			this.windTurbineEventListeners.add(event);
		}

	}

	private void measureTemperature() {
		Random random = new Random();
		status.temperature = random.nextFloat() * (float) random.nextInt(15)
				+ 15;
	}

	private void setProduction() {
		status.production = status.RPM * (float) 0.8;
	}

	private void setRPM() {
		status.RPM = status.bladePitch * (float) 2.3;
	}

	private void setBladePitch(Environment windData) {
		status.bladePitch = (float) 0.2 * windData.windSpeed;
	}

	private void setOrientation(Environment windData) {
		if (windData.windDirection <= (float) 180.0) {
			status.orientation = windData.windDirection + (float) 180.0;
		}

		else {
			status.orientation = windData.windDirection - (float) 180.0;
		}
	}

}
