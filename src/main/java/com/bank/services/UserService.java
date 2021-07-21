package com.bank.services;

import java.time.LocalDate;
import com.bank.beans.User;
import com.bank.data.userDAO;

public class UserService {
	public userDAO ud = new userDAO();
	
	public User login (String name, String password) {
		User u = ud.getUser(name, password);
		return u;
	}
	
	public boolean existingUser(String name) {
		boolean userExist = ud.checkUser(name);
		return userExist;
	}
	
	public void register(String username, String password, String fname, String lname, String email, LocalDate birthday) {
		ud.addUser(username, password, fname, lname, email, birthday);
	}
}
