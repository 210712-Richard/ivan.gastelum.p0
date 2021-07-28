package com.bank.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bank.beans.LoanApplication;
import com.bank.beans.User;
import com.bank.beans.UserType;
import com.bank.services.LoanService;

import io.javalin.http.Context;

public class LoanController {
	private static Logger log = LogManager.getLogger(LoanController.class);
	private LoanService ls = new LoanService();
	
	public void approveLoan(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		String id = ctx.pathParam("id");
		int loan = Integer.parseInt(id);
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		//Verifying if user is an ADMIN
		if(!loggedUser.getType().equals(UserType.ADMIN)) {
			ctx.status(403);
			return;
		}
		// Update status in the loan
		ls.acceptLoan(ls.getLoanById(loan));
		log.trace("Loan has been approved");
		ctx.status(201);
	}
	
	public void viewLoanApplications(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		String id = ctx.pathParam("id");
		int loan = Integer.parseInt(id);
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		
		//Verifying if user is an ADMIN
		if(!loggedUser.getType().equals(UserType.ADMIN)) {
			ctx.status(403);
			return;
		}
		
		//Otherwise we are all set!
		ctx.json(ls.getList());
	}
}
