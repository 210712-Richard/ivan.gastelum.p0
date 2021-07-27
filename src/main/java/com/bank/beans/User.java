package com.bank.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = -6426075925303078798L;
	private Integer id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private LocalDate birthday;
	private UserType type;

	//Array containing a List of checking accounts and/or saving accounts
	private List<CheckingAccount> checkingAccounts;
	private List <SavingsAccount> savingsAccounts;
	
	public long loanAmount;
	public boolean loanSent = false;
	public boolean loanApproved = false;
	
	public User() {
		super();
		this.type = UserType.CUSTOMER;
	}
	
	public User(Integer id, String username, String password, String fname, String lname, String email, LocalDate birthday) {
		this();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.birthday = birthday;
		checkingAccounts = new ArrayList<CheckingAccount>();
		savingsAccounts = new ArrayList<SavingsAccount>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}
	public void setCheckingAccounts(List l) {
		this.checkingAccounts = l;
	}
	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}
	public void setSavingsAccounts(List<SavingsAccount> list) {
		this.savingsAccounts = list;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((checkingAccounts == null) ? 0 : checkingAccounts.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((savingsAccounts == null) ? 0 : savingsAccounts.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (checkingAccounts == null) {
			if (other.checkingAccounts != null)
				return false;
		} else if (!checkingAccounts.equals(other.checkingAccounts))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (savingsAccounts == null) {
			if (other.savingsAccounts != null)
				return false;
		} else if (!savingsAccounts.equals(other.savingsAccounts))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", fname=" + fname + ", lname=" + lname + ", checkingAccounts="
				+ checkingAccounts + ", savingsAccounts=" + savingsAccounts + "]";
	}
	
}
