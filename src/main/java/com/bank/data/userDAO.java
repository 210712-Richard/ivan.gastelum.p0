package com.bank.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bank.beans.User;

public class userDAO {
	//Database Access Object
	private static String filename = "users.dat";
	private static List<User> users;
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
		if(users==null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), ))
		}
	}
	
	public User getUser(String name) {
		for(User user: users) {
			if(user.getUsername().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public void writeToFile() {
		new DataSerializer<User>().writeObjectsToFile(users, filename);
	}
}
