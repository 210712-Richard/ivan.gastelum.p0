package com.bank.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bank.beans.User;
import com.bank.beans.UserType;

public class userDAO {
	//Database Access Object
	private static String filename = "users.dat";
	private static List<User> users;
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
		if(users==null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), "agastelum", "123", "Andres", "Gastelum", "agastelum@example.com", LocalDate.of(1999, 3, 10)));
			users.add(new User(users.size(), "kkenia", "456", "Kenia", "Guerrero", "kkenia@example.com", LocalDate.of(1994, 8, 19)));
			users.add(new User(users.size(), "agarcia", "789", "Alex", "Garcia", "agarcia@example.com", LocalDate.of(1980, 5, 10)));
			users.add(new User(users.size(), "rlomeli", "654", "Rocio", "Lomeli", "rlomeli@example.com", LocalDate.of(1975, 10, 15)));
			User u = new User(users.size(), "igastelum", "123", "Ivan", "Gastelum", "igastelum@example.com", LocalDate.of(1994, 8, 7));
			u.setType(UserType.ADMIN);
			users.add(u);
			ds.writeObjectsToFile(users, filename);
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
