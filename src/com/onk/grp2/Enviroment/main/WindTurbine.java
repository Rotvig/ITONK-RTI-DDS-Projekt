package com.onk.grp2.main;

import com.onk.grp2.dds.Publisher;
import com.onk.grp2.dds.Subscriber;

public class WindTurbine {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }      
        
        Publisher publisher = Publisher.INSTANCE;        
        publisher.Initialize(2, "WindTurbineStatus");
        Subscriber subscriber = Subscriber.INSTANCE;
        subscriber.Initialize("Wind", 1);
        
        (new Thread(new WindTurbineControlImpl(publisher, subscriber))).start();    

    }
}
