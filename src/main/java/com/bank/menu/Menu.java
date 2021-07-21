package com.bank.menu;

import java.time.LocalDate;
import java.util.Scanner;

import com.bank.beans.CheckingAccount;
import com.bank.beans.SavingsAccount;
import com.bank.beans.User;
import com.bank.services.UserService;
import com.bank.util.SingletonScanner;

public class Menu {
	
	//private static final Logger log = LogManager.getLogger(Menu.class);
	
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		//Function to start the console app
		mainLoop: while(true) {
			switch(startMenu()) {
				case 1:
					//Log in
					System.out.println("Please enter your username: ");
					String username = scan.nextLine();
					System.out.println("Please enter your password: ");
					String password = scan.nextLine();
					User u = us.login(username,password);
					if (u==null) {
						//log.warn("Unsuccessful login attempt: "+ username);
						System.out.println("Username and/or password are incorrect. Please try again.");
					}else {
						loggedUser = u;
						//System.out.println("Welcome back: "+u.getUsername());
						switch(loggedUser.getType()) {
							case ADMIN:
								adminMenu();
								break;
							case CUSTOMER:
								customerMenu();
								break;
						}
					}
					break;
				case 2:
					//Register
					register();
					break;
				case 3:
					//Quit
					System.out.println("Goodbye!");
					break mainLoop;
				default:
					System.out.println("Not a valid selection. Please try again.");
			}
		}
	}
	
	private void register() {
		String username = null;
		String password = null;
		String fname = null;
		String lname = null;
		String email = null;
		int year = -1;
		int month = -1;
		int day = -1;
		
		registerLoop: while(true) {
			if(username==null) {
				System.out.println("Enter username:");
				username = scan.nextLine();
			}
			boolean exist = us.existingUser(username);
			if(exist) {
				System.out.println("The username already exist. Try again");
				username = null;
				continue;
			}
			System.out.println("Enter password:");
			password = scan.nextLine();
			System.out.println("Enter First name:");
			fname = scan.nextLine();
			System.out.println("Enter Last name:");
			lname = scan.nextLine();
			System.out.println("Enter your email:");
			email = scan.nextLine();
			
			
			//Entering birthday
			while(year==-1 || month==-1 || day==-1) {
				System.out.println("Please enter your birthday");
				System.out.println("\tEnter the year:");
				year = Integer.parseInt(scan.nextLine());
				System.out.println("\tEnter the month:");
				month = Integer.parseInt(scan.nextLine());
				System.out.println("\tEnter the day:");
				day = Integer.parseInt(scan.nextLine());
				if(!validBirthday(year,month,day)) {
					System.out.println("The birthday is not valid. Try again");
					year = -1;
					month = -1;
					day = -1;
				}
			}
			break;	
		}
		LocalDate birthday = LocalDate.of(year, month, day);
		//Adding the new registered user into the DAO
		us.register(username, password, fname, lname,email,birthday);
	}

	private boolean validBirthday(int year, int month, int day) {
		if(year>1000 && year<2022) {
			if(month>0 && month<=12) {
				if(day>0 && day<32) {
					return true;
				}
			}
		}
		return false;
	}

	private int startMenu() {
		//Main menu for either logging in or create an account
		System.out.println("Welcome to Goldmine Bank!");
		System.out.println("Please select one of the options:");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		int selection = select();
		
		return selection;
	}
	
	private void customerMenu() {
		//Function to display after a customer type user logs in
		System.out.println("Welcome " + loggedUser.getFname() + " " + loggedUser.getLname() + "!");
		System.out.println("MENU BAR");
		System.out.println("\t1. Create an account (checking or savings)");
		System.out.println("\t2. View accounts");
		System.out.println("\t2. Deposit into an account");
		System.out.println("\t3. Apply for a loan");
		System.out.println("\t4. Log Out");
		int option = select();
		
		customerLoop: while(true) {
			switch(option) {
				case 1:
					createAccount();
					break;
				case 2:
					viewAccounts();
					break;
				case 3:
					deposit();
					break;
				case 4:
					applyForLoan();
					break;
				case 5:
					System.out.println("You have logged out.");
					break customerLoop;
				default:
					System.out.println("Not a valid selection. Try again.");
			}
		}
		
	}
	
	private void viewAccounts() {
		System.out.println("Checking accounts: ");
		if(loggedUser.getCheckingAccounts().size()==0) {
			System.out.println("\tYou don't have an existing checkings account");
		}else {
			for(CheckingAccount account: loggedUser.getCheckingAccounts()) {
				System.out.println("\tAccount:\t" + account.);
			}
		}
	}

	private void applyForLoan() {
		// TODO Auto-generated method stub
		
	}

	private void deposit() {
		// TODO Auto-generated method stub
		
	}

	private void createAccount() {
		if(loggedUser.getCheckingAccounts()==null && loggedUser.getSavingsAccounts()==null) {
			loggedUser.getCheckingAccounts().add(new CheckingAccount(1));
			loggedUser.getSavingsAccounts().add(new SavingsAccount(1));
			System.out.println("Great! we have created both a checking and savings accounts!");
		}else {
			createLoop: while(true) {
				System.out.println("Select an option: ");
				System.out.println("\t1. Create Checkings account");
				System.out.println("\t2. Create Savings account");
				System.out.println("\t3. Go back <-");
				int selection = select();
				switch(selection) {
				case 1:
					loggedUser.getCheckingAccounts().add(new CheckingAccount(loggedUser.getCheckingAccounts().size()));
					System.out.println("Great! your new Checkings account has been created!");
					System.out.println("Checking account no. " + loggedUser.getCheckingAccounts().size());
					System.out.println();
					break;
				case 2:
					loggedUser.getSavingsAccounts().add(new SavingsAccount(loggedUser.getSavingsAccounts().size()));
					System.out.println("Great! your new Checkings account has been created!");
					System.out.println("Checking account no. " + loggedUser.getSavingsAccounts().size());
					System.out.println();
				case 3:
					break createLoop;
				}
			}
		}
		
	}

	private void adminMenu() {
		//Function to display after admin type user log in
		System.out.println("ADMIN: " + loggedUser.getFname() + " " + loggedUser.getLname());
	}
	
	private int select() {
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		//log
		return selection;
	}
}
