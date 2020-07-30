package com.ex.service.cheque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.entity.cheque.Cheque;
import com.ex.entity.cheque.ChequeOfVoucher;
import com.ex.repo.cheque.ChequeRepo;

@Service
public class ChequeDBService {
	@Autowired
	private ChequeRepo chequeRepo;
	
	public void saveCheque(ChequeOfVoucher chequeOfVoucher) {
		chequeRepo.save((Cheque) chequeOfVoucher);
	}
	
	public void saveCheque(Cheque cheque) {
		chequeRepo.save(cheque);
	}
	
	public void saveCheques(List<Cheque> cheques) {
		chequeRepo.saveAll(cheques);
	}
	
	public void saveChequesOfVoucher(List<ChequeOfVoucher> chequeOfVouchers) {
		chequeOfVouchers.forEach(cv -> {
			chequeRepo.save((Cheque) cv);
		});
	}
}
