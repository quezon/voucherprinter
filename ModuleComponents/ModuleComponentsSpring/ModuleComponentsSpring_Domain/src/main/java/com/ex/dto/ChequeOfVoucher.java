package com.ex.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ChequeOfVoucher extends Cheque {
	private Long chequeVoucherId;
}
