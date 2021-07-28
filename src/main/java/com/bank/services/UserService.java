package com.bank.services;

import java.time.LocalDate;

import com.bank.beans.CheckingAccount;
import com.bank.beans.LoanApplication;
import com.bank.beans.SavingsAccount;
import com.bank.beans.User;
import com.bank.data.userDAO;

public class UserService {
	public userDAO ud = new userDAO();
	//public static LinkedList<LoanApplication> loans = new LinkedList<LoanApplication>();
	
	public User login (String name, String password) {
		User u = ud.getUser(name, password);
		return u;
	}
	
	public boolean existingUser(String name) {
		boolean userExist = ud.checkUser(name);
		return userExist;
	}
	
	public User register(String username, String password, String fname, String lname, String email, LocalDate birthday) {
		return ud.addUser(username, password, fname, lname, email, birthday);
	}
	
	//Set new accounts into the DAO
	public void updateAccounts(User u) {
		u.setCheckingAccounts(u.getCheckingAccounts());
		u.setSavingsAccounts(u.getSavingsAccounts());
		ud.writeToFile();
	}
	
	public User createAccounts(User u) {
		u.getCheckingAccounts().add(new CheckingAccount(1));
		u.getSavingsAccounts().add(new SavingsAccount(1));
		updateAccounts(u);
		return u;
	}
	
	public User addCheckingAccount(User u) {
		u.getCheckingAccounts().add(new CheckingAccount(u.getCheckingAccounts().size()+1));
		updateAccounts(u);
		return u;
	}
	
	public User addSavingsAccount(User u) {
		u.getSavingsAccounts().add(new SavingsAccount(u.getSavingsAccounts().size()+1));
		updateAccounts(u);
		return u;
	}
	
	public User deposit(User u, long money) {
		u.getCheckingAccounts().get(0).setBalance(u.getCheckingAccounts().get(0).getBalance()+money);
		updateAccounts(u);
		return u;
	}

	public void updateInfo(User u) {
		ud.writeToFile();
	}
}
