package com.ex.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author Gabriel Ferrer
 * 
 *
 */

@Entity
@Data 
@EqualsAndHashCode(callSuper = true)
public class CashVoucher extends Voucher {

}
