package com.bank.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bank.beans.CheckingAccount;
import com.bank.beans.LoanApplication;
import com.bank.beans.User;
import com.bank.data.userDAO;

public class UserService {
	public userDAO ud = new userDAO();
	public static LinkedList<LoanApplication> loans = new LinkedList<LoanApplication>();
	
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
	
	
	public void addLoanApplication(User u, long amount) {
		loans.add(new LoanApplication(loans.size(), amount,u));
	}
	
	public void acceptLoan(LoanApplication la) {
		la.setLoanApproved(true);
		la.getU().loanApproved = true;
		//la.getU().loanSent = true;
	}

	public void printLoanList() {
		for(LoanApplication loan : loans) {
			System.out.println("ID: " + loan.getId() + "\tUsername: " + loan.getU().getUsername() + "\tAmount Requested: $" + loan.getAmountRequested() + "\tApproved: " + loan.isLoanApproved());
		}
		System.out.println();
	}
	
	public boolean checkAlreadyApproved(LoanApplication la) {
		if(la.isLoanApproved())
			return true;
		else
			return false;
	}
	
	public LoanApplication getLoanById(int id) {
		return loans.get(id);
	}
	
	//Set new accounts into the DAO
	public void updateAccounts(User u) {
		u.setCheckingAccounts(u.getCheckingAccounts());
		u.setSavingsAccounts(u.getSavingsAccounts());
		ud.writeToFile();
	}
}
