package com.ex.javafx.listener;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class AmountChangeListener implements ChangeListener<String> {
	private String stringMatcher;
	private DoubleProperty total;
	private TextField amount;

	public AmountChangeListener(DoubleProperty total, TextField amount, String matcher) {
		this.total = total;
		this.amount = amount;
		this.stringMatcher = matcher;
	}

	public AmountChangeListener(TextField amount, String matcher) {
		this.amount = amount;
		this.stringMatcher = matcher;
	}

	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		double newDouble;

		if (!newValue.matches(stringMatcher) || newValue.length() == 0) {
			amount.setText(oldValue);
		} else {
			newDouble = newValue == null || newValue.length() == 0 ? 0.0 : Double.parseDouble(newValue);
			amount.setText(newValue);
			if (total != null) {
				total.set(newDouble);
			}
		}
	}
}
