package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

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
    Button addClient;
    
 
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
    	Database.addClient(client);
    	try {
    		clientTable.setItems(ClientList.getClientList());
    	}catch(Exception error) {
    		System.out.println(error);
    	}
    	
    }
  
}