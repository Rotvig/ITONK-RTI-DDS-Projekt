package com.onk.grp2.Environment.main;

import com.onk.grp2.WindTurbine.utilities.WindTurbinePublisher;
import com.onk.grp2.WindTurbine.utilities.WindTurbineSubscriber;

public class EnvironmentMain {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }      
        
        WindTurbinePublisher publisher = WindTurbinePublisher.INSTANCE;        
        publisher.Initialize(2, "WindTurbineStatus");
        WindTurbineSubscriber subscriber = WindTurbineSubscriber.INSTANCE;
        subscriber.Initialize("Wind", 1);
        
        (new Thread(new EnvironmentControlImpl(publisher, subscriber))).start();    

    }
}
