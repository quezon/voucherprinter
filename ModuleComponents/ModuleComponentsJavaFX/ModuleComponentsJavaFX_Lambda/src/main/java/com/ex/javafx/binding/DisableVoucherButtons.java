package com.ex.javafx.binding;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class DisableVoucherButtons {
	private BooleanBinding disableVoucherButtons;

	public DisableVoucherButtons(ChoiceBox[] choices, DatePicker[] dates, DoubleProperty[] totals) {
		// true if at least one total equals zero
		for (int i = 0; i < totals.length; i++) {
			if (i == 0) {
				disableVoucherButtons = totals[i].isEqualTo(0.0, 0.0);
			} else {
				disableVoucherButtons = disableVoucherButtons.or(totals[i].isEqualTo(0.0, 0.0));
			}
		}

		// true if at least one total is not equal to any totals
		for (int j = 0; j < totals.length; j++) {
			disableVoucherButtons = disableVoucherButtons.or(totals[0].isNotEqualTo(totals[j]));
		}

		// check if all dates are not set
		for (int k = 0; k < dates.length; k++) {
			disableVoucherButtons = disableVoucherButtons.or(dates[k].valueProperty().isNull());
		}

		// check if no choice is chosen
		for (int l = 0; l < choices.length; l++) {
			disableVoucherButtons = disableVoucherButtons.or(choices[l].valueProperty().isNull());
		}

	}

	public BooleanBinding get() {
		return this.disableVoucherButtons;
	}
}

/*
 * BooleanBinding disableVoucherButtons = totalParticulars.isEqualTo(0.0, 0.0)
 * .or(totalPayments.isEqualTo(0.0, 0.0)) .or(totalDebits.isEqualTo(0.0,
 * 0.0)).or(totalCredits.isEqualTo(0.0, 0.0))
 * .or(Bindings.createBooleanBinding(() ->
 * amountCash.getText().trim().isEmpty(), amountCash.textProperty()))
 * .or(Bindings.createBooleanBinding(() ->
 * amountParticular.getText().trim().isEmpty(),
 * amountParticular.textProperty()))
 * .or(totalParticulars.isNotEqualTo(totalPayments));
 * 
 */
