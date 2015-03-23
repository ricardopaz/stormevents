package com.stormevents.dao;

import com.stormevents.entities.User;
import com.stormevents.exceptions.ModelException;

public class TestConnectionDao {
	
	public static void main(String[] args) {
		UserModel dao = new UserModel();
		User user = new User();
		user.setName("Ricardo");
		user.setEmail("ricardo.paz@hotmail.com");
		user.setPassword("123");
		user.setRole("Admin");
		
		try {
			dao.save(user);
			
			System.out.println("Olá " + dao.getByID(1).getName());
			
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
