package com.ex.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ex.entity.PettyCashVoucher;


@Repository
public interface PettyCashVoucherRepo extends VoucherRepo<PettyCashVoucher> {
	public List<PettyCashVoucher> findByDateApprovedIsNullAndDatePrintedIsNull();
	
	
}
