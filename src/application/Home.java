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
    
    public Scene scene;
    
    public void clientButton() throws IOException {
   	 Parent root = FXMLLoader.load(getClass().getResource("clientforms.fxml"));
   	 scene = new Scene(root, 1074, 748 );
   	 Stage window = (Stage) clientBtn.getScene().getWindow();
   	 window.setTitle("Clients");
   	 window.setScene(scene);
   	
   }
   public void paymentButton() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("clientforms.fxml"));
    scene = new Scene(root, 1074, 748 );
    Stage window = (Stage) clientBtn.getScene().getWindow();
    window.setTitle("Clients");
    window.setScene(scene);
   
}

}
