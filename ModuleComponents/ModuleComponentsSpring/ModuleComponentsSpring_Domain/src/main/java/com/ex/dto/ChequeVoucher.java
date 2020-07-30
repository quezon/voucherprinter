package com.ex.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Gabriel Ferrer Can have as many types of expenses per cheque voucher
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChequeVoucher extends Voucher {
	List<Cheque> cheques;
}
