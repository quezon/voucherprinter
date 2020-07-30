package com.ex.service.voucher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.xml.transform.TransformerException;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ex.entity.ChequeVoucher;
import com.ex.exception.VoucherExistsException;
import com.ex.exception.VoucherNotExistsException;
import com.ex.exception.VoucherNotValidException;
import com.ex.repo.voucher.ChequeVoucherRepo;

@Service
public class ChequeVoucherService extends VoucherService {
	RestTemplate restTemplate;
	
	@Autowired
	ChequeVoucherRepo chvr;

	public ChequeVoucher save(ChequeVoucher chv) throws TransformerException, IOException, TranscoderException {
		ChequeVoucher chv2 = chvr.save(chv);
		return chv2;
	}

	public ChequeVoucher save(ChequeVoucher chv, Long voucherNumber)
			throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException, TransformerException, IOException, TranscoderException {
		ChequeVoucher chv2 = new ChequeVoucher();
		Boolean chequeVoucherExists = chvr.existsById(voucherNumber);

		if (chequeVoucherExists && voucherNumber == null) {
			throw new VoucherExistsException();
		} else if (!chequeVoucherExists && voucherNumber != null) {
			throw new VoucherNotExistsException();
		} else if (isValidVoucher(chv)) {
			if (voucherNumber != null) {
				Optional<ChequeVoucher> chequeVoucherFound = chvr.findById(voucherNumber);
				chequeVoucherFound.ifPresent(chequeVoucher -> {
					// delete what is found
					chvr.delete(chequeVoucher);
				});
			}
			chv2 = chvr.save(chv);
		} else {
			throw new VoucherNotValidException();
		}
		return chv2;
	}

	public Boolean isValidVoucher(ChequeVoucher chv) {
		Boolean hasSpecificCreditAccounts = false;

		hasSpecificCreditAccounts = chv.getJournalEntry().getDebits().stream().anyMatch(a -> {
			return a.getDebit().equals("Interest-bearing checking accounts")
					|| a.getDebit().equals("Money market checking accounts")
					|| a.getDebit().equals("Free checking accounts");
		});

		return super.isValidVoucher(chv) && hasSpecificCreditAccounts;
	}

	public ChequeVoucherRepo getChequeVoucherRepo() {
		return chvr;
	}

	public List<ChequeVoucher> getAll() {
		return chvr.findAll();
	}

	public List<ChequeVoucher> getAllByDateBetween(ZonedDateTime from, ZonedDateTime to, String methodName)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Method getAllByDateBetweenMethod = ChequeVoucherRepo.class.getMethod(methodName, ZonedDateTime.class,
				ZonedDateTime.class);

		return (List<ChequeVoucher>) getAllByDateBetweenMethod.invoke(chvr, from, to);
	}
}


//public Long savePrintVoucher(ChequeVoucher chv) {
//// save cheque voucher in db
//Long voucherId = save(chv).getId();
//
//if (voucherId != null) {
//	// save cheque voucher file
//	if (externalAPI.saveVoucherFile(chv)) {
//		// print cheque voucher
//		if (externalAPI.print(vtf.convert(chv))) {
//			return voucherId;
//		}
//	}
//}
//
//return voucherId;
//}
//
//public Boolean editPrintVoucher(ChequeVoucher chv)
//	throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException {
//Boolean saved = save(chv, chv.getId());
//
//if (saved) {
//	// save cheque voucher file
//	if (externalAPI.saveVoucherFile(chv)) {
//		// print cheque voucher
//		if (externalAPI.print(vtf.convert(chv))) {
//			return saved;
//		}
//	}
//}
//
//return false;
//}
