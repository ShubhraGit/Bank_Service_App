package com.hs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hs.model.Transaction;
import com.hs.model.TransactionDTO;
import com.hs.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public void saveTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = Transaction.builder().transactionType(transactionDTO.getTransactionType())
				.account_no(transactionDTO.getAccount_no()).amount(transactionDTO.getAmount()).status("SUCCESS").
				email(transactionDTO.getEmail()).build();
		transactionRepository.save(transaction);
		System.out.println("Transaction Saved Successfully");
	}

	
}
