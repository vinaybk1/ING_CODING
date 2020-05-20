package com.ing.kata.banking;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ing.kata.banking.controller.BankingController;
import com.ing.kata.banking.model.Transaction;
import com.ing.kata.banking.repository.BankingRepository;
import com.ing.kata.banking.service.BankingService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BankingController.class, secure = false)
public class BankingControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	BankingController controller;

	@MockBean
	private BankingService service;

	@MockBean
	private BankingRepository mockRepository;

	String mockBalance = "{\"accountNumber\":\"123451\"}";

	String mockTransactionDeposit = "{\"accountNumber\":\"123451\",\"amount\":\"50\"}";

	String mockTransactionWithdraw = "{\"accountNumber\":\"123451\",\"amount\":\"10\"}";

	@Test
	public void testDeposit() throws Exception {

		Transaction transaction = new Transaction();
		transaction.setAccountNumber(12345);
		transaction.setAmount(new Double(123));
		transaction.setBalance(new Double(123));
		transaction.setStatus("SUCCESS");
		transaction.setTransactionType("DEPOSIT");

		Mockito.when(service.depositAmount(Mockito.any(Transaction.class))).thenReturn(transaction);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/deposit")
				.accept(MediaType.APPLICATION_JSON).content(mockTransactionDeposit)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("DEPOSIT::"+result.getResponse());

		String expected = "{\"accountNumber\":12345,\"amount\":123.0,\"balance\":123.0,\"status\":\"SUCCESS\",\"transactionId\":0,\"transactionType\":\"DEPOSIT\",\"transactionDate\":null}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void testWithdraw() throws Exception {

		Transaction transaction1 = new Transaction();
		transaction1.setAccountNumber(12345);
		transaction1.setAmount(new Double(123));
		transaction1.setBalance(new Double(123));
		transaction1.setStatus("SUCCESS");
		transaction1.setTransactionType("DEPOSIT");

		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(transaction1);

		Mockito.when(service.withdrawAmount(Mockito.any(Transaction.class))).thenReturn(transaction1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/withdraw")
				.accept(MediaType.APPLICATION_JSON).content(mockTransactionDeposit).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("WITHDRAW"+result.getResponse().getContentAsString());

		String expected = "{\"accountNumber\":12345,\"amount\":123.0,\"balance\":123.0,\"status\":\"SUCCESS\",\"transactionId\":0,\"transactionType\":\"DEPOSIT\",\"transactionDate\":null}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}


	@Test
	public void testGetBalance() throws Exception {

		Transaction transaction = new Transaction();
		transaction.setAccountNumber(12345);

		Mockito.when(service.getBalance(Mockito.anyLong())).thenReturn(new Double(100));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/getBalance")
				.accept(MediaType.APPLICATION_JSON).content(mockBalance).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals("100.0", result.getResponse().getContentAsString());

	}

	@Test
	public void testTransactions() throws Exception {

		Transaction transaction1 = new Transaction();
		transaction1.setAccountNumber(12345);
		transaction1.setAmount(new Double(123));
		transaction1.setBalance(new Double(123));
		transaction1.setStatus("SUCCESS");
		transaction1.setTransactionType("DEPOSIT");

		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(transaction1);

		Mockito.when(service.getTransactions(Mockito.anyLong())).thenReturn(transactionList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/getTransactions")
				.accept(MediaType.APPLICATION_JSON).content(mockBalance).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"accountNumber\":12345,\"amount\":123.0,\"balance\":123.0,\"status\":\"SUCCESS\",\"transactionId\":0,\"transactionType\":\"DEPOSIT\",\"transactionDate\":null}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

}
