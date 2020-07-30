package com.ex.dto;

import java.util.List;

import lombok.Data;

@Data
public class JournalEntry {
	private long id;
	private List<Debit> debits;
	private List<Credit> credits;

	public double getTotalDebits() {
		double total = 0.0;
		for (Debit debit : debits) {
			total += debit.getAmount();
		}
		return total;
	}

	public double getTotalCredits() {
		double total = 0.0;
		for (Credit credit : credits) {
			total += credit.getAmount();
		}
		return total;
	}
}