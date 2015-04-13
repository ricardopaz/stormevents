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
	
    /** 
     * Login the user
     * @author ricardo
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
     * @author ricardo
     * @throws UnsupportedEncodingException 
     */
    @ApiMethod(name = "user.faceLogin")
    public GenericResponse loginUserbyFacebook(@Named("idFacebook") String idFacebook,
    											@Named("name") String name,
    											@Named("email") String email,
    											@Named("gender") String gender,
    											@Named("picture") String picture) throws UnsupportedEncodingException {
    	try {
			return new OK(userController.loginUserbyFacebook(idFacebook,name,email,gender,picture));
		} catch (ControllerException e) {
			e.printStackTrace();
			return Error.build(e);
		}
    }
    
    /** 
     * Get user by email
     * @author ricardo
     * @throws UnsupportedEncodingException 
     */
    @ApiMethod(name = "user.byemail")
    public GenericResponse getUserByEmail(@Named("email") String email) throws UnsupportedEncodingException {
        	try {
				return new OK(userController.getUser(URLDecoder.decode(email,"UTF-8")));
			} catch (ControllerException e) {
				logger.error("A error is thrown when trying to get user by email --- "
						+ e.getMessage());
				return Error.build(e);
			}
    }
    
    /** 
     * New user
     * @author ricardo
     * @throws UnsupportedEncodingException 
     */
    @ApiMethod(name = "new.user")
    public GenericResponse newUser(@Named("name") String name, @Named("email") String email, @Named("password") String password, @Named("gender") String gender) throws UnsupportedEncodingException {
        	try {
        		int newUser = userController.newUser(name, email, password, gender);
        		if(newUser == 1){
        			return new OK();
        		}else if(newUser == 2000){
        			return new Error(2000,"");
        		}else{
        			return new Error();
        		}
			} catch (ControllerException e) {
				logger.error("A error is thrown when trying to save new user --- "
						+ e.getMessage());
				return Error.build(e);
			}
    }
    
}
