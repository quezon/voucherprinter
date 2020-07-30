package com.ex.service.voucher;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.entity.Particular;

@Service
public class ParticularsService {
	public double getTotalParticulars(List<Particular> particulars) {
		double total = 0.0;
		for(Particular particular: particulars) {
			total += particular.getAmount();
		}
		return total;
	}
}
