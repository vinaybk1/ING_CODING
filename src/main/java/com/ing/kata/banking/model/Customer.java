package com.ing.kata.banking.model;

public class Customer {
	
	private long customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String address1;
	
	private String address2;
	
	private boolean overdraft;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public boolean isOverdraft() {
		return overdraft;
	}

	public void setOverdraft(boolean overdraft) {
		this.overdraft = overdraft;
	}
	
	

}
