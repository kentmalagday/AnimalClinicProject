package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Grooming implements Initializable {

    @FXML
    CheckBox checkbox_teethBrushing;

    @FXML
    CheckBox checkbox_deMattingtreatment;

    @FXML
    CheckBox checkbox_deTickingtreatment;

    @FXML
    CheckBox checkbox_nailCLipping;

    @FXML
    CheckBox checkbox_earClipping;

    @FXML
    CheckBox checkbox_pawPadCleaning;

    @FXML
    Text text_Petname;

    @FXML
    DatePicker picker_date;

    @FXML
    ComboBox<String> combobox_typeOfgroom;

    @FXML
    ComboBox<String> combobox_sizeOfpet;

    @FXML
    TextField textField_groomer;

    @FXML
    TableColumn<?, ?> date;

    @FXML
    TableColumn<?, ?> typeofgroom;

    @FXML
    TableColumn<?, ?> sizeofpet;

    @FXML
    TableColumn<?, ?> treatments;

    @FXML
    TableColumn<?, ?> groomer;

    @FXML
    Button btn_back;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Small (0-5lb)", "Medium (6-20lb)","Large (21-40lb)",
        "Extra large (41-60lb)","Double XL (61lb or higher)"); 
		combobox_sizeOfpet.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("Basic Groom","Full Groom","Bath & Dry","None");
        combobox_typeOfgroom.setItems(list1);
		
    }
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }
}
