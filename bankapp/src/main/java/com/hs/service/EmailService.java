package com.hs.service;

import java.util.List;

import com.hs.model.EmailDetails;
import com.hs.model.Transaction;

public interface EmailService {

	
	void sendEmailAlert(EmailDetails emailDetails);
	void sendTransactionHistoryEmail(String recipientEmail, List<Transaction> transactionList);
}
