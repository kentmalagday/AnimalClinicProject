package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Client {

    @FXML
    private TextField clientLastname;

    @FXML
    private TextField clientFirstname;

    @FXML
    private TextField clientAge;

    @FXML
    private TextField clientContactNo;

    @FXML
    private TextField clientEmail;

    @FXML
    private TextField clientAddress;

    @FXML
    private Button petButton;

    
    public void addPetbtn() throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("addpet.fxml"));
    	 Stage window = (Stage) petButton.getScene().getWindow();
    	 window.setTitle("Add Pet");
    	 window.setScene(new Scene(root, 1074, 748 ));
    	
    }

}