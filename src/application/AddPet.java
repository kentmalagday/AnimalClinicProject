package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddPet {
    @FXML
    Button btn_back;
    @FXML
    Button btn_savePet;
    @FXML
    Button btn_editPet;
    @FXML
    TextField textField_petName;
    @FXML
    TextField textField_species;
    @FXML
    TextField textField_breed;
    @FXML
    TextField textField_weight;
    @FXML
    TextField textField_age;
    @FXML
    TextField textField_color;
    @FXML
    TextArea textArea_purposeOfVisit;
    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("clientforms.fxml", "Clients", 1074, 748);
    }
}
