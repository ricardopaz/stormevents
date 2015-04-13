package com.stormevents.controllers;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.stormevents.dao.UserModel;
import com.stormevents.entities.User;
import com.stormevents.exceptions.ControllerException;
import com.stormevents.exceptions.ModelException;
import com.stormevents.network.Error;
import com.stormevents.network.OK;
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
	public User loginUser(User user) throws ControllerException {
		try {
			User userLogged = userModel.userByEmail(user.getEmail());
			if(userLogged != null){
				if(user.getPassword().equals(userLogged.getPassword())){
					return userLogged;
				}
			}
			return null;
		} catch (ModelException e) {
			throw new ControllerException(e);
		}
	}

	public User verifyAccountFacebook(String idFacebook) throws ControllerException {
		try {
			User user = new User();
			user = userModel.getAccountFacebookById(idFacebook);
			return user;
		} catch (ModelException e) {
			throw new ControllerException(e);
		}
	}
	
	public User loginUserbyFacebook(String idFacebook,String name,String email,String gender,String picture) throws UnsupportedEncodingException, ControllerException{
		User user = verifyAccountFacebook(idFacebook);
		if(user != null){
			return user;
		}else{
			user = new User();
			user.setEmail(URLDecoder.decode(email,"UTF-8"));
			user.setIdFacebook(idFacebook);
			user.setName(URLDecoder.decode(name,"UTF-8"));
			user.setGender(gender);
			user.setPicture(URLDecoder.decode(picture,"UTF-8"));
			user.setRole("user");
			
			saveUser(user);
			return user;
		}
	}
	
	public int newUser(String name,String email,String password,String gender) throws ControllerException, UnsupportedEncodingException{
		String emailUser = URLDecoder.decode(email,"UTF-8");
		User existsUser = getUser(emailUser);
		if(existsUser==null){
			User newUser = new User(); 
			newUser.setName(URLDecoder.decode(name,"UTF-8"));
			newUser.setEmail(emailUser);
			newUser.setPassword(URLDecoder.decode(password,"UTF-8"));
			newUser.setRole("user");
			String genderUser = URLDecoder.decode(gender,"UTF-8");
			newUser.setGender(genderUser);
			if(genderUser.equals("female")){
				newUser.setPicture("UI/templateStormSystem/img/user-female.png");
			}else{
				newUser.setPicture("UI/templateStormSystem/img/user-male.png");
			}
			saveUser(newUser);
			return 1;
		}else{
			return 2000;
		}
	}

}
