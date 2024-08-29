package com.hs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hs.model.Bank;
import com.hs.model.Transaction;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{

	
	@Query(value = "SELECT * FROM shubhradb.transaction WHERE account_no = :account_no", nativeQuery = true)
	List<Transaction> findByAccNo(@Param("account_no") Long account_no);
	
}
