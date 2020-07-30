package com.ex.javafx.window;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneBuilder {
	public Stage run(String fxmlFile, String title) throws IOException {
		FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
		Parent root = (Parent) fmxlLoader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(title);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		return stage;
	}

	public void run(String fxmlFile, Stage stage) throws IOException {
		FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
		Parent root = (Parent) fmxlLoader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
}
