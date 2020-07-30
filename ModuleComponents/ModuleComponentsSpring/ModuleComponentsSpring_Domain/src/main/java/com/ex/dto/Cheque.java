package com.ex.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Cheque extends ModeOfPayment {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate chequeDate;
	private String chequeNumber;
	private String bankAccountNumber;
	private String bankRoutingNumber;
	private String bankName;
	private int bankId;
	private boolean printed;
	private boolean dishonored;
}
