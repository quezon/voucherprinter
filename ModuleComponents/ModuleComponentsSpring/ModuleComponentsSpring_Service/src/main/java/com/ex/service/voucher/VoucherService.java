package com.ex.service.voucher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.entity.Voucher;
import com.ex.schedule.NumberRangeSchedule;

@Service
public class VoucherService {
	@Autowired
	NumberRangeSchedule nrs;

	@Autowired
	JournalService jos;

	@Autowired
	ParticularsService particularsService;

//	@Autowired
//	ExternalAPIService externalAPI;
//
//	public boolean printVoucher(List<String> fileNames) {
//		// call print-docs microservice
//		return externalAPI.print(fileNames);
//	}

	public boolean isValidVoucher(Voucher voucher) {
		boolean valid = false;

		valid = valid && jos.debitsEqualCredits(voucher.getJournalEntry());
		valid = valid && voucher.getJournalEntry().getTotalDebits() > 0;
		valid = valid && nrs.getMax() >= voucher.getId();
		valid = valid && particularsService.getTotalParticulars(voucher.getParticulars()) == voucher.getJournalEntry()
				.getTotalDebits();
		valid = valid && particularsService.getTotalParticulars(voucher.getParticulars()) == voucher.getJournalEntry()
				.getTotalCredits();
		return valid;
	}
}
