package com.hs.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.model.Bank;
import com.hs.model.EmailDetails;
import com.hs.model.UserDetailsDTO;
import com.hs.repository.BankRepository;
import com.hs.model.ResponseDTO;
import com.hs.model.TransactionDTO;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRepository bankRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	TransactionService transactionService;
	
	

	@Override
	public Bank register(Bank bank) {

		boolean creatingNewAcc = true;
		Long acc = null;
		
		while (creatingNewAcc) {
			Random random = new Random(); // 0 - 100000
			Long tempAcc = random.nextLong(100000); // 234, 657, 9999, 234

			Optional<Bank> existingAcc = bankRepository.findByAccountno(acc);

			if (!existingAcc.isPresent()) {
				acc = tempAcc;
				creatingNewAcc = false;
			}

		}
		bank.setAccountno(acc);
		
		System.out.println(" bank.setAccountno(acc);" + acc);
		Bank bank1 = bankRepository.save(bank);
System.out.println("email >> service impl"+bank1.getEmail());
		// Save Transaction
		TransactionDTO transactionDTO = TransactionDTO.builder().account_no(acc).transactionType("REGISTER")
				.amount(bank1.getBalance()).email(bank1.getEmail()).build();

		transactionService.saveTransaction(transactionDTO);

		System.out.println(" bank.setAccountno(acc);" + bank1.getName() + bank1.getAccountno());
		// send email
		EmailDetails emailDetails = EmailDetails.builder().recipient(bank1.getEmail()).subject("Account Creation")
				.messageBody("Congratulations! Your Acount has been Succesfully created "
						+ "\nYour Account Details\n Account Name" + bank1.getName() + "\nAccount Number"
						+ bank1.getAccountno())
				.build();
		emailService.sendEmailAlert(emailDetails);
		return bank1;

	}

	@Override
	public List<UserDetailsDTO> getAllUsers() {
		// only show username and account number
		List<Bank> accounts = bankRepository.findAll();
		List<UserDetailsDTO> userdetails = new ArrayList<>();
		for (Bank account : accounts) {
			UserDetailsDTO ud = new UserDetailsDTO(account.getName(), account.getAccountno());
			userdetails.add(ud);
		}
		return userdetails;
	}

	@Override
	public ResponseDTO transfer(Long fromAccount, Long toAccount, Long amount) {

		Bank fromAcc = bankRepository.findByAccountno(fromAccount).get();
		Bank toAcc = bankRepository.findByAccountno(toAccount).get();

		// check accounts are available or not

		// check balance of fromAccount (>=amount)

		fromAcc.setBalance(fromAcc.getBalance() - amount);

		toAcc.setBalance(toAcc.getBalance() + amount);

		bankRepository.save(fromAcc);

		// Save Transaction
		TransactionDTO transactionDTO = TransactionDTO.builder().account_no(fromAcc.getAccountno())
				.transactionType("TransferFrmAcc").amount(fromAcc.getBalance()).email(fromAcc.getEmail()).build();

		transactionService.saveTransaction(transactionDTO);

		bankRepository.save(toAcc);

		// Save Transaction
		TransactionDTO transactionDTO1 = TransactionDTO.builder().account_no(toAcc.getAccountno())
				.transactionType("TransferToAcc").amount(toAcc.getBalance()).email(toAcc.getEmail()).build();

		transactionService.saveTransaction(transactionDTO1);

		return new ResponseDTO("Amount transfer successfully");
	}

	@Override
	public ResponseDTO delete(Long accNo) {
		bankRepository.deleteByAccNo(accNo);
		return new ResponseDTO("Account deleted successfully");
	}

	@Override
	public ResponseDTO deposit(Long accountno, Long amount) {
		Bank Acc_no = bankRepository.findByAccountno(accountno).get();
		Acc_no.setBalance(Acc_no.getBalance() + amount);
		bankRepository.save(Acc_no);

		// Save Transaction
		TransactionDTO transactionDTO = TransactionDTO.builder().account_no(Acc_no.getAccountno())
				.transactionType("DEPOSIT").amount(amount).email(Acc_no.getEmail()).build();

		transactionService.saveTransaction(transactionDTO);

		return new ResponseDTO("Amount Deposited successfully");
	}

	@Override
	public ResponseDTO withdraw(Long accountno, Long amount) {
		Bank Acc_no = bankRepository.findByAccountno(accountno).get();
		Acc_no.setBalance(Acc_no.getBalance() - amount);
		bankRepository.save(Acc_no);

		// Save Transaction
		TransactionDTO transactionDTO = TransactionDTO.builder().account_no(Acc_no.getAccountno())
				.transactionType("WITHDRAW").amount(amount).email(Acc_no.getEmail()).build();

		transactionService.saveTransaction(transactionDTO);

		return new ResponseDTO("Amount Withdraw successfully");
	}

}
