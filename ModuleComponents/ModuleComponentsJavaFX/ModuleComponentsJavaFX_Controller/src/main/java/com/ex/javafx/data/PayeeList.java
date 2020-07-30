package com.ex.javafx.data;

import lombok.Data;

@Data
public class PayeeList {
	public String[] get() {
		String[] payees = { "Juan Villa", "Jose Tolentino", "Raul Rodriguez", "Natalia Gomez" };
		return payees;
	}
}
