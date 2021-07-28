package com.bank.services;

import java.util.List;

import com.bank.beans.LoanApplication;
import com.bank.beans.User;
import com.bank.data.LoanDAO;

public class LoanService {
	public LoanDAO ld = new LoanDAO();
	
	
	public LoanApplication registerLoan(User u, long amount) {
		LoanApplication la = ld.addLoan(u, amount);
		u.loanSent = true;
		u.loanAmount = amount;
		return la;
	}
	
	public void viewLoans() {
		ld.printLoanList();
	}
	
	public void acceptLoan(LoanApplication la) {
		la.setLoanApproved(true);
		la.getU().loanApproved = true;
		ld.writeToFile();
	}
	
	public boolean checkAlreadyApproved(LoanApplication la) {
		if(la.isLoanApproved())
			return true;
		else
			return false;
	}
	
	public LoanApplication getLoanById(int id) {
		return ld.getLoan(id);
	}
	
	public int getSize() {
		return ld.getSize();
	}
	
	public List<LoanApplication> getList(){
		return ld.getLoanApplications();
	}
}
