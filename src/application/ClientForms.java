package application;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;

public class ClientForms implements Initializable {

	@FXML
	TextField clientLastname;

	@FXML
	TextField clientFirstname;

	@FXML
	TextField clientAge;

	@FXML
	TextField clientContactNo;

	@FXML
	TextField clientEmail;

	@FXML
	TextField clientAddress;

	@FXML
	Button petButton;

	@FXML
	TableView<Client> clientTable;
	@FXML
	TableColumn<Client, Integer> id;
	@FXML
	TableColumn<Client, String> lastName;
	@FXML
	TableColumn<Client, String> firstName;
	@FXML
	TableColumn<Client, Integer> age;
	@FXML
	TableColumn<Client, String> contact;
	@FXML
	TableColumn<Client, String> email;
	@FXML
	TableColumn<Client, String> address;
	@FXML
	Button clearButton;

	@FXML
	Button addClient;

	@FXML
	Button deleteClientBtn;

	TableViewSelectionModel<Client> selectionModel;
	TextField[] emptyTextFields = new TextField[6];
	Client selectedClient;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			clientTable.setItems(ClientList.getClientList());
		} catch (Exception e) {
			System.out.println(e);
		}
		clientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("ID"));
		lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
		firstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
		age.setCellValueFactory(new PropertyValueFactory<Client, Integer>("age"));
		contact.setCellValueFactory(new PropertyValueFactory<Client, String>("contact"));
		email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));

		selectionModel = clientTable.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);

	}

	public void addPetbtn() throws IOException {
		if (selectedClient != null) {
			AddPet.selectedClient = selectedClient;
		} else {
			System.out.println("Select A client first!");
			return;
		}
		Parent root = FXMLLoader.load(getClass().getResource("addpet.fxml"));
		Stage window = (Stage) petButton.getScene().getWindow();
		window.setTitle("Add Pet");
		window.setScene(new Scene(root, 1074, 748));

	}

	public void addClient() throws Exception {
		if (textFieldsEmpty()) {
			for (int i = 0; i < emptyTextFields.length; i++) {
				if (emptyTextFields[i] == null) {
					break;
				}
				emptyTextFields[i].setText("Please input something before saving!");
			}
		} else {
			if (!checkIfValid()) {
				return;
			}
			Client client = new Client();
			client.setLastName(clientLastname.getText());
			client.setFirstName(clientFirstname.getText());
			client.setAge(Integer.parseInt(clientAge.getText()));
			client.setContact(clientContactNo.getText());
			client.setEmail(clientEmail.getText());
			client.setAddress(clientAddress.getText());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Client");
			alert.setHeaderText("Client record is added succesfully!");
			alert.showAndWait();
			if (clientTable.getSelectionModel().isEmpty()) {
				Database.addClient(client);
				try {
					clientTable.setItems(ClientList.getClientList());
					clearTextFields();
				} catch (Exception error) {
					System.out.println(error);
				}
			} else {
				try {
					Database.updateClient(client, selectedClient);
					clientTable.setItems(ClientList.getClientList());
					clearTextFields();
				} catch (Exception error) {
					System.out.println(error);
				}
			}
		}
	}

	public void getSelection(MouseEvent e) {
		Node source = e.getPickResult().getIntersectedNode();
		if (selectionModel.getSelectedItem() != null) {
			selectedClient = selectionModel.getSelectedItem();
			clientLastname.setText(selectedClient.getLastName());
			clientFirstname.setText(selectedClient.getFirstName());
			clientAge.setText(Integer.toString(selectedClient.getAge()));
			clientContactNo.setText(selectedClient.getContact());
			clientEmail.setText(selectedClient.getEmail());
			clientAddress.setText(selectedClient.getAddress());
		}
		while (source != null && !(source instanceof TableRow)) {
			source = source.getParent();
		}

		if (source == null || (source instanceof TableRow && ((TableRow) source).isEmpty())) {
			clientTable.getSelectionModel().clearSelection();
			selectedClient = null;
			clearTextFields();
		}
		System.out.println("Clicked!");
	}

	public void clearTextFields() {
		clientLastname.setText("");
		clientFirstname.setText("");
		clientAge.setText("");
		clientContactNo.setText("");
		clientEmail.setText("");
		clientAddress.setText("");
	}

	public boolean textFieldsEmpty() {
		int i = 0;
		if (clientLastname.getText().isEmpty()) {
			System.out.println("EMPTY");
			emptyTextFields[i] = clientLastname;
			i++;
		}
		if (clientFirstname.getText().isEmpty()) {
			emptyTextFields[i] = clientFirstname;
			i++;
		}
		if (clientAge.getText().isEmpty()) {
			emptyTextFields[i] = clientAge;
			i++;
		}
		if (clientContactNo.getText().isEmpty()) {
			emptyTextFields[i] = clientContactNo;
			i++;
		}
		if (clientEmail.getText().isEmpty()) {
			emptyTextFields[i] = clientEmail;
			i++;
		}
		if (clientAddress.getText().isEmpty()) {
			emptyTextFields[i] = clientAddress;
			i++;
		}
		if (i > 0) {
			System.out.println("There are empty textfields");
			return true;
		} else {
			System.out.println("No Empty TextFields!");
			Arrays.fill(emptyTextFields, null);
		}
		return false;
	}

	public boolean checkIfValid() {
		try {
			Integer.parseInt(clientAge.getText());

		} catch (Exception e) {
			System.out.println("Age should be a number");
			clientAge.setText("Invalid!");
			return false;
		}
		try {
			Integer.parseInt(clientContactNo.getText());
			return true;
		} catch (Exception e) {
			System.out.println("Contact Number not valid!");
			clientContactNo.setText("Invalid!");
			return false;
		}
	}

	public void clearBtn() {
		clearTextFields();
		clientTable.getSelectionModel().clearSelection();
		selectedClient = null;
	}

	public void backButton_Clicked() throws IOException {
		Main m = new Main();
		m.changeScene("home.fxml", "Home", 1074, 748);
	}

	public void deleteClientBtn() {
		if (clientTable.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("No client selected.");
			alert.showAndWait();
		} else {
			Alert check = new Alert(AlertType.CONFIRMATION);
			check.setTitle("Delete Client");
			check.setHeaderText(null);
			check.setContentText("Are you sure you want to delete this client data?");
			Optional<ButtonType> result = check.showAndWait();
			if (result.get() == ButtonType.OK) {
				Client client = new Client();
				client.setLastName(clientLastname.getText());
				client.setFirstName(clientFirstname.getText());
				client.setAge(Integer.parseInt(clientAge.getText()));
				client.setContact(clientContactNo.getText());
				client.setEmail(clientEmail.getText());
				client.setAddress(clientAddress.getText());

				System.out.println("delete client ok.");
				Database.deleteClient(client, selectedClient);
				try {
					clientTable.setItems(ClientList.getClientList());
					clearTextFields();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}