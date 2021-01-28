package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AddPet {
    @FXML
    Button backButton;

    public void backButton_Clicked() throws IOException {
        Main m = new Main();
        m.changeScene("clientforms.fxml", "Clients", 1074, 748);
    }
}
