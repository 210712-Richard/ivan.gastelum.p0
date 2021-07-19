package com.bank.menu;

import java.util.Scanner;

import com.bank.beans.User;
import com.bank.services.UserService;

public class Menu {
	private UserService us = new UserService();
	private User loggedUser = null;
	
	
	public void start() {
		//Function to start the menu for either logging in or create an account
	}
	
	private int customerMenu() {
		return 1;//Function to display after a customer type user logs in
	}
	
	private int adminMenu() {
		return 1;//Function to display after admin type user log in
	}
	
	private int select() {
		return 1;//Selects an input
	}
}
