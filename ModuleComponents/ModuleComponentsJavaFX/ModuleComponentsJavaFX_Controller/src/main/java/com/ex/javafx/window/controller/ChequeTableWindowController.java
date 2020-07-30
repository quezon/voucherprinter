package com.ex.javafx.window.controller;

import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;
import java.util.TreeSet;

import com.ex.entity.cheque.ChequeOfVoucher;
import com.ex.javafx.ReadProperties;
import com.ex.javafx.control.CheckBoxColumnFactory;
import com.ex.javafx.control.CustomAlert;
import com.ex.resteasy.client.service.ChequeService;
import com.ex.resteasy.client.service.PrintingService;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ChequeTableWindowController implements Initializable {
	ChequeService chequeService = new ChequeService();
	PrintingService printingService = new PrintingService();
	ObservableList<ChequeOfVoucher> cheques = FXCollections.observableArrayList();
	ObservableSet<Long> selectedRows = FXCollections.observableSet(new TreeSet<Long>());
	CustomAlert calert = new CustomAlert();
	@FXML
	private TableView<ChequeOfVoucher> chequeTable;

	@FXML
	private TableColumn<ChequeOfVoucher, CheckBox> checkColumn;

	@FXML
	private TableColumn<ChequeOfVoucher, String> chequeVoucherNumber;

	@FXML
	private TableColumn<ChequeOfVoucher, String> bankName;

	@FXML
	private TableColumn<ChequeOfVoucher, String> routingNumber;

	@FXML
	private TableColumn<ChequeOfVoucher, String> chequeNumber;

	@FXML
	private TableColumn<ChequeOfVoucher, Double> chequeAmount;

	@FXML
	private Button printBtn;

	@FXML
	private Button voidBtn;

	@FXML
	private Button sendBtn;

	public void initialize(URL location, ResourceBundle resources) {
		ChequeOfVoucher cheqv = new ChequeOfVoucher();
		ChequeOfVoucher cheqv2 = new ChequeOfVoucher();
		ChequeOfVoucher cheqv3 = new ChequeOfVoucher();

		cheqv.setId(4L);
		cheqv.setAmount(90.00);
		cheqv.setBankName("Bank of America");
		cheqv.setBankRoutingNumber("990886879");
		cheqv.setChequeNumber("9886875");
		cheqv.setChequeVoucherId(2L);

		cheqv2.setId(2L);
		cheqv2.setAmount(90.00);
		cheqv2.setBankName("Chase Bank");
		cheqv2.setBankRoutingNumber("4525252");
		cheqv2.setChequeNumber("342342354");
		cheqv2.setChequeVoucherId(3L);

		cheqv3.setId(1L);
		cheqv3.setAmount(190.00);
		cheqv3.setBankName("Wells Fargo");
		cheqv3.setBankRoutingNumber("65766858");
		cheqv3.setChequeNumber("687589996");
		cheqv3.setChequeVoucherId(3L);

		cheques = FXCollections.observableArrayList(cheqv, cheqv2, cheqv3);

		checkColumn.setCellValueFactory(new CheckBoxColumnFactory(cheques, selectedRows));
		chequeVoucherNumber.setCellValueFactory(new PropertyValueFactory<ChequeOfVoucher, String>("chequeVoucherId"));
		bankName.setCellValueFactory(new PropertyValueFactory<ChequeOfVoucher, String>("bankName"));
		routingNumber.setCellValueFactory(new PropertyValueFactory<ChequeOfVoucher, String>("bankRoutingNumber"));
		chequeNumber.setCellValueFactory(new PropertyValueFactory<ChequeOfVoucher, String>("chequeNumber"));
		chequeAmount.setCellValueFactory(new PropertyValueFactory<ChequeOfVoucher, Double>("amount"));

		chequeTable.setItems(cheques);
		chequeTable.getSelectionModel().setSelectionMode(null);

		printBtn.disableProperty().bind(Bindings.isEmpty(selectedRows));
		voidBtn.disableProperty().bind(Bindings.isEmpty(selectedRows));
		sendBtn.disableProperty().bind(Bindings.isEmpty(selectedRows));
	}

	public void printCheques(MouseEvent event) {
		try {
			for (ChequeOfVoucher cheque : cheques) {
				if (selectedRows.contains(cheque.getId())) {
					String confMessage = String.format(ReadProperties.printConfCheque,
							cheque.getChequeNumber(), cheque.getBankName(), cheque.getBankRoutingNumber());
					String infoMessage = String.format(ReadProperties.printInfoCheque,
							cheque.getChequeNumber(), cheque.getBankName(), cheque.getBankRoutingNumber());
					calert.showConfAlert("Printing cheque", confMessage)
					.showAndWait().ifPresent(type -> {
						if (type == ButtonType.OK) {
							calert.showInfoAlert("Printing cheque", infoMessage);
							cheque.setPrinted(true);
							if (
								printingService.printDocument(
								ReadProperties.parentFolder_cheque,
								"cheque.pdf", cheque.getId())) {
								selectedRows.remove(cheque.getId());
								
								Platform.runLater(() -> {
									cheques.remove(cheque);
								});
							}
						} else if (type == ButtonType.NO) {
						}
					});
				}
			}
			chequeService.saveCheques(cheques);
		} catch (ConcurrentModificationException ex) {

		}

	}

	public void voidCheques(MouseEvent event) {
		try {
			calert.showConfAlert("Voiding cheques", "Are you sure you want to void the selected cheques?")
			.showAndWait().ifPresent(type -> {
				if (type == ButtonType.OK) {
					for (ChequeOfVoucher cheque : cheques) {
						if (selectedRows.contains(cheque.getId())) {
							cheque.setPrinted(true);
							selectedRows.remove(cheque.getId());
							// call rest api
							Platform.runLater(() -> {
								cheques.remove(cheque);
							});
						}
					}
					chequeService.saveCheques(cheques);
				} else if (type == ButtonType.NO) {
				}
			});
			calert.showInfoAlert("Voided cheques", "Cheques selected successfully voided.");
		} catch (ConcurrentModificationException ex) {

		}
	}

	public void sendCheques(MouseEvent event) {
		try {
			calert.showConfAlert("Sending cheques", "Are you sure you want to send the selected cheques?")
			.showAndWait().ifPresent(type -> {
				if (type == ButtonType.OK) {
					for (ChequeOfVoucher cheque : cheques) {
						if (selectedRows.contains(cheque.getId())) {
							cheque.setSent(true);
							selectedRows.remove(cheque.getId());

							Platform.runLater(() -> {
								cheques.remove(cheque);
							});
						}
					}
					chequeService.saveCheques(cheques);
				} else if (type == ButtonType.NO) {
				}
			});
			calert.showInfoAlert("Cheques approved for sending", "Cheques selected successfully marked for sending.");
		} catch (ConcurrentModificationException ex) {

		}
	}
}
