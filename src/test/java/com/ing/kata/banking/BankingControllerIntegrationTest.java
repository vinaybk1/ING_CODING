package com.ing.kata.banking;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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

     @Test
     public void contextLoads() {

     }

     @Test
     public void testGetBalance() {
        long accountNumber = 12345;
        Transaction transaction1 = new Transaction();
        transaction1.setAccountNumber(12345);
        transaction1.setAmount(new Double(100));
        ResponseEntity<Transaction> postResponse1 = restTemplate.postForEntity(getRootUrl() + "/deposit", transaction1, Transaction.class);
        
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/getBalance", accountNumber, String.class);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testDeposit() {
        Transaction transaction1 = new Transaction();
        transaction1.setAccountNumber(12345);
        transaction1.setAmount(new Double(100));
        
        ResponseEntity<Transaction> postResponse = restTemplate.postForEntity(getRootUrl() + "/deposit", transaction1, Transaction.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testWithdraw() {
        Transaction transaction1 = new Transaction();
        transaction1.setAccountNumber(12345);
        transaction1.setAmount(new Double(100));
        
        ResponseEntity<Transaction> postResponse1 = restTemplate.postForEntity(getRootUrl() + "/deposit", transaction1, Transaction.class);
        Transaction transaction2 = new Transaction();
        transaction2.setAccountNumber(12345);
        transaction2.setAmount(new Double(50));
        
        ResponseEntity<Transaction> postResponse = restTemplate.postForEntity(getRootUrl() + "/deposit", transaction2, Transaction.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

}
