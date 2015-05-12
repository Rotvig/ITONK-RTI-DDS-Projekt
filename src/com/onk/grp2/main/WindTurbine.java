package com.onk.grp2.main;

import com.onk.grp2.dds.Publisher;

public class WindTurbine {
	public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]).intValue();
        }      
        
        Publisher publisher = new Publisher(domainId);
        publisher
    }
}
