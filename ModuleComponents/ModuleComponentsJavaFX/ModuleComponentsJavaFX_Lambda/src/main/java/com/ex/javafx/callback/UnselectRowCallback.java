package com.ex.javafx.callback;

import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class UnselectRowCallback<T> implements Callback<TableView<T>, TableRow<T>> {

	TableView<T> table;

	public UnselectRowCallback(TableView<T> table) {
		this.table = table;
	}

	public TableRow<T> call(TableView<T> tableView2) {
		final TableRow<T> row = new TableRow<>();
		row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				final int index = row.getIndex();
				if (index >= 0 && index < table.getItems().size() && table.getSelectionModel().isSelected(index)) {
					table.getSelectionModel().clearSelection();
					event.consume();
				}
			}
		});
		return row;
	}
}

//debitTable.setRowFactory(new Callback<TableView<Debit>, TableRow<Debit>>() {
//	@Override
//	public TableRow<Debit> call(TableView<Debit> tableView2) {
//		final TableRow<Debit> row = new TableRow<>();
//		row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				final int index = row.getIndex();
//				if (index >= 0 && index < debitTable.getItems().size()
//						&& debitTable.getSelectionModel().isSelected(index)) {
//					debitTable.getSelectionModel().clearSelection();
//					event.consume();
//				}
//			}
//		});
//		return row;
//	}
//});