package com.onk.grp2.Environment.driver;

import com.onk.grp2.Evironment.control.EnvironmentEvent;

public interface EnvironmentMeasurere extends Runnable {
	public void addEnvironmentEventListener(EnvironmentEvent event);

}
