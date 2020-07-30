package com.ex.javafx.listener;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;

public class DCTableSelectionChangeListener<DC, CD> implements ChangeListener<Number> {
	private TableView<DC> firstTable;
	private TableView<CD> secondTable;

	public DCTableSelectionChangeListener(TableView<DC> firstTable, TableView<CD> secondTable) {
		super();
		this.firstTable = firstTable;
		this.secondTable = secondTable;
	}

	public void changed(ObservableValue<? extends Number> observable, Number oldIndex, Number newIndex) {
		DC dc = firstTable.getSelectionModel().getSelectedItem();
		if (dc != null) {
			Platform.runLater(() -> {
				secondTable.getSelectionModel().clearSelection();
			});
		}

		if (oldIndex.equals(newIndex)) {
			Platform.runLater(() -> {
				secondTable.getSelectionModel().clearSelection();
			});
		}

	}

}

/*
 * 
 * 
 * debitTable.getSelectionModel().selectedIndexProperty().addListener((obs,
 * oldIndex, newIndex) -> { Credit credit =
 * creditTable.getSelectionModel().getSelectedItem(); if (credit != null) {
 * Platform.runLater(() -> { debitTable.getSelectionModel().clearSelection();
 * }); }
 * 
 * if (oldIndex.equals(newIndex)) { Platform.runLater(() -> {
 * debitTable.getSelectionModel().clearSelection(); }); } });
 * 
 * 
 * 
 * 
 * 
 */
