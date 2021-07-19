package com.bank.services;

import java.time.LocalDate;
import com.bank.beans.User;
import com.bank.data.userDAO;

public class UserService {
	public userDAO ud = new userDAO();
	
	public User login (String name) {
		User u = ud.getUser(name);
		return u;
	}
	
	public void register(String username, String password, String fname, String lname, LocalDate birthday) {
		
	}
}
