package com.ex.javafx.window.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ex.dto.Voucher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ListOfVouchersWindowController implements Initializable {
	@FXML
	private Button editBtn;
	@FXML
	private Button voidBtn;
	@FXML
	private Button sendBtn;
	@FXML
	private TableView<Voucher> voucherTable;
	@FXML
	private TableColumn<Voucher, String> voucherStatus;
	@FXML
	private TableColumn<Voucher, String> voucherType;
	@FXML
	private TableColumn<Voucher, String> voucherId;
	@FXML
	private TableColumn<Voucher, String> voucherPayee;
	@FXML
	private TableColumn<Voucher, String> voucherAmount;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void editVoucher(MouseEvent event) {

	}

	public void voidVouchers(MouseEvent event) {

	}

	public void sendVouchers(MouseEvent event) {

	}
}
