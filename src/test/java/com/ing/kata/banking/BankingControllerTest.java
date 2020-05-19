package com.ing.kata.banking;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

	@MockBean
	private BankingService service;
	
	 @MockBean
	    private BankingRepository mockRepository;

	String mockBalance = "{\"accountNumber\":\"123451\"}";

	String mockTransactionDeposit = "{\"accountNumber\":\"123451\",\"amount\":\"50\"}";

	String mockTransactionWithdraw = "{\"accountNumber\":\"123451\",\"amount\":\"10\"}";

	@Test
	public void testDeposit() throws Exception {

		// Deposit
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/deposit")
				.accept(MediaType.APPLICATION_JSON).content(mockTransactionDeposit)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		mockMvc.perform(post("/banking/getBalance").accept(MediaType.APPLICATION_JSON).content(mockBalance)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(mockRepository, times(0)).getTransactions(123451);


	}
	
	@Test
	public void testWithdraw() throws Exception {

		// Deposit
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/withdraw")
				.accept(MediaType.APPLICATION_JSON).content(mockTransactionWithdraw)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		mockMvc.perform(post("/banking/getBalance").accept(MediaType.APPLICATION_JSON).content(mockBalance)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(mockRepository, times(0)).getTransactions(123451);

	}
	
	@Test
	public void testGetBalance() throws Exception {

		// Deposit
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/getBalance")
				.accept(MediaType.APPLICATION_JSON).content(mockTransactionDeposit)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
	public void testTransactions() throws Exception {

		// Deposit
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/banking/getTransactions")
				.accept(MediaType.APPLICATION_JSON).content(mockTransactionDeposit)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}
