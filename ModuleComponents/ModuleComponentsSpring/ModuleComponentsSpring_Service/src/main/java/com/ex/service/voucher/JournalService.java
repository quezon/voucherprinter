package com.ex.service.voucher;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ex.entity.Credit;
import com.ex.entity.Debit;
import com.ex.entity.JournalEntry;

@Service
public class JournalService {
	public boolean debitsEqualCredits(Map<String, Double> map) {
		return map.get("debit") == map.get("credit");
	}
	
	public boolean debitsEqualCredits(JournalEntry je) {
		double totaldebits = 0.0;
		double totalcredits = 0.0;
		
		for(Debit debit: je.getDebits()) {
			totaldebits += debit.getAmount();
		}
		
		for(Credit credit: je.getCredits()) {
			totalcredits += credit.getAmount();
		}
		
		return totaldebits == totalcredits;
	}
}
