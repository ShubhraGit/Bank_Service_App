package com.hs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.model.Bank;
import com.hs.model.UserDetailsDTO;
import com.hs.service.BankService;
import com.hs.service.BankStatement;
import com.hs.service.TransactionService;
import com.hs.model.ResponseDTO;
import com.hs.model.Transaction;
import com.hs.model.TransactionDTO;

@RestController
@CrossOrigin("*")
public class BankController {

	@Autowired
	BankService bankservice;

	@Autowired
	BankStatement bankStatement;
	
	@Autowired
	TransactionService transactionService;

	@PostMapping("/register")
	public Bank register(@RequestBody Bank bank) {
		System.out.println("email >> controller" +bank.getEmail());
		return bankservice.register(bank);

	}

	@GetMapping("/users")
	public List<UserDetailsDTO> getAllUsers() {
		return bankservice.getAllUsers();
	}

	@PutMapping("/transfer")
	public ResponseDTO transfer(@RequestParam("fromAccount") Long fromAccount,
			@RequestParam("toAccount") Long toAccount, @RequestParam("amount") Long amount) {

		return bankservice.transfer(fromAccount, toAccount, amount);
	}

	@DeleteMapping("/delete/{accno}")
	public ResponseDTO delete(@PathVariable("accno") Long accNo) {
		return bankservice.delete(accNo);
	}

	@PutMapping("/deposit")
	public ResponseDTO deposit(@RequestParam("accountno") Long accountno, @RequestParam("amount") Long amount) {

		return bankservice.deposit(accountno, amount);

	}

	@PutMapping("/withdraw")
	public ResponseDTO withdraw(@RequestParam("accountno") Long accountno, @RequestParam("amount") Long amount) {

		return bankservice.withdraw(accountno, amount);

	}

	@GetMapping("/bankStatement")
	public List<TransactionDTO> generateStatement(@RequestParam("account_no") Long account_no,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("email") String email) {
		System.out.println("account_no" + account_no + "startDate" + startDate + "endDate" + endDate + "email"+email);

		return bankStatement.generateStatement(account_no, startDate, endDate, email);
	}
	
	  

	
	 
}
