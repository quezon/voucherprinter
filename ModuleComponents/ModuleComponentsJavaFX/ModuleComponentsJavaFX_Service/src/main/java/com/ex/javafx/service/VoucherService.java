package com.ex.javafx.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.ex.dto.CashVoucher;
import com.ex.dto.Cheque;
import com.ex.dto.ChequeVoucher;
import com.ex.dto.Credit;
import com.ex.dto.Debit;
import com.ex.dto.JournalEntry;
import com.ex.dto.Particular;
import com.ex.dto.PettyCashVoucher;
import com.ex.dto.Voucher;
import com.ex.dto.VoucherEnum;

public class VoucherService<T extends Voucher> {
	private String mainPath = "http://127.0.0.1:9017/";

	private T voucher;

	Client client = ClientBuilder.newBuilder().build();
	WebTarget target = client.target(UriBuilder.fromPath(mainPath));
	ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
	IVoucherService proxy = rtarget.proxy(IVoucherService.class);

	public VoucherService(T voucher) {
		this.mainPath = this.mainPath + VoucherEnum.getThreeLetterCodeByClazz(voucher.getClass());
		if (voucher.getClass() == CashVoucher.class) {
			this.voucher = (T) new CashVoucher();
		} else if (voucher.getClass() == ChequeVoucher.class) {
			this.voucher = (T) new ChequeVoucher();
		} else {
			this.voucher = (T) new PettyCashVoucher();
		}
	}

	public boolean editVoucher(List<Particular> particulars, List<Debit> debits, List<Credit> credits,
			ZonedDateTime voucherDate, String voucherPayee) {
		JournalEntry je = new JournalEntry();
		je.setDebits(debits);
		je.setCredits(credits);

		this.voucher.setParticulars(particulars);
		this.voucher.setJournalEntry(je);
		this.voucher.setDateCreated(voucherDate);
		this.voucher.setPayee(voucherPayee);

		return this.proxy.editVoucher(this.voucher);
	}

	public long saveVoucher(List<Particular> particulars, List<Debit> debits, List<Credit> credits,
			ZonedDateTime voucherDate, String voucherPayee) {
		JournalEntry je = new JournalEntry();
		je.setDebits(debits);
		je.setCredits(credits);

		this.voucher.setParticulars(particulars);
		this.voucher.setJournalEntry(je);
		this.voucher.setDateCreated(voucherDate);
		this.voucher.setPayee(voucherPayee);

		return this.proxy.saveVoucher(this.voucher);
	}

	public ChequeVoucher saveVoucher(List<Particular> particulars, List<Debit> debits, List<Credit> credits,
			List<Cheque> cheques, ZonedDateTime voucherDate, String voucherPayee) {
		JournalEntry je = new JournalEntry();
		je.setDebits(debits);
		je.setCredits(credits);

		ChequeVoucher chv = (ChequeVoucher) this.voucher;

		chv.setCheques(cheques);
		chv.setParticulars(particulars);
		chv.setJournalEntry(je);
		chv.setDateCreated(voucherDate);
		chv.setPayee(voucherPayee);
		return this.proxy.saveChequeVoucher(chv);
	}

	public boolean printVoucher(Long voucherId) {
		return this.proxy.printVoucher(voucherId);
	}

	public long savePrintVoucher(List<Particular> particulars, List<Debit> debits, List<Credit> credits,
			ZonedDateTime voucherDate, String voucherPayee) {
		JournalEntry je = new JournalEntry();
		je.setDebits(debits);
		je.setCredits(credits);

		this.voucher.setParticulars(particulars);
		this.voucher.setJournalEntry(je);
		this.voucher.setDateCreated(voucherDate);
		this.voucher.setPayee(voucherPayee);
		return this.proxy.savePrintVoucher(this.voucher);
	}

	public T getVoucher(Long voucherId) {
		return (T) this.proxy.getVoucher(voucherId);
	}

	public List<T> getVouchers() {
		return (List<T>) this.proxy.getVouchers();
	}

	/*
	 * DateCreated DatePrinted
	 */
	public List<T> getVouchersByDateBetween(ZonedDateTime from, ZonedDateTime to, String name) {
		return (List<T>) this.proxy.getVouchersByDateBetween(from, to, name);
	}
}
