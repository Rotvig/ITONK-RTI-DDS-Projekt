package com.onk.grp2.main;

import com.onk.grp2.dds.WindTurbine;

public interface WindTurbineEvent {
	public void SendEvent(WindTurbine status);
}