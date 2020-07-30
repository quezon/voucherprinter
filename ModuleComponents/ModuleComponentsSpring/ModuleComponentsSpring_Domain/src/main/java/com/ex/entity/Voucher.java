package com.ex.entity;

import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Voucher implements Voucherable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Min(1) @Max(999999)
	private long id;
	@NotNull
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MMM dd, YYYY")
	private ZonedDateTime dateCreated;
	
	private String payee;
	private Currency currency;
	@OneToOne
	private JournalEntry journalEntry;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Particular> particulars;
	private String approvedBy;
	private String rejectedBy;
	private String preparedBy;
	private String voidedBy;
	private String certifiedBy;
	private String receivedBy;
	private String receivedFrom;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MMM dd, YYYY")
	private ZonedDateTime datePrinted;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MMM dd, YYYY")
	private ZonedDateTime dateApproved;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MMM dd, YYYY")
	private ZonedDateTime dateRejected;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MMM dd, YYYY")
	private ZonedDateTime dateSent;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MMM dd, YYYY")
	private ZonedDateTime dateVoided;
	
	private boolean printed;
	private boolean valid;
	private boolean voided;
}
