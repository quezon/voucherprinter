package com.ex.repo.voucher;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ex.entity.CashVoucher;

@Repository
public interface CashVoucherRepo extends VoucherRepo<CashVoucher> {
	public List<CashVoucher> findByDateApprovedIsNullAndDatePrintedIsNull();
}
