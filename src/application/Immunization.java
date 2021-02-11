package application;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Immunization implements Initializable{

    @FXML
    Text text_Petname;

    @FXML
    TextField textField_date;

    @FXML
    TextField textField_weight;

    @FXML
    Button btn_back;

    @FXML
    ComboBox<String> combobox_Against;

    @FXML
    ComboBox<String> combobox_Vet;

    @FXML
    TextField textField_nextdue;

    @FXML
    TableColumn<?, ?> date;

    @FXML
    TableColumn<?, ?> weight;

    @FXML
    TableColumn<?, ?> against;

    @FXML
    TableColumn<?, ?> veterinarian;

    @FXML
    TableColumn<?, ?> nextdue;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Distemper-Hepatitis-Leptospirosis","Leptospirosis","Parainfluenza","Canine Corana Virus",
        "Canine Parvovirus","Feline Distemper", "Feline Distemper-Calici Virus Rhinotracheitis"); 
		combobox_Against.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("Dr. Nielson Donato","Dr. Ashley Abaday","Dr. Eufemia Abance"); 
		combobox_Vet.setItems(list1);
    }

    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }

}
