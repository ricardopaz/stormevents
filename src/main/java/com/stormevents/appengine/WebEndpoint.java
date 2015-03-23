package com.stormevents.appengine;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.stormevents.controllers.UserController;
import com.stormevents.entities.User;
import com.stormevents.exceptions.ControllerException;
import com.stormevents.network.Error;
import com.stormevents.network.GenericResponse;
import com.stormevents.network.OK;
import com.stormevents.util.StormEventsLogger;

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
	
	private UserController userController;
	private StormEventsLogger logger;
	
	public WebEndpoint() throws ControllerException {
		super();
		logger = StormEventsLogger.getLogger(WebEndpoint.class);
		userController = new UserController();
	}
	
	/** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public GenericResponse sayHi(@Named("name") String name) {
    	User user = new User();
    	user.setName("Hi, " + name);

        return new OK(user);
    }
    
    /** 
     * Login the user
     */
    @ApiMethod(name = "user.login")
    public GenericResponse loginUser(@Named("email") String email, @Named("password") String password) {
    	User user = new User();
    	user.setEmail(email);
    	user.setPassword(password);

        try {
			return new OK(userController.loginUser(user));
		} catch (ControllerException e) {
			logger.error("A error is thrown when trying to login: "
					+ e.getMessage());
			return Error.build(e);
		}
    }
    
}
