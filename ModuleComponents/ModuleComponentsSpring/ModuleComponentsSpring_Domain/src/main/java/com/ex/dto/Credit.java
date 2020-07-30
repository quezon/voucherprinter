package com.ex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
	double amount;
	String credit;
	String chartOfAccountCode;
}
