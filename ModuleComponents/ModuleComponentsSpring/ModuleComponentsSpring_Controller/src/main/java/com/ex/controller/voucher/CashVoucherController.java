package com.ex.controller.voucher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.dto.DateRange;
import com.ex.entity.CashVoucher;
import com.ex.exception.VoucherExistsException;
import com.ex.exception.VoucherNotExistsException;
import com.ex.exception.VoucherNotValidException;
import com.ex.service.voucher.CashVoucherService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/cav")
public class CashVoucherController {
	@Autowired
	CashVoucherService voucherService;

	@PostMapping("/edit")
	public CashVoucher editVoucher(@RequestBody CashVoucher cav)
			throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException {
		return voucherService.save(cav, cav.getId());
	}

	@PostMapping("/save")
	public CashVoucher saveVoucher(@RequestBody CashVoucher cav) {
		return voucherService.save(cav);
	}

	@GetMapping("/get/{voucherId}")
	public CashVoucher getVoucherBalance(@PathVariable("voucherId") Long voucherId) {
		return voucherService.getCashVoucherRepo().getOne(voucherId);
	}

	@GetMapping("/gets")
	public List<CashVoucher> getAllVouchers() {
		return voucherService.getAll();
	}

	@GetMapping("/gets/date")
	public List<CashVoucher> getAllVouchersByDateBetween(@RequestBody DateRange dr) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String methodName = "findAllBy" + dr.getDateName() + "Between";
		return voucherService.getAllByDateBetween(dr.getStart(), dr.getEnd(), methodName);
	}
}

//@PostMapping("/edit/print")
//public boolean editPrintVoucher(@RequestBody CashVoucher cav)
//		throws VoucherExistsException, VoucherNotExistsException, VoucherNotValidException {
//	return voucherService.editPrintVoucher(cav);
//}

//@PostMapping("/save/print")
//public Long savePrintVoucher(@RequestBody CashVoucher cav) {
//	return voucherService.savePrintVoucher(cav);
//}
