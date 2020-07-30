package com.ex.javafx.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DoubleChangeListener implements ChangeListener<Number> {
	double amounts[] = { 0.0, 0.0 };
	boolean disable = true;

	public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
		System.out.println("the value has changed from " + oldValue + " to " + newValue);
		for (int i = 0; i < amounts.length; i++) {
			if (amounts[i] == 0.0 || amounts[0] != amounts[i]) {
				disable = true;
				break;
			}
			disable = false;
		}

		if (0.0 > 0.0) {
			// do not add particular to table
			System.out.println("particulars greater than payment"); // substitute with alert
		}
	}
}