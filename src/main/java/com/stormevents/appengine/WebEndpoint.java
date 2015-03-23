package com.stormevents.appengine;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.stormevents.entities.User;
import com.stormevents.network.GenericResponse;
import com.stormevents.network.OK;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/

@Api(name = "stormEventsApi",
     version = "v1",
     namespace = @ApiNamespace(ownerDomain = "stormevents.com",
                                ownerName = "StormEvents",
                                packagePath=""))
public class WebEndpoint {
	
	/** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public GenericResponse sayHi(@Named("name") String name) {
    	User user = new User();
    	user.setName("Hi, " + name);

        return new OK(user);
    }
    
}
