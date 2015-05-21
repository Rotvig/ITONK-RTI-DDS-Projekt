package com.onk.grp2.DataConsumer.main;

import com.onk.grp2.Common.utilities.WindTurbineSubscriber;
import com.onk.grp2.DataConsumer.control.DataConsumerControlImpl;

public class DataConsumerMain {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }
        WindTurbineSubscriber subscriber = WindTurbineSubscriber.INSTANCE;
        subscriber.Initialize("WindTurbineStatus", domainId, new DataConsumerControlImpl());
        
        for(;;);

    }
}
