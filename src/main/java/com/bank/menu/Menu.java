package com.bank.menu;

import java.util.Scanner;

import com.bank.beans.User;
import com.bank.services.UserService;
import com.bank.util.SingletonScanner;

public class Menu {
	
	//private static final Logger log = LogManager.getLogger(Menu.class);
	
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		//Function to start the app
		mainLoop: while(true) {
			switch(startMenu()) {
				case 1:
					//Log in
					System.out.println("Please enter your username");
					String username = scan.nextLine();
					User u = us.login(username);
					if (u==null) {
						//log.warn("Unsuccessful login attempt: "+ username);
						System.out.println("The username was not found. Please try again.");
					}else {
						loggedUser = u;
						System.out.println("Welcome back: "+u.getUsername());
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
				case 3:
					//Quit
					System.out.println("Goodbye!");
					break mainLoop;
				default:
					System.out.println("");
			}
		}
	}
	

	private int startMenu() {
		//Main menu for either logging in or create an account
		System.out.println("Welcome to Goldmine Bank!");
		System.out.println("Please select the one of the options:");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		int selection = select();
		
		return selection;
	}
	
	private int customerMenu() {
		return 1;//Function to display after a customer type user logs in
	}
	
	private int adminMenu() {
		return 1;//Function to display after admin type user log in
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
