package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Home {

    @FXML
    private Button clientBtn;

    @FXML
    private Button appointmentBtn;

    @FXML
    private Button paymentBtn;
    
    public void clientButton() throws IOException {
   	 Parent root = FXMLLoader.load(getClass().getResource("clientforms.fxml"));
   	 Stage window = (Stage) clientBtn.getScene().getWindow();
   	 window.setTitle("Clients");
   	 window.setScene(new Scene(root, 1074, 748 ));
   	
   }

}
