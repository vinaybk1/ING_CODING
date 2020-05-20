package com.ing.kata.banking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.kata.banking.controller.BankingController;
import com.ing.kata.banking.execption.BankingException;
import com.ing.kata.banking.model.Transaction;
import com.ing.kata.banking.repository.BankingRepository;

@Service
public class BankingService {
	
	Logger logger = LoggerFactory.getLogger(BankingService.class);

	@Autowired
	private BankingRepository repository;

	public Double getBalance(long accountNumber) throws BankingException {
		Optional<Transaction> transact = repository.getBalanceTransactions(accountNumber).stream().findFirst();
		if(transact.isPresent()){
			return transact.get().getBalance();
		}else{
			throw new BankingException("INVALID REQUEST", "Account Number Does not exist");
		}
	}

	public Transaction depositAmount(Transaction transaction) {
		transaction.setTransactionType("DEPOSIT");
		transaction.setStatus("SUCCESS");
		double balance = 0;
		try {
			balance = getBalance(transaction.getAccountNumber());
		} catch (BankingException e) {
			logger.error("Error while fetching balance :: "+e.getMessage());
			if(e.getErrMsg().equalsIgnoreCase("Account Number Does not exist")){
				logger.debug("Account number does not exist. First Transaction");
			}
		}
		
		transaction.setBalance(transaction.getAmount()+balance);
	
		return repository.save(transaction);
	}

	public Transaction withdrawAmount(Transaction transaction) throws BankingException {
		transaction.setTransactionType("WITHDRAW");
		transaction.setStatus("SUCCESS");
		
		double balance = getBalance(transaction.getAccountNumber());
			if(transaction.getAmount()>balance || balance==0){
				throw new BankingException("INVALID REQUEST", "No sufficient balance");
			}else{
				transaction.setBalance(balance - transaction.getAmount());
			}
		
		return repository.save(transaction);
	}

	public List<Transaction> getTransactions(long accountNumber) {	
		return repository.getTransactions(accountNumber);
	}

}
