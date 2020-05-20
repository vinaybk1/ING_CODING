package com.ing.kata.banking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Transaction")
public class Transaction {
	
	public Transaction() {
		super();
	}

	@Column(name = "account_Number")
	private long accountNumber;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "balance")
	private Double balance;
	
	@Column(name="status")
	private String status;
	
	@Column(name = "transaction_Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	
	@Column(name = "type")
	private String transactionType;
	
	@Column(name = "transaction_date")
	@CreationTimestamp
	public Timestamp transactionDate;
	
	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	
	public Double getAmount() {
		return amount;
	}

	public Double getBalance() {
		return balance;
	}

	public String getStatus() {
		return status;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}	

}
