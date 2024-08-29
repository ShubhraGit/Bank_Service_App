package com.hs.service;

import java.util.List;

import com.hs.model.Bank;
import com.hs.model.ResponseDTO;
import com.hs.model.UserDetailsDTO;

public interface BankService {
	
	 Bank register(Bank bank);
	 List<UserDetailsDTO> getAllUsers();
	ResponseDTO transfer(Long fromAccount, Long toAccount, Long amount);
	ResponseDTO delete(Long accNo);
	ResponseDTO deposit(Long accountno,Long amount);
	ResponseDTO withdraw(Long accountno, Long amount);
}
