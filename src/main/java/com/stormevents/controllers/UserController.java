package com.stormevents.controllers;

import java.io.Serializable;

import com.stormevents.dao.UserModel;
import com.stormevents.entities.User;
import com.stormevents.exceptions.ControllerException;
import com.stormevents.exceptions.ModelException;
import com.stormevents.util.StormEventsLogger;

public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;
	private StormEventsLogger logger = StormEventsLogger
			.getLogger(UserController.class);
	private UserModel userModel;
	
	
	protected UserController(UserModel userModel){
		this.userModel = userModel;
	}

	public UserController() throws ControllerException {
		logger = StormEventsLogger.getLogger(UserController.class);
		userModel = new UserModel();
	}

	/**
	 * Saves a motoplay user into the database.
	 * 
	 * @param user
	 * @throws ModelException
	 */
	public void saveUser(User user) throws ControllerException {
		try {
			logger.debug("Saving a new user: " + user.getEmail() + " as "
					+ user.getRole());

			userModel.save(user);
		} catch (ModelException e) {
			throw new ControllerException(e);
		}
	}

	/**
	 * Returns a stormevents user from DB from email address passed as argument.
	 * 
	 * @param userEmail
	 * @return A reference of stormeventsUser
	 * @throws ControllerException
	 */
	public User getUser(String email) throws ControllerException {
		try {
			User user = userModel.userByEmail(email);
			return user;
		} catch (ModelException e) {
			throw new ControllerException(e);
		}
	}

	/**
	 * Verify if the user is a storm user and login in system
	 * 
	 * @param userEmail
	 * @return A reference of stormeventsUser
	 * @throws ControllerException
	 */
	public boolean loginUser(User user) throws ControllerException {
		try {
			User userLogged = userModel.userByEmail(user.getEmail());
			if(userLogged != null){
				if(user.getPassword().equals(userLogged.getPassword())){
					return true;
				}
			}
			return false;
		} catch (ModelException e) {
			throw new ControllerException(e);
		}
	}

}
