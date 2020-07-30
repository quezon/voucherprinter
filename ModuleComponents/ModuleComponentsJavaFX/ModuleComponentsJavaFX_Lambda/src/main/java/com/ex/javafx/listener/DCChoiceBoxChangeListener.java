package com.ex.javafx.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class DCChoiceBoxChangeListener implements ChangeListener<String> {

	ObservableList<String> accounts;
	ChoiceBox<String> accountJE;

	public DCChoiceBoxChangeListener(ObservableList<String> accounts, ChoiceBox<String> accountJE) {
		this.accounts = accounts;
		this.accountJE = accountJE;
	}

	@Override
	public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		if (newValue.equals("Debit")) {
			accounts.clear();
			accounts.addAll("Inventory", "Salaries Expense", "Accounts Payable");
			accountJE.getSelectionModel().select("Inventory");
		} else {
			accounts.clear();
			accounts.addAll("Cash on Hand");
			accountJE.getSelectionModel().select("Cash on Hand");
		}

	}

}
