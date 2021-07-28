package com.bank.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bank.beans.LoanApplication;
import com.bank.beans.User;

public class LoanDAO {
	private static String filename = "loans.dat";
	public static List<LoanApplication> loans;
	
	static {
		DataSerializer<LoanApplication> ls = new DataSerializer<LoanApplication>();
		loans = ls.readObjectsFromFile(filename);
		
		if(loans==null) {
			loans = new ArrayList<LoanApplication>();
			loans.add(new LoanApplication(loans.size(),5000l, new User(100, "rrick", "987654", "Rick", "Sanchez", "rrick@example", LocalDate.of(1992, 8, 13))));
			loans.add(new LoanApplication(loans.size(),5000l, new User(101, "jjohn", "987654", "John", "Doe", "rrick@example", LocalDate.of(1992, 8, 13))));
			loans.add(new LoanApplication(loans.size(),5000l, new User(102, "tthomas", "987654", "Thomas", "Phillips", "rrick@example", LocalDate.of(1992, 8, 13))));
			ls.writeObjectsToFile(loans, filename);
		}
	}

	public LoanApplication addLoan(User u, long amount) {
		LoanApplication la = new LoanApplication(loans.size(),amount,u);
		loans.add(la);
		writeToFile();
		return la;
	}
	
	public void printLoanList() {
		for(LoanApplication loan : loans) {
			System.out.println("ID: " + loan.getId() + "\tUsername: " + loan.getU().getUsername() + "\t\tAmount Requested: $" + loan.getAmountRequested() + "\t\tApproved: " + (loan.isLoanApproved()? "Yes": "No"));
		}
		System.out.println();
	}

	public LoanApplication getLoan(int id) {
		return loans.get(id);
	}
	
	public void writeToFile() {
		new DataSerializer<LoanApplication>().writeObjectsToFile(loans, filename);
	}

	public int getSize() {
		return loans.size();
	}
	
	public List<LoanApplication> getLoanApplications(){
		return loans;
	}
}
