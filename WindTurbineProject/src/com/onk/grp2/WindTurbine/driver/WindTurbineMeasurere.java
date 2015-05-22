package com.onk.grp2.WindTurbine.driver;

import com.onk.grp2.Common.utilities.EnvironmentReceiver;
import com.onk.grp2.WindTurbine.control.WindTurbineEvent;

public interface WindTurbineMeasurere extends EnvironmentReceiver {
	public void addWindTurbineEventListener(WindTurbineEvent event);
	public void run();

}
