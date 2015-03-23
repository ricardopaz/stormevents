/*
 * Copyright Motorola 2014. 
 * 
 * Description: Model class that handles information about motoplay report.
 * 
 * Author: eliasq@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Description  									  Date 
 * =================         ============================================     ===========
 * felipec					 getUserEmails method							  Dec 24, 2014
 * felipec 					 added search method							  Dec 31, 2014
 * tsousa 					 adjust on search query							  Jan 21, 2015
 */
package com.stormevents.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stormevents.entities.User;
import com.stormevents.exceptions.ModelException;

public class UserModel extends BasicModel<User> {
	
	public static final String USER_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email";

	/**
	 * Query that returns an user according to his email address
	 * 
	 * @param version
	 * @return user that matches the query
	 */
	public User userByEmail(String email) throws ModelException{
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		List<User> users = query(USER_BY_EMAIL, params);
		if(!users.isEmpty())
			return users.get(0);
		return null;
	}
}
