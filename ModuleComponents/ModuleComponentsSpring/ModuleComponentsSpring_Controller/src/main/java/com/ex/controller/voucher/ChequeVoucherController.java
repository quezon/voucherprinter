package com.ex.controller.voucher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.dto.DateRange;
import com.ex.entity.ChequeVoucher;
import com.ex.exception.VoucherExistsException;
import com.ex.exception.VoucherNotExistsException;
import com.ex.exception.VoucherNotValidException;
import com.ex.service.voucher.ChequeVoucherService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/chv")
public class ChequeVoucherController {
	@Autowired
	ChequeVoucherService voucherService;

	@PostMapping("/edit")
	public ChequeVoucher editVoucher(@RequestBody ChequeVoucher chv)
			throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException, TransformerException, IOException, TranscoderException {
		return voucherService.save(chv, chv.getId());
	}

	@PostMapping("/save")
	public ChequeVoucher saveVoucher(@RequestBody ChequeVoucher chv) throws TransformerException, IOException, TranscoderException {
		return voucherService.save(chv);
	}

	@GetMapping("/get/{voucherId}")
	public ChequeVoucher getVoucherBalance(@PathVariable("voucherId") Long voucherId) {
		return voucherService.getChequeVoucherRepo().getOne(voucherId);
	}

	@GetMapping("/gets")
	public List<ChequeVoucher> getAllVouchers() {
		return voucherService.getAll();
	}

	@GetMapping("/gets/date")
	public List<ChequeVoucher> getAllVouchersByDateBetween(@RequestBody DateRange dr) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String methodName = "findAllBy" + dr.getDateName() + "Between";
		return voucherService.getAllByDateBetween(dr.getStart(), dr.getEnd(), methodName);
	}
}

//@PostMapping("/print")
//public Long printVoucher(@RequestBody ChequeVoucher chv) {
//	return 0L;
//}
//
//@PostMapping("/edit/print")
//public boolean editPrintVoucher(@RequestBody ChequeVoucher chv)
//		throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException {
//	return voucherService.editPrintVoucher(chv);
//}
//
//@PostMapping("/save/print")
//public Long savePrintVoucher(@RequestBody ChequeVoucher chv) {
//	return voucherService.savePrintVoucher(chv);
//}
