package com.ex.javafx.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;

public class RowSelectChangeListener implements ChangeListener<Number> {

	private TableView tbl;

	public RowSelectChangeListener(TableView tbl) {
		this.tbl = tbl;
	}

	public void changed(ObservableValue ov, Number oldVal, Number newVal) {

		int ix = newVal.intValue();

		this.tbl.getSelectionModel().clearSelection(ix);

	}
}
