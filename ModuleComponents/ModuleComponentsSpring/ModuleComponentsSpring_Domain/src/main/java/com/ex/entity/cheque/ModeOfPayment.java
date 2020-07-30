package com.ex.entity.cheque;

import java.util.Currency;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ModeOfPayment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String payeeName;
	private double amount;
	private Currency currency;
	private boolean sent;
	private boolean voided;
	private boolean cleared;
}
