package com.hs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hs.model.Bank;

import jakarta.transaction.Transactional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

	Optional<Bank> findByAccountno(Long accountno);
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM bank WHERE account_no = :accNo", nativeQuery = true)
	void deleteByAccNo(@Param("accNo") Long accNo);
	
}
