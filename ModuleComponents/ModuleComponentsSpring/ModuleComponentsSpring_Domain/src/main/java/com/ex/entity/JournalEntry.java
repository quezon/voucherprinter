package com.ex.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class JournalEntry {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Embedded
	private List<Debit> debits;
	@Embedded
	private List<Credit> credits;
	
	public double getTotalDebits() {
		double total = 0.0;
		for(Debit debit: debits) {
			total += debit.getAmount();
		}
		return total;
	}
	
	public double getTotalCredits() {
		double total = 0.0;
		for(Credit credit: credits) {
			total += credit.getAmount();
		}
		return total;
	}
}