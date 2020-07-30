package com.ex.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Debit {
	double amount;
	String debit;
	String chartOfAccountCode;
}
