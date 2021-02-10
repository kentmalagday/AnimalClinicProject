package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Pethealth {
    @FXML
    private Button btn_medical;

    @FXML
    private Button btn_immunization;

    @FXML
    private Button btn_vetvisit;

    @FXML
    private Button btn_medication;

    @FXML
    private Button btn_grooming;

    @FXML
    private Button btn_allergies;

    public void immunization() throws IOException {
        Main m = new Main();
        m.changeScene("immunization.fxml", "Immunization", 1072, 735);

    }
   
   
}