package com.onk.grp2.Environment.main;

import com.onk.grp2.Common.utilities.EnvironmentPublisher;
import com.onk.grp2.Common.utilities.EnvironmentSubscriber;

public class EnvironmentMain {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }      
        
        EnvironmentPublisher publisher = EnvironmentPublisher.INSTANCE;        
        publisher.Initialize(domainId, "EnvironmentStatus");
        EnvironmentSubscriber subscriber = EnvironmentSubscriber.INSTANCE;
        subscriber.Initialize("WindTurbineStatus", domainId);
        
        (new Thread(new EnvironmentControlImpl(publisher, subscriber))).start();    

    }
}
