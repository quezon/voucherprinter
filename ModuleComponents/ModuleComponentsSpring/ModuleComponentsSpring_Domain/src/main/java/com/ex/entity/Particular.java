package com.ex.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data public class Particular {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String description;
	private String docReferred;
	private String docNumber;
	private double amount;
}
