package com.ing.kata.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ing.kata.banking.model.Transaction;

@Repository
public interface BankingRepository extends JpaRepository<Transaction, Long> {

	@Query("FROM Transaction WHERE accountNumber = ?1 ORDER BY Transaction_Date DESC")
	List<Transaction> getTransactions(long accountNumber);

	@Query(nativeQuery = true, value = "SELECT * FROM (SELECT * FROM Transaction WHERE account_Number = :accountNumber ORDER BY Transaction_Date DESC) WHERE ROWNUM<=1")
	List<Transaction> getBalanceTransactions(@Param("accountNumber")long accountNumber);

}
