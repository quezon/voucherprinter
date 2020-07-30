package com.ex.dto;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class DateRange {
	private ZonedDateTime start;
	private ZonedDateTime end;
	private String dateName;
}
