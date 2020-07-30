package com.ex.entity.cheque;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChequeOfVoucher extends Cheque {
	private Long chequeVoucherId;
}
