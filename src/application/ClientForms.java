package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ClientForms implements Initializable{

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
    
    TableViewSelectionModel<Client> selectionModel;
    
    Client selectedClient;
 
    @Override
    public void initialize(URL location, ResourceBundle resources){
   	 	try {
   	 		clientTable.setItems(ClientList.getClientList());
   	 	}catch(Exception e) {
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
    	 Parent root = FXMLLoader.load(getClass().getResource("addpet.fxml"));
    	 Stage window = (Stage) petButton.getScene().getWindow();
    	 window.setTitle("Add Pet");
    	 window.setScene(new Scene(root, 1074, 748 ));
    	
    }
    
    public void addClient() throws Exception{
    	Client client = new Client();
    	client.setLastName(clientLastname.getText());
    	client.setFirstName(clientFirstname.getText());
    	client.setAge(Integer.parseInt(clientAge.getText()));
    	client.setContact(clientContactNo.getText());
    	client.setEmail(clientEmail.getText());
    	client.setAddress(clientAddress.getText());
    	if(clientTable.getSelectionModel().isEmpty()) {
        	Database.addClient(client);
        	try {
        		clientTable.setItems(ClientList.getClientList());
        		clearTextFields();
        	}catch(Exception error) {
        		System.out.println(error);
        	}
    	}else {
    		try {
    			Database.updateClient(client, selectedClient);
        		clientTable.setItems(ClientList.getClientList());
        		clearTextFields();
    		}
    		catch(Exception error) {
    			System.out.println(error);
    		}
    	}
    }
    
    public void getSelection(MouseEvent e) {
    	Node source = e.getPickResult().getIntersectedNode();
    	if(selectionModel.getSelectedItem() != null) {
    		selectedClient = selectionModel.getSelectedItem();
       	 	clientLastname.setText(selectedClient.getLastName());
       	 	clientFirstname.setText(selectedClient.getFirstName());
       	 	clientAge.setText(Integer.toString(selectedClient.getAge()));
       	 	clientContactNo.setText(selectedClient.getContact());
       	 	clientEmail.setText(selectedClient.getEmail());
       	 	clientAddress.setText(selectedClient.getAddress());
    	}
    	while(source != null && !(source instanceof TableRow)) {
    		source = source.getParent();
    	}
    	
    	if(source == null || (source instanceof TableRow && ((TableRow) source).isEmpty())) {
    		clientTable.getSelectionModel().clearSelection();
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
	
	public void backButton_Clicked() throws IOException {
		Main m = new Main();
		m.changeScene("home.fxml", "home", 1074, 748);
	}
  
}