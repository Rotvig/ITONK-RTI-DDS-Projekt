package onk.grp2.WindTurbine.main;

import onk.grp2.WindTurbine.utilities.WindTurbinePublisher;
import onk.grp2.WindTurbine.utilities.WindTurbineSubscriber;

public class WindTurbineMain {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }
        
        WindTurbinePublisher publisher = WindTurbinePublisher.INSTANCE;        
        publisher.Initialize(domainId, "WindTurbineStatus");
        WindTurbineSubscriber subscriber = WindTurbineSubscriber.INSTANCE;
        subscriber.Initialize("EnvironmentStatus", domainId);
        
        (new Thread(new WindTurbineControlImpl(publisher, subscriber))).start();    

    }
}
