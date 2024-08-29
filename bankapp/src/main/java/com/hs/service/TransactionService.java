package com.hs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.hs.model.Transaction;
import com.hs.model.TransactionDTO;
import com.hs.repository.TransactionRepository;

public interface TransactionService {
	
	
	 void saveTransaction(TransactionDTO transactionDTO);

}
