package com.ing.kata.banking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.kata.banking.model.Transaction;
import com.ing.kata.banking.service.BankingService;

@RestController
@RequestMapping("/banking")
public class BankingController {

	@Autowired
	private BankingService service;

	@PostMapping("/getBalance")
	public Double getBalance(@Valid @RequestBody long accountNumber) {
		return service.getBalance(accountNumber);
	}
	
	@PostMapping("/deposit")
	public Transaction depositAmount(@Valid @RequestBody Transaction transaction) {
		return service.depositAmount(transaction);
	}
	
	@PostMapping("/withdraw")
	public Transaction withdrawAmount(@Valid @RequestBody Transaction transaction) {
		return service.withdrawAmount(transaction);
	}
	
	@PostMapping("/getTransactions")
	public List<Transaction> getTransactions(@Valid @RequestBody long accountNumber) {
		return service.getTransactions(accountNumber);
	}
}
