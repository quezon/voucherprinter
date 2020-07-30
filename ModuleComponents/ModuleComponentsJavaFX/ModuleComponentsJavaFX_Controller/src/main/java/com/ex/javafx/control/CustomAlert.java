package com.ex.javafx.control;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class CustomAlert {
	Alert confAlert = new Alert(AlertType.CONFIRMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	
	ButtonType okButton = new ButtonType("Yes", ButtonData.YES);
	ButtonType noButton = new ButtonType("No", ButtonData.NO);
	
	public void showInfoAlert(String title, String header) {
		infoAlert.setTitle(title);
		infoAlert.setHeaderText(header);
		infoAlert.showAndWait();
	}
	
	public void showErrorAlert(String title, String header) {
		errorAlert.setTitle(title);
		errorAlert.setHeaderText(header);
		errorAlert.showAndWait();
	}
	
	public void showErrorAlert(String title, String header, String content) {
		errorAlert.setTitle(title);
		errorAlert.setHeaderText(header);
		errorAlert.setContentText(content);
		errorAlert.showAndWait();
	}
	
	public Alert showConfAlert(String title, String header) {
		confAlert.setTitle(title);
		confAlert.setHeaderText(header);
		return confAlert;
	}
}
