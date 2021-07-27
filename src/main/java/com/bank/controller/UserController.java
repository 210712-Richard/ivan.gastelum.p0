package com.bank.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bank.services.UserService;

import io.javalin.http.Context;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();
	
	public void login(Context ctx) {
		
	}
}
