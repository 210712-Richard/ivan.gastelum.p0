package com.bank;

import com.bank.menu.Menu;
import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		/*
		Menu menu = new Menu();
		menu.start();
		*/
		
		//Starts the Javalin framework
		Javalin app = Javalin.create().start(8080);
		
		//Javalin has created a web server for us 
		//and we have to tell Javalin how to handle the requests it receives
		
		//We do this by:
		// app.METHOD(URN, CALL BACK FUNCTION);
		app.get("/", (ctx) -> ctx.json("Hello World"));
	}

}