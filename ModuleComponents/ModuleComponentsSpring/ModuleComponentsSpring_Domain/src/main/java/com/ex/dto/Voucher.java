package com.ex.dto;

import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public abstract class Voucher implements Voucherable {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, YYYY")
	private ZonedDateTime dateCreated;
	private String payee;
	private Currency currency;
	private JournalEntry journalEntry;
	private List<Particular> particulars;
	private String approvedBy;
	private String rejectedBy;
	private String preparedBy;
	private String voidedBy;
	private String certifiedBy;
	private String receivedBy;
	private String receivedFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, YYYY")
	private ZonedDateTime datePrinted;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, YYYY")
	private ZonedDateTime dateApproved;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, YYYY")
	private ZonedDateTime dateRejected;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, YYYY")
	private ZonedDateTime dateSent;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, YYYY")
	private ZonedDateTime dateVoided;

	private boolean printed;
	private boolean valid;
	private boolean voided;
}
