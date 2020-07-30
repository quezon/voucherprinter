package com.ex.service.voucher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ex.entity.CashVoucher;
import com.ex.exception.VoucherExistsException;
import com.ex.exception.VoucherNotExistsException;
import com.ex.exception.VoucherNotValidException;
import com.ex.repo.voucher.CashVoucherRepo;

@Service
public class CashVoucherService extends VoucherService {
	@Autowired
	CashVoucherRepo cavr;

	public CashVoucher save(CashVoucher cav) {
		return cavr.save(cav);
	}

	public CashVoucher save(CashVoucher cav, Long voucherNumber)
			throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException {
		Boolean cashVoucherExists = cavr.existsById(voucherNumber);
		CashVoucher cav2 = new CashVoucher();

		if (cashVoucherExists && voucherNumber == null) {
			throw new VoucherExistsException();
		} else if (!cashVoucherExists && voucherNumber != null) {
			throw new VoucherNotExistsException();
		} else if (isValidVoucher(cav)) {
			if (voucherNumber != null) {
				Optional<CashVoucher> cashVoucherFound = cavr.findById(voucherNumber);
				cashVoucherFound.ifPresent(cashVoucher -> {
					// delete what is found
					cavr.delete(cashVoucher);
				});
			}
			cav2 = cavr.save((CashVoucher) cav);
		} else {
			throw new VoucherNotValidException();
		}
		return cav2;
	}

	public Boolean isValidVoucher(CashVoucher cav) {
		Boolean hasCashOnHandCredit = false;

		hasCashOnHandCredit = cav.getJournalEntry().getCredits().stream()
				.anyMatch(a -> a.getCredit().equals("Cash On Hand"));

		return super.isValidVoucher(cav) && hasCashOnHandCredit;
	}

	public CashVoucherRepo getCashVoucherRepo() {
		return cavr;
	}

	public List<CashVoucher> getAll() {
		return cavr.findAll();
	}

	public List<CashVoucher> getAllByDateBetween(ZonedDateTime from, ZonedDateTime to, String methodName)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Method getAllByDateBetweenMethod = CashVoucherRepo.class.getMethod(methodName, ZonedDateTime.class,
				ZonedDateTime.class);

		return (List<CashVoucher>) getAllByDateBetweenMethod.invoke(cavr, from, to);
	}
}

//public Long savePrintVoucher(CashVoucher cav) {
//// save cash voucher in db
//Long voucherId = save(cav);
//
//if (voucherId != null) {
//	// save cash voucher file
//	if ( documentService.saveVoucherToFile(cav) ) {
//		// print cash voucher
//		if ( printingService.printDocument(vtf.convert(cav)) ) {
//			return voucherId;
//		}
//	}
//}
//return voucherId;
//}
//
//public Boolean editPrintVoucher(CashVoucher cav)
//	throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException {
//Boolean saved = save(cav, cav.getId());
//
//// save cash voucher in db
//if (saved) {
//	// save cash voucher file
//	if (documentService.saveVoucherToFile(cav)) {
//		// print cash voucher
//		if (printingService.printDocument(vtf.convert(cav))) {
//			return saved;
//		}
//	}
//}
//
//return false;
//}