package com.ex.javafx.window.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginWindowController implements Initializable {

	@FXML
	private TextField usernameBox;
	@FXML
	private TextField passwordBox;
	@FXML
	private Button loginBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginBtn.disableProperty().bind(usernameBox.textProperty().isNull().or(passwordBox.textProperty().isNull()));
	}

	public void login(MouseEvent event) {
		String username = usernameBox.getText();
		String password = passwordBox.getText();
	}

}
