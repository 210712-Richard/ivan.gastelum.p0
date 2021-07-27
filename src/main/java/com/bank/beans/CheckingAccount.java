package com.bank.beans;

import java.io.Serializable;
import java.util.Objects;

public class CheckingAccount implements Serializable {
	
	private static final long serialVersionUID = 654987313l;
	private Long balance = 0l;
	private int id;

	public CheckingAccount(int id){
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
	
	@Override
	public int hashCode() {
		return Objects.hash(balance, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckingAccount other = (CheckingAccount) obj;
		return Objects.equals(balance, other.balance) && id == other.id;
	}

	@Override
	public String toString() {
		return "CheckingAccount [balance=" + balance + ", id=" + id + "]";
	}
	
}
