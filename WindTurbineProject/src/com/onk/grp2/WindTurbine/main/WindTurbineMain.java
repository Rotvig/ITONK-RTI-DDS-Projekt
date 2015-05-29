package com.onk.grp2.WindTurbine.main;

import com.onk.grp2.Common.utilities.EnvironmentSubscriber;
import com.onk.grp2.Common.utilities.WindTurbinePublisher;
import com.onk.grp2.WindTurbine.control.WindTurbineControl;
import com.onk.grp2.WindTurbine.control.WindTurbineControlImpl;
import com.onk.grp2.WindTurbine.driver.Display;
import com.onk.grp2.WindTurbine.driver.FourDSystemsLCD;
import com.onk.grp2.WindTurbine.driver.WindTurbineMeasurere;
import com.onk.grp2.WindTurbine.driver.WindTurbineMeasurereImpl;

public class WindTurbineMain {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if(args.length < 1) {
        	throw new IllegalArgumentException("Specify a wind turbine ID");
        }
        
        if (args.length >= 2) {
            domainId = Integer.valueOf(args[1]).intValue();
        }
        
        //Create and initialize publisher
        WindTurbinePublisher publisher = WindTurbinePublisher.INSTANCE;        
        publisher.Initialize(domainId, "WindTurbineStatus");
        
        //Create and initialize subscriber
        EnvironmentSubscriber subscriber = EnvironmentSubscriber.INSTANCE;
        WindTurbineMeasurere measurere = WindTurbineMeasurereImpl.INSTANCE;
        subscriber.Initialize("EnvironmentStatus", domainId, measurere);
        
        //Create and start the wind turbine controller
        WindTurbineControl controller =  new WindTurbineControlImpl(publisher, measurere, new FourDSystemsLCD());
        controller.run(Integer.valueOf(args[0]).intValue());
        
        for(;;);

    }
}
