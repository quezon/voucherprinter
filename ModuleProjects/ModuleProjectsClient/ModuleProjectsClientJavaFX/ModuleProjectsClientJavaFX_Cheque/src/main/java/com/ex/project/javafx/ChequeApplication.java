package com.ex.project.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


@SpringBootApplication
public class ChequeApplication extends Application {
	public void start(Stage stage) throws Exception {
		String fxmlFile = "/fxml/MainWindow.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResource(fxmlFile));

		Scene scene = new Scene(rootNode);
		// scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("Voucher Program");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}

}