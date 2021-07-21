package com.bank.beans;

public class LoanApplication {
	private int id;
	private long amountRequested;
	private boolean loanApproved = false;
	private User u;
	

	public LoanApplication(long amountRequested, User u) {
		this.amountRequested = amountRequested;
		this.u = u;
	}
	
	public LoanApplication(int id, long amountRequested, User u) {
		this.id = id;
		this.amountRequested = amountRequested;
		this.u = u;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getAmountRequested() {
		return amountRequested;
	}
	public void setAmountRequested(long amountRequested) {
		this.amountRequested = amountRequested;
	}
	public boolean isLoanApproved() {
		return loanApproved;
	}
	public void setLoanApproved(boolean loanApproved) {
		this.loanApproved = loanApproved;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}

}
