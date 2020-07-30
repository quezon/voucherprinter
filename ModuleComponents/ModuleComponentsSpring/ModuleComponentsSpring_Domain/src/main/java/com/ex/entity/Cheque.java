package com.ex.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Cheque extends ModeOfPayment {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
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
