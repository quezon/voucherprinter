package com.ex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debit {
	double amount;
	String debit;
	String chartOfAccountCode;
}
