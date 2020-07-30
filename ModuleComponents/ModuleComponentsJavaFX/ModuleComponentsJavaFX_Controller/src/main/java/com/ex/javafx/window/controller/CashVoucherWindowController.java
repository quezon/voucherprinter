package com.ex.javafx.window.controller;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import com.ex.dto.CashVoucher;
import com.ex.dto.Credit;
import com.ex.dto.Debit;
import com.ex.dto.Particular;
import com.ex.javafx.ReadProperties;
import com.ex.javafx.binding.DisableVoucherButtons;
import com.ex.javafx.callback.UnselectRowCallback;
import com.ex.javafx.control.CustomAlert;
import com.ex.javafx.data.PayeeList;
import com.ex.javafx.listener.AmountChangeListener;
import com.ex.javafx.listener.DCChoiceBoxChangeListener;
import com.ex.javafx.listener.DCTableSelectionChangeListener;
import com.ex.javafx.service.VoucherService;
import com.ex.javafx.window.SceneBuilder;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CashVoucherWindowController implements Initializable {
	CashVoucher cav = new CashVoucher();
	VoucherService<CashVoucher> vs = new VoucherService<CashVoucher>(cav);

	// observable values
	// aggregate value observables
	DoubleProperty totalPayments = new SimpleDoubleProperty(0.0);
	DoubleProperty totalParticulars = new SimpleDoubleProperty(0.0);
	DoubleProperty totalDebits = new SimpleDoubleProperty(0.0);
	DoubleProperty totalCredits = new SimpleDoubleProperty(0.0);
	// table observables
	ObservableList<Particular> particulars = FXCollections.observableArrayList();
	ObservableList<Debit> debits = FXCollections.observableArrayList();
	ObservableList<Credit> credits = FXCollections.observableArrayList();
	// choice box observables
	ObservableList<String> dc = FXCollections.observableArrayList();
	ObservableList<String> documents = FXCollections.observableArrayList();
	ObservableList<String> accounts = FXCollections.observableArrayList();
	ObservableList<String> payees = FXCollections.observableArrayList();
	CustomAlert calert = new CustomAlert();
	// buttons to persist/modify voucher
	@FXML
	private Button saveBtn;
	@FXML
	private Button savePrintBtn;
	@FXML
	private Button voidBtn;
	@FXML
	private Button sendBtn;
	// cash
	@FXML
	private TextField amountPayment = new TextField();
	// voucher
	// main voucher info
	@FXML
	private ChoiceBox<String> payeeBox = new ChoiceBox<String>();
	@FXML
	private DatePicker voucherDate = new DatePicker();
	// one particular
	@FXML
	private TextField refParticular;
	@FXML
	private ChoiceBox<String> docParticular;
	@FXML
	private TextArea descriptionParticular;
	@FXML
	private TextField amountParticular;
	@FXML
	private Button addParticularBtn;
	// particular table
	@FXML
	private Button removeParticularBtn;
	@FXML
	private Button editParticularBtn;
	@FXML
	private TableView<Particular> particularTable;
	@FXML
	private TableColumn<Particular, String> particularsRef;
	@FXML
	private TableColumn<Particular, String> particularsDoc;
	@FXML
	private TableColumn<Particular, String> particularsDescription;
	@FXML
	private TableColumn<Particular, Double> particularsAmount;
	// journal entries
	@FXML
	private ChoiceBox<String> accountDC;
	@FXML
	private ChoiceBox<String> accountJE;
	@FXML
	private TextField amountJE;
	@FXML
	private Button addJEBtn;
	@FXML
	private Button removeJEBtn;
	@FXML
	private Button editJEBtn;
	@FXML
	private TableView<Debit> debitTable;
	@FXML
	private TableColumn<Debit, String> debitAccount;
	@FXML
	private TableColumn<Debit, Double> debitAmount;
	@FXML
	private TableView<Credit> creditTable;
	@FXML
	private TableColumn<Credit, String> creditAccount;
	@FXML
	private TableColumn<Credit, Double> creditAmount;

	// arrays of observables, widgets
	DoubleProperty[] totals = { totalPayments, totalParticulars, totalDebits, totalCredits };

	public void initialize(URL arg0, ResourceBundle arg1) {
		DatePicker[] dates = { voucherDate };
		ChoiceBox[] choiceBoxes = { payeeBox };

		dc.addAll("Debit", "Credit");
		documents.addAll("Purchase Invoice", "Utility Bill");
		accounts.addAll("Inventory", "Salaries Expense", "Accounts Payable");
		payees.addAll(new PayeeList().get());

		amountPayment.setText("0.0");
		amountParticular.setText("0.0");
		amountJE.setText("0.0");
		accountDC.setItems(dc);
		accountJE.setItems(accounts);
		docParticular.setItems(documents);
		payeeBox.setItems(payees);
		accountJE.getSelectionModel().select("Inventory");
		accountDC.getSelectionModel().select("Debit");
		docParticular.getSelectionModel().select("Purchase Invoice");

		particularsRef.setCellValueFactory(new PropertyValueFactory<Particular, String>("docNumber"));
		particularsDoc.setCellValueFactory(new PropertyValueFactory<Particular, String>("docReferred"));
		particularsDescription.setCellValueFactory(new PropertyValueFactory<Particular, String>("description"));
		particularsAmount.setCellValueFactory(new PropertyValueFactory<Particular, Double>("amount"));
		particularTable.setItems(particulars);
		debitAccount.setCellValueFactory(new PropertyValueFactory<Debit, String>("debit"));
		debitAmount.setCellValueFactory(new PropertyValueFactory<Debit, Double>("amount"));
		debitTable.setItems(debits);
		creditAccount.setCellValueFactory(new PropertyValueFactory<Credit, String>("credit"));
		creditAmount.setCellValueFactory(new PropertyValueFactory<Credit, Double>("amount"));
		creditTable.setItems(credits);

		amountPayment.textProperty()
				.addListener(new AmountChangeListener(totalPayments, amountPayment, "\\d{0,7}([\\.]\\d{0,2})?"));
		amountParticular.textProperty()
				.addListener(new AmountChangeListener(amountParticular, "\\d{0,7}([\\.]\\d{0,2})?"));
		amountJE.textProperty().addListener(new AmountChangeListener(amountJE, "\\d{0,7}([\\.]\\d{0,2})?"));

		accountDC.getSelectionModel().selectedItemProperty()
				.addListener(new DCChoiceBoxChangeListener(accounts, accountJE));

		addParticularBtn.disableProperty().bind(Bindings
				.createBooleanBinding(() -> refParticular.getText().trim().isEmpty(), refParticular.textProperty())
				.or(Bindings.createBooleanBinding(() -> descriptionParticular.getText().trim().isEmpty(),
						descriptionParticular.textProperty()))
				.or(Bindings.createBooleanBinding(
						() -> Double.parseDouble(amountParticular.getText().replaceAll("[^0-9.]", "")) == 0.0,
						amountParticular.textProperty())));

		saveBtn.disableProperty().bind(new DisableVoucherButtons(choiceBoxes, dates, totals).get());
		savePrintBtn.disableProperty().bind(new DisableVoucherButtons(choiceBoxes, dates, totals).get());

		removeParticularBtn.disableProperty().bind(Bindings.isEmpty(particulars));
		editParticularBtn.disableProperty().bind(Bindings.isEmpty(particulars));

		addJEBtn.disableProperty()
				.bind(Bindings.createBooleanBinding(
						() -> Double.parseDouble(amountJE.getText().replaceAll("[^0-9.]", "")) == 0.0,
						amountJE.textProperty()));
		removeJEBtn.disableProperty().bind(Bindings.isEmpty(debits).and(Bindings.isEmpty(credits)));
		editJEBtn.disableProperty().bind(Bindings.isEmpty(debits).and(Bindings.isEmpty(credits)));

		debitTable.getSelectionModel().selectedIndexProperty()
				.addListener(new DCTableSelectionChangeListener<Credit, Debit>(creditTable, debitTable));

		creditTable.getSelectionModel().selectedIndexProperty()
				.addListener(new DCTableSelectionChangeListener<Debit, Credit>(debitTable, creditTable));

		debitTable.setRowFactory(new UnselectRowCallback<Debit>(debitTable));
		creditTable.setRowFactory(new UnselectRowCallback<Credit>(creditTable));
	}

	public void addParticular(MouseEvent event) {
		System.out.println(voucherDate.valueProperty().get());
		System.out.println(payeeBox.valueProperty().get());

		String particularText = amountParticular.getText() == null || amountParticular.getText().length() == 0 ? "0.0"
				: amountParticular.getText();
		double particularAmount = Double.parseDouble(particularText);
		double particularAmounts = totalParticulars.doubleValue() + particularAmount;

		Particular particular = new Particular();
		particular.setDocNumber(refParticular.getText());
		particular.setDocReferred(docParticular.getValue());
		particular.setDescription(descriptionParticular.getText());
		particular.setAmount(particularAmount);

		if (particularAmounts <= totalPayments.doubleValue()) {
			// add particular to table
			particulars.add(particular);
			totalParticulars.set(particularAmounts);
			refParticular.clear();
			descriptionParticular.clear();
			amountParticular.setText("0.0");
		} else {
			if (totalPayments.doubleValue() > 0.0) {
				calert.showErrorAlert("Add Particular Error", "Particulars amount exceeding Payment Amount",
						"Particular not added!");

			} else {
				calert.showErrorAlert("Add Particular Error", "Payment Amount equals zero", "Particular not added!");
			}
		}

	}

	public void removeParticular(MouseEvent event) {
		Particular selectedItem = particularTable.getSelectionModel().getSelectedItem();
		particularTable.getItems().remove(selectedItem);
		totalParticulars.set(totalParticulars.doubleValue() - selectedItem.getAmount());
	}

	public void editParticular(MouseEvent event) {
		Particular selectedItem = particularTable.getSelectionModel().getSelectedItem();
		refParticular.setText(selectedItem.getDocNumber());
		docParticular.setValue(selectedItem.getDocReferred());
		descriptionParticular.setText(selectedItem.getDescription());
		amountParticular.setText(String.valueOf(selectedItem.getAmount()));
		particularTable.getItems().remove(selectedItem);
		totalParticulars.set(totalParticulars.doubleValue() - selectedItem.getAmount());
	}

	public void addAccount(MouseEvent event) {
		String accountAmountText = amountJE.getText() == null || amountJE.getText().length() == 0 ? "0.0"
				: amountJE.getText();
		double accountAmount = Double.parseDouble(accountAmountText);
		double accountAmounts = 0.0;
		Debit debit = new Debit();
		Credit credit = new Credit();

		if (accountDC.getValue().equals("Debit")) {
			accountAmounts = totalDebits.doubleValue() + accountAmount;

			debit.setAmount(accountAmount);
			debit.setChartOfAccountCode(accountJE.getValue());
			debit.setDebit(accountJE.getValue());

			if (accountAmounts <= totalPayments.doubleValue()) {
				// add debits to table
				debits.add(debit);
				totalDebits.set(accountAmounts);
				amountJE.setText("0.0");
			} else {
				if (totalPayments.doubleValue() > 0.0) {
					calert.showErrorAlert("Add Debit Error", "Debits amount exceeding Payment Amount",
							"Debit not added!");
				} else {
					calert.showErrorAlert("Add Debit Error", "Payment amount equals zero", "Debit not added!");
				}
			}

		} else {
			accountAmounts = totalCredits.doubleValue() + accountAmount;

			credit.setAmount(accountAmount);
			credit.setChartOfAccountCode(accountJE.getValue());
			credit.setCredit(accountJE.getValue());

			if (accountAmounts <= totalPayments.doubleValue()) {
				// add credits to table
				credits.add(credit);
				totalCredits.set(accountAmounts);
				amountJE.setText("0.0");
			} else {
				if (totalPayments.doubleValue() > 0.0) {
					calert.showErrorAlert("Add Credit Error", "Credit amount exceeding Payment Amount",
							"Credit not added!");
				} else {
					calert.showErrorAlert("Add Credit Error", "Payment amount equals zero", "Credit not added!");
				}
			}
		}

	}

	public void removeAccount(MouseEvent event) {
		Debit debit = debitTable.getSelectionModel().getSelectedItem();
		Credit credit = creditTable.getSelectionModel().getSelectedItem();
		if (debit != null) {
			debitTable.getItems().remove(debit);
			totalDebits.set(totalDebits.doubleValue() - debit.getAmount());
		}
		if (credit != null) {
			creditTable.getItems().remove(credit);
			totalCredits.set(totalCredits.doubleValue() - credit.getAmount());
		}

	}

	public void editAccount(MouseEvent event) {
		Debit debit = debitTable.getSelectionModel().getSelectedItem();
		Credit credit = creditTable.getSelectionModel().getSelectedItem();

		if (debit != null) {
			accountDC.getSelectionModel().select("Debit");
			accountJE.setValue(debit.getDebit());
			amountJE.setText(String.valueOf(debit.getAmount()));
			debitTable.getItems().remove(debit);
			totalDebits.set(totalDebits.doubleValue() - debit.getAmount());
		}

		if (credit != null) {
			accountDC.getSelectionModel().select("Credit");
			accountJE.setValue(credit.getCredit());
			amountJE.setText(String.valueOf(credit.getAmount()));
			creditTable.getItems().remove(credit);
			totalCredits.set(totalCredits.doubleValue() - credit.getAmount());
		}
	}

	public void saveVoucher(MouseEvent event) throws IOException {
		ZonedDateTime zdt = voucherDate.getValue().atStartOfDay(ZoneId.systemDefault());
		Long voucherId = vs.saveVoucher(particulars, debits, credits, zdt, payeeBox.getValue());
		if (voucherId != null) {
			calert.showInfoAlert("Voucher saved", "Saved Cash Voucher with id of " + voucherId);
			new SceneBuilder().run(ReadProperties.fxmlcav, (Stage) saveBtn.getScene().getWindow());
		} else {
			calert.showInfoAlert("Voucher unsaved", "Please try hitting save button again");
		}
	}

	public void savePrintVoucher(MouseEvent event) {
		ZonedDateTime zdt = voucherDate.getValue().atStartOfDay(ZoneId.systemDefault());
		Long voucherId = vs.saveVoucher(particulars, debits, credits, zdt, payeeBox.getValue());
		if (voucherId != null) {
			calert.showInfoAlert("Voucher saved", "Saved Cash Voucher with id of " + voucherId);
			calert.showConfAlert("Voucher printing", "Are you sure you want to print voucher?").showAndWait()
			.ifPresent(type -> {
				if (type == ButtonType.OK) {
					calert.showInfoAlert("Voucher printing", "Please insert paper before pressing OK");
					try {
						if (vs.printVoucher(voucherId)) {
							calert.showInfoAlert("Voucher printed successfully", "Press OK to Clear Fields");
						} else {
							calert.showInfoAlert("Voucher not printed, but saved", "Press OK to Clear Fields");
						}
						new SceneBuilder().run(ReadProperties.fxmlcav, (Stage) savePrintBtn.getScene().getWindow());
					} catch (IOException e) {
					}
				} else if (type == ButtonType.NO) {
				}
			});

		}
	}

}
