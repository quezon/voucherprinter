package com.ex.repo.voucher;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ex.entity.ChequeVoucher;

@Repository
public interface ChequeVoucherRepo extends VoucherRepo<ChequeVoucher>{
	public List<ChequeVoucher> findByDateApprovedIsNullAndDatePrintedIsNull();
	
	
}
