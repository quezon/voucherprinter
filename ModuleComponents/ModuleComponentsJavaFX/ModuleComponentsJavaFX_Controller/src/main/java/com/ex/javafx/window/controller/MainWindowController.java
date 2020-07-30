package com.ex.javafx.window.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import com.ex.javafx.window.SceneBuilder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
	String fxmlcav;
	String fxmlchv;
	String fxmlpcv;
	String fxmllvx;
	String fxmlccw;
	String fxmlmnu;
	String fxmlrgw;
	String fxmllgw;
	InputStream inputStream;
	Image chequeImage = new Image("/images/cheque.png");

	@FXML
	ImageView chequeImageView;

	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			fxmlcav = prop.getProperty("fxmlcav");
			fxmlchv = prop.getProperty("fxmlchv");
			fxmlpcv = prop.getProperty("fxmlpcv");
			fxmllvx = prop.getProperty("fxmllvx");
			fxmlccw = prop.getProperty("fxmlccw");
			fxmlmnu = prop.getProperty("fxmlmnu");
			fxmlrgw = prop.getProperty("fxmlrgw");
			fxmllgw = prop.getProperty("fxmllgw");
			
		} catch (Exception e) {
			
		}
		chequeImageView.setImage(chequeImage);
	}

	public void closeApp(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	public void newCashVoucher(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmlcav, "Cash Voucher Form");
	}

	public void newChequeVoucher(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmlchv, "Cheque Voucher Form");
	}

	public void newPettyCashVoucher(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmlpcv, "Petty Cash Voucher Form");
	}

	public void listOfVouchers(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmllvx, "List of Vouchers");
	}

	public void clearCheques(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmlccw, "Clear Cheques");
	}

	public void manageUsers(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmlmnu, "Manage Users");
	}

	public void editMyAccount(ActionEvent event) throws IOException {
		new SceneBuilder().run(fxmlrgw, "Edit My Account");
	}

	public void logoutAccount(ActionEvent event) throws IOException {
		Stage mainStage = (Stage) chequeImageView.getScene().getWindow();
		mainStage.close();
		new SceneBuilder().run(fxmllgw, "Login to Voucher App");
	}

	public void openWebpage(ActionEvent event) {
		// this doesn't work!!
//		try {
//			Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		try {
//			new ProcessBuilder("x-www-browser", "https://www.google.com").start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		Display.getInstance().execute("http://www.google.com/");
	}
}
