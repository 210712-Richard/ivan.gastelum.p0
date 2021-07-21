package com.bank.beans;

public class SavingsAccount {
	private Long balance = 0l;
	private int id;
	
	public SavingsAccount(int id) {
		this.id = id;
	}
	
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
