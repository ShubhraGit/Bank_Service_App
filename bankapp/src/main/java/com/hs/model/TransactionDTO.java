package com.hs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
	
	private String transactionType;
	private Long amount;
	private Long account_no;
private String status;
private String email;

}
