package com.ex.dto;

import java.util.Currency;

import lombok.Data;

@Data
public abstract class ModeOfPayment {
	private long id;
	private String payeeName;
	private double amount;
	private Currency currency;
	private boolean sent;
	private boolean voided;
	private boolean cleared;
}
