package com.ing.kata.banking.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ing.kata.banking.model.Transaction;
import com.ing.kata.banking.service.BankingService;

@RestController
@RequestMapping("/banking")
public class BankingController {

	@Autowired
	private BankingService service;

	@PostMapping("/getBalance")
	public Double getBalance(@Valid @RequestBody Transaction transaction) {
		try{
			return service.getBalance(transaction.getAccountNumber());
		}catch(NoSuchElementException excep){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Number does not exist");
		}
	}
	
	@PostMapping("/deposit")
	public Transaction depositAmount(@Valid @RequestBody Transaction transaction) {
		if(transaction.getAmount()>0.01){
			return service.depositAmount(transaction);
		}else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Amount should be greater than 0.01");
		}
	}
	
	@PostMapping("/withdraw")
	public Transaction withdrawAmount(@Valid @RequestBody Transaction transaction) {
		return service.withdrawAmount(transaction);
	}
	
	@PostMapping("/getTransactions")
	public List<Transaction> getTransactions(@Valid @RequestBody Transaction transaction) {
		try{
			return service.getTransactions(transaction.getAccountNumber());
		}catch(NoSuchElementException excep){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Number does not exist");
		}
		
	}
}
