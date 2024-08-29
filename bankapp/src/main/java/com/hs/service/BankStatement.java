package com.hs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hs.model.Bank;
import com.hs.model.Transaction;
import com.hs.model.TransactionDTO;
import com.hs.model.UserDetailsDTO;
import com.hs.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BankStatement {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	EmailService emailService;

	public List<TransactionDTO> generateStatement(Long account_no, String startDate, String endDate,String email) {

		LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

		System.out.println("account_no" + account_no + "start " + start + "end" + end);

		List<Transaction> transaction = transactionRepository.findByAccNo(account_no);

		List<TransactionDTO> transdetails = new ArrayList<>();
		for (Transaction trans : transaction) {
System.out.println("trans.getTransactionType(),"
		+ " trans.getAmount(),"
		+ " trans.getAccount_no(),"
		+ "trans.getStatus(),trans.getEmail()" + trans.getTransactionType()+ trans.getAmount()+ trans.getAccount_no()+
		trans.getStatus()+trans.getEmail());
			
			TransactionDTO td = new TransactionDTO(trans.getTransactionType(), trans.getAmount(), trans.getAccount_no(),
					trans.getStatus(),trans.getEmail());
			transdetails.add(td);
			System.out.println("Added");
		}
		System.out.println(">>>email Bankstatement"+email);
		emailService.sendTransactionHistoryEmail(email, transaction);
		return transdetails;
	}
	
	

}
