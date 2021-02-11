package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Medication implements Initializable{

    @FXML
    TableColumn<?, ?> description;

    @FXML
    TableColumn<?, ?> dosage;

    @FXML
    TableColumn<?, ?> status;

    @FXML
    TextArea textArea_Description;

    @FXML
    TextField textField_dosage;

    @FXML
    Button btn_back;

    @FXML
    ComboBox<String> comboBox_status;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Taken", "Not Taken"); 
		comboBox_status.setItems(list);
    }
    
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }
}