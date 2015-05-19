package onk.grp2.Environment.main;

import onk.grp2.Environment.utilities.EnvironmentPublisher;
import onk.grp2.Environment.utilities.EnvironmentSubscriber;

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
