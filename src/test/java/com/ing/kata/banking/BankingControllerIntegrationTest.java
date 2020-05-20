package com.ing.kata.banking;

import static org.junit.Assert.assertNotNull;

import org.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;


import com.ing.kata.banking.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankingControllerIntegrationTest {
     @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }
     HttpHeaders headers = new HttpHeaders();
     @Test
     public void contextLoads() {

     }
     
     @Test
     public void testGetBalance() throws JSONException {
    	 
         Transaction transaction = new Transaction();
         transaction.setAccountNumber(12345);
         transaction.setAmount(new Double(100));
         
         ResponseEntity<String> depositResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl() + "/banking/deposit", transaction, String.class);
         
       Transaction balance = new Transaction();
       balance.setAccountNumber(12345);
         
         System.out.println(getRootUrl());
         
         depositResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl()+"/banking/getBalance", transaction, String.class);

         String expected = "100.0";
 
         JSONAssert.assertEquals(expected, depositResponse.getBody(), false);
    }

     @Test
     public void testGetBalanceException() throws JSONException {
         
       Transaction transaction = new Transaction();
       transaction.setAccountNumber(123450);
         
         System.out.println(getRootUrl());

         ResponseEntity<String> response = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl()+"/banking/getBalance", transaction, String.class);

         String expected = "{\"message\":\"INVALID REQUEST\",\"details\":[\"Account Number Does not exist\"]}";
 
         JSONAssert.assertEquals(expected, response.getBody(), false);
    }

   @Test
    public void testDeposit() throws JSONException {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(123451);
        transaction.setAmount(new Double(100));
        
        ResponseEntity<String> postResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl() + "/banking/deposit", transaction, String.class);
        
        String expected = "{\"accountNumber\":123451,\"amount\":100.0,\"balance\":100.0,\"status\":\"SUCCESS\",\"transactionType\":\"DEPOSIT\"}";
        
        JSONAssert.assertEquals(expected, postResponse.getBody(), false);

    }


   @Test
    public void testWithdraw() throws JSONException {
       Transaction transaction = new Transaction();
       transaction.setAccountNumber(123452);
       transaction.setAmount(new Double(100));
       
       ResponseEntity<String> postResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl() + "/banking/deposit", transaction, String.class);
       
       transaction.setAmount(new Double(10));
       
       postResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl() + "/banking/withdraw", transaction, String.class);
       
       String expected = "{\"accountNumber\":123452,\"amount\":10.0,\"balance\":90.0,\"status\":\"SUCCESS\",\"transactionType\":\"WITHDRAW\"}";
       
       
       JSONAssert.assertEquals(expected, postResponse.getBody(), false);
    }

   @Test
   public void testGetTransactionsForAccount() throws JSONException {
      Transaction transaction = new Transaction();
      transaction.setAccountNumber(123453);
      transaction.setAmount(new Double(100));
      
      ResponseEntity<String> postResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl() + "/banking/deposit", transaction, String.class);
      
      
      postResponse = restTemplate.withBasicAuth("test", "test123").postForEntity(getRootUrl() + "/banking/getTransactions", transaction, String.class);
      
      String expected = "[{\"accountNumber\":123453,\"amount\":100.0,\"balance\":100.0,\"status\":\"SUCCESS\",\"transactionType\":\"DEPOSIT\"}]";
      
      JSONAssert.assertEquals(expected, postResponse.getBody(), false);
   }
   
   @Test
   public void testInvalidUser() throws JSONException {
  	 
       Transaction transaction = new Transaction();
       transaction.setAccountNumber(12345);
       transaction.setAmount(new Double(100));
       
       Assertions.assertThrows(ResourceAccessException.class,()->restTemplate.withBasicAuth("test", "test1234").postForEntity(getRootUrl()+"/banking/getBalance", transaction, String.class));
  }
}
