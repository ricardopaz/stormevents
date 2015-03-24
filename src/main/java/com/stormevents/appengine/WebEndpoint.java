package com.stormevents.appengine;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
     * @throws UnsupportedEncodingException 
     */
    @ApiMethod(name = "user.login")
    public GenericResponse loginUser(@Named("email") String email, @Named("password") String password) throws UnsupportedEncodingException {
    	User user = new User();
    	user.setEmail(URLDecoder.decode(email,"UTF-8"));
    	user.setPassword(password);

        try {
        	User userLogged = userController.loginUser(user);
        	return new OK(userLogged);
		} catch (ControllerException e) {
			logger.error("A error is thrown when trying to login: "
					+ e.getMessage());
			return Error.build(e);
		}
    }
    
    /** 
     * Login the user by facebook
     * @throws UnsupportedEncodingException 
     */
    @ApiMethod(name = "user.faceLogin")
    public GenericResponse loginUserbyFacebook(@Named("idFacebook") String idFacebook,
    											@Named("name") String name,
    											@Named("email") String email,
    											@Named("gender") String gender,
    											@Named("picture") String picture) throws UnsupportedEncodingException {
    	try {
    		User user = userController.verifyAccountFacebook(idFacebook);
	    	if(user.getId() != null){
	    		return new OK(user);
	    	}else{
	    		user = new User();
	        	user.setEmail(URLDecoder.decode(email,"UTF-8"));
	        	user.setIdFacebook(idFacebook);
	        	user.setName(URLDecoder.decode(name,"UTF-8"));
	        	user.setGender(gender);
	        	user.setPicture(URLDecoder.decode(picture,"UTF-8"));
	        	user.setRole("user");
	        	
	        	userController.saveUser(user);
	        	return new OK(user);
	    	}
		} catch (ControllerException e) {
			logger.error("A error is thrown when trying to login in facebook: "
					+ e.getMessage());
			return Error.build(e);
		}
    }
    
}
