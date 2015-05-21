package com.onk.grp2.WindTurbine.main;

import com.onk.grp2.Common.utilities.EnvironmentSubscriber;
import com.onk.grp2.Common.utilities.WindTurbinePublisher;
import com.onk.grp2.WindTurbine.control.WindTurbineControlImpl;
import com.onk.grp2.WindTurbine.driver.WindTurbineMeasurere;
import com.onk.grp2.WindTurbine.driver.WindTurbineMeasurereImpl;

public class WindTurbineMain {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }
        
        WindTurbinePublisher publisher = WindTurbinePublisher.INSTANCE;        
        publisher.Initialize(domainId, "WindTurbineStatus");
        EnvironmentSubscriber subscriber = EnvironmentSubscriber.INSTANCE;
        WindTurbineMeasurere measurere = WindTurbineMeasurereImpl.INSTANCE;
        subscriber.Initialize("EnvironmentStatus", domainId, measurere);
        (new Thread(new WindTurbineControlImpl(publisher, measurere))).start();   
        
        for(;;);

    }
}
