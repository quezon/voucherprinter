package com.ex.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Particular {
	private long id;
	private String description;
	private String docReferred;
	private String docNumber;
	private double amount;
}
