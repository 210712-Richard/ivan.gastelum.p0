package com.bank.beans;

import java.io.Serializable;
import java.util.Objects;

public class SavingsAccount implements Serializable {
	
	private static final long serialVersionUID = 65487931l;
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
		SavingsAccount other = (SavingsAccount) obj;
		return Objects.equals(balance, other.balance) && id == other.id;
	}

	@Override
	public String toString() {
		return "SavingsAccount [balance=" + balance + ", id=" + id + "]";
	}
}
