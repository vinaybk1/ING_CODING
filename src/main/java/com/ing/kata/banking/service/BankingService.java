package com.ing.kata.banking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.kata.banking.model.Transaction;
import com.ing.kata.banking.repository.BankingRepository;

@Service
public class BankingService {

	@Autowired
	private BankingRepository repository;

	public Double getBalance(long accountNumber) {
		Optional<Transaction> transact = repository.getTransactions(accountNumber).stream().findFirst();
		return transact.get().getBalance();
	}

	public Transaction depositAmount(@Valid Transaction transaction) {
		transaction.setTransactionType("DEPOSIT");
		transaction.setStatus("SUCCESS");
		List<Transaction> list = repository.getTransactions(transaction.getAccountNumber());

		Optional<Transaction> transact = list.stream().findFirst();
		
		if(transact.isPresent()){
			transaction.setBalance(transact.get().getBalance() + transaction.getAmount());
		}else{
			transaction.setBalance(transaction.getAmount());
		}

		
		Transaction depositTransaction = repository.save(transaction);
		return depositTransaction;
	}

	public Transaction withdrawAmount(@Valid Transaction transaction) {
		transaction.setTransactionType("WITHDRAW");
		transaction.setStatus("SUCCESS");
		List<Transaction> list = repository.getTransactions(transaction.getAccountNumber());

		Optional<Transaction> transact = list.stream().findFirst();
		
		if(transact.isPresent()){
			transaction.setBalance(transact.get().getBalance() - transaction.getAmount());
		}else{
			transaction.setBalance(transaction.getAmount());
		}
		return repository.save(transaction);
	}

	public List<Transaction> getTransactions(@Valid long accountNumber) {	
		return repository.getTransactions(accountNumber);
	}

}
