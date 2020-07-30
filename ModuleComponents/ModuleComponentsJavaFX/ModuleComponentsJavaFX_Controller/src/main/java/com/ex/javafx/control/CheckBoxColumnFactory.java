package com.ex.javafx.control;

import com.ex.dto.ChequeOfVoucher;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CheckBoxColumnFactory
		implements Callback<TableColumn.CellDataFeatures<ChequeOfVoucher, CheckBox>, ObservableValue<CheckBox>> {

	private ObservableList<ChequeOfVoucher> covs;
	private ObservableSet<Long> selectedRows;

	public CheckBoxColumnFactory(ObservableList<ChequeOfVoucher> covs, ObservableSet<Long> selectedRows) {
		this.covs = covs;
		this.selectedRows = selectedRows;
	}

	public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<ChequeOfVoucher, CheckBox> arg0) {
		ChequeOfVoucher cov = arg0.getValue();
		CheckBox checkBox = new CheckBox();

		checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
			if (newVal) {
				this.selectedRows.add(cov.getId());
			} else {
				this.selectedRows.remove(cov.getId());
			}
		});

		return new SimpleObjectProperty<CheckBox>(checkBox);

	}

}

//CheckBoxColumn