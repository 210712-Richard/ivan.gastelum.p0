package com.bank;

import com.bank.controller.LoanController;
import com.bank.controller.UserController;
import com.bank.menu.Menu;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Driver {

	public static void main(String[] args) {
		
		//Menu menu = new Menu();
		//menu.start();
		
		// Set up Jackson to serialize LocalDates and LocalDateTimes
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		//Starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		
		//Javalin has created a web server for us 
		//and we have to tell Javalin how to handle the requests it receives
		
		//We do this by:
		// app.METHOD(URN, CALL BACK FUNCTION);
		app.get("/", (ctx) -> ctx.json("Hello World"));
		
		UserController uc = new UserController();
		LoanController lc = new LoanController();
		
		// As a user, I can log in
		app.post("/users", uc::login);
		
		// As a user, I can register for an account
		app.put("/users/:username", uc::register);
		
		// As a user, I can log out
		app.delete("/users", uc::logout);
		
		//As a user, I can create new checkings/savings accounts
		app.put("users/:username/newAccount", uc::createAccounts);
		
		//As a user, I can create a checking account
		app.put("users/:username/createChecking", uc::createCheckingAccount);
		
		// As a user, I can Create a savings account
		app.put("users/:username/createSavings", uc::createSavingsAccount);
		
		// As a user, I can deposit no more than $2,000 per deposit into an account
		app.put("users/:username/deposit/:amount", uc::deposit);
		
		// As a user, I can apply for a loan
		app.put("users/:username/loanApplication/:amount", uc::applyForLoan);
		
		//MISSING TEST
		// As an admin, I can view list of loans
		app.get("users/:username/viewLoans", lc::viewLoanApplications);
		
		// As an admin, I can approve a loan
		app.patch("users/:username/approveLoan/:id", lc::approveLoan);
		
		//As a user, I can get deposit my approved loan
		app.put("users/:username/depositLoan", uc::depositLoan);
		
		//As a user, I can view my Checking accounts
		app.get("users/:username/viewCheckingAccounts", uc::viewCheckingAccounts);
		
		//As a user, I can view my Savings accounts
		app.get("users/:username/viewSavingsAccounts", uc::viewSavingsAccounts);
	}

}