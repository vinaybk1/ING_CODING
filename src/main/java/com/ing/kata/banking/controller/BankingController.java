package com.ing.kata.banking.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.kata.banking.execption.BankingException;
import com.ing.kata.banking.model.Transaction;
import com.ing.kata.banking.service.BankingService;

@RestController
@RequestMapping("/banking")
public class BankingController {
	
	Logger logger = LoggerFactory.getLogger(BankingController.class);

	@Autowired
	private BankingService service;

	@PostMapping("/getBalance")
	public Double getBalance(@Valid @RequestBody Transaction transaction) throws BankingException{
		    logger.debug("Get Balance Request for account number :: "+transaction.getAccountNumber());
			return service.getBalance(transaction.getAccountNumber());
	}
	
	@PostMapping("/deposit")
	public Transaction depositAmount(@Valid @RequestBody Transaction transaction) throws BankingException{
		logger.debug("Deposit request for account number :: "+transaction.getAccountNumber()+" :: Amount :: "+transaction.getAmount());
		if(transaction.getAmount()>0.01){
			return service.depositAmount(transaction);
		}else{
			throw new BankingException("INVALID", "Amount should be greater than 0.01");
		}
	}
	
	@PostMapping("/withdraw")
	public Transaction withdrawAmount(@Valid @RequestBody Transaction transaction) throws BankingException{
		logger.debug("Withdraw Amount from the account number :: "+transaction.getAccountNumber()+" :: Amount :: "+transaction.getAmount());
		if(transaction.getAmount()>0){
			return service.withdrawAmount(transaction);
		}else{
			throw new BankingException("INVALID AMOUNT", "Amount is not valid");
		}
		
	}
	
	@PostMapping("/getTransactions")
	public List<Transaction> getTransactionsForAccount(@Valid @RequestBody Transaction transaction) throws BankingException{
		logger.debug("Get Transactions for the account number :: "+transaction.getAccountNumber());
			List<Transaction> transactionList = service.getTransactions(transaction.getAccountNumber());
			if(transactionList.isEmpty()){
				throw new BankingException("INVALID REQUEST", "No Transaction present for the account");
			}else{
				return transactionList;
			}
		
	}
}
