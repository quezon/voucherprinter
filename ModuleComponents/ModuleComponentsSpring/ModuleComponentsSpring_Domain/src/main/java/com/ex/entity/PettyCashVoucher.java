package com.ex.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PettyCashVoucher extends Voucher {
	
}
