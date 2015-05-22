package com.onk.grp2.Environment.main;

import com.onk.grp2.Common.utilities.EnvironmentPublisher;
import com.onk.grp2.Environment.driver.EnvironmentMeasurereImpl;
import com.onk.grp2.Evironment.control.EnvironmentControl;
import com.onk.grp2.Evironment.control.EnvironmentControlImpl;

public class EnvironmentMain {
	public static void main(String[] args) {
		// --- Get domain ID --- //
		int domainId = 0;

		if (args.length >= 1) {
			domainId = Integer.valueOf(args[0]).intValue();
		}

		EnvironmentPublisher publisher = EnvironmentPublisher.INSTANCE;
		publisher.Initialize(domainId, "EnvironmentStatus");

		EnvironmentControl environmentControl = new EnvironmentControlImpl(
				publisher, EnvironmentMeasurereImpl.INSTANCE);
		
		environmentControl.run();

		for (;;)
			;

	}
}
