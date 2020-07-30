package com.ex.entity;

import java.util.Currency;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ModeOfPayment {
	private long id;
	private String payeeName;
	private double amount;
	private Currency currency;
	private boolean sent;
	private boolean voided;
	private boolean cleared;
}
