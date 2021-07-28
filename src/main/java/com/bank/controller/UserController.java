package com.bank.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bank.beans.LoanApplication;
import com.bank.beans.User;
import com.bank.services.LoanService;
import com.bank.services.UserService;

import io.javalin.http.Context;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();
	private LoanService ls = new LoanService();
	
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		
		
		User u = ctx.bodyAsClass(User.class);
		log.debug(u.getPassword());
		u = us.login(u.getUsername(), u.getPassword());
		log.debug(u);
		
		if(u!=null) {
			ctx.sessionAttribute("loggedUser", u);
			ctx.json(u);
			return;
		}
		
		ctx.status(401);
	}
	
	public void register(Context ctx) {
		User u = ctx.bodyAsClass(User.class);

		if(!us.existingUser(u.getUsername())) {
			User newUser = us.register(u.getUsername(), u.getPassword(), u.getFname(), u.getLname(),u.getEmail(), u.getBirthday());
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
	}
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void createAccounts(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		//Check if user has not created new accounts
		if(loggedUser.getCheckingAccounts().size()==0 && loggedUser.getSavingsAccounts().size()==0) {
			loggedUser = us.createAccounts(loggedUser);
			log.trace("New checking and savings accoung created");
			log.debug(loggedUser.getCheckingAccounts());
			log.debug(loggedUser.getSavingsAccounts());
			ctx.status(201);
			ctx.json(loggedUser);
		}else {
			ctx.status(405);
			ctx.html("New accounts have been already created.");
			return;
		}
	}
	
	public void createCheckingAccount(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		loggedUser = us.addCheckingAccount(loggedUser);
		log.trace("New checking account created");
		log.debug(loggedUser.getCheckingAccounts());
		ctx.status(201);
		ctx.json(loggedUser);
	}
	
	public void createSavingsAccount(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		loggedUser = us.addSavingsAccount(loggedUser);
		log.trace("New savings account created");
		log.debug(loggedUser.getSavingsAccounts());
		ctx.status(201);
		ctx.json(loggedUser);
	}
	
	public void deposit(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		String amount = ctx.pathParam("amount");
		long money = Long.parseLong(amount);
		
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		if(loggedUser.getCheckingAccounts().size()==0 && loggedUser.getSavingsAccounts().size()==0) {
			log.trace("Accounts do not exist for deposit");
			ctx.status(405);
			ctx.html("There are no accounts existing for deposit");
			return;
		}
		
		if(money>2000) {
			log.trace("Money rejected. Amount exceeds limit per deposit");
			ctx.status(405);
			ctx.html("Money rejected. Amount exceeds limit per deposit");
			return;
		}else {
			loggedUser = us.deposit(loggedUser, money);
			log.trace("Deposit has succesfully been completed");
			ctx.status(201);
			ctx.json(loggedUser);
		}
	}
	
	public void applyForLoan(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		String amount = ctx.pathParam("amount");
		long moneyRequested = Long.parseLong(amount);
		
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		LoanApplication la = ls.registerLoan(loggedUser, moneyRequested);
		us.updateInfo(loggedUser);
		log.trace("Loan application has been sent.");
		ctx.status(201);
		ctx.json(la);
	}
	
	public void depositLoan(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		if(loggedUser.loanApproved) {
			loggedUser.getCheckingAccounts().get(0).setBalance(loggedUser.getCheckingAccounts().get(0).getBalance()+loggedUser.loanAmount);
			loggedUser.loanAmount = 0;
			loggedUser.loanApproved = false;
			loggedUser.loanSent = false;
			us.updateAccounts(loggedUser);
			log.trace("Loan approved has been deposited.");
		}else {
			ctx.html("Loan has not been approved yet.");
		}
	}
	
	public void viewCheckingAccounts(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		ctx.json(loggedUser.getCheckingAccounts());
	}
	
	public void viewSavingsAccounts(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		ctx.json(loggedUser.getSavingsAccounts());
	}
}
