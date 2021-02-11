package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Allergies {

    
    @FXML
    Text text_Petname;

    @FXML
    TextArea textArea_allergy;

    @FXML
    TextArea textArea_description;

    @FXML
    TableColumn<?, ?> allergies;

    @FXML
    TableColumn<?, ?> description;

    @FXML
    Button btn_back;


    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("pethealthlogmain.fxml", "Pet Health Log", 1522, 839);
    }

}

