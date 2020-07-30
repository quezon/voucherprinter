package com.ex.entity;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Gabriel Ferrer Can have as many types of expenses per cheque voucher
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ChequeVoucher extends Voucher {
	List<Cheque> cheques;
}
