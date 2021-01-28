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
    private Button btn_clients;

    @FXML
    private Button btn_appointments;

    @FXML
    private Button btn_payments;

    @FXML
    private Button btn_logout;

    //public Scene scene;

    
    public void btn_clientsClicked() throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("clientforms.fxml"));
        scene = new Scene(root, 1074, 748 );
        Stage window = (Stage) btn_clients.getScene().getWindow();
        window.setTitle("Clients");
        window.setScene(scene);
        */
        //lance -- alternative code -- still dont see what happens if .getWindow() is there or not
        Main m = new Main();
        m.changeScene("clientforms.fxml", "Clients", 1074, 748);

    }
    public void btn_appointmentsClicked() throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("clientforms.fxml"));
        scene = new Scene(root, 1074, 748 );
        Stage window = (Stage) btn_clients.getScene().getWindow();
        window.setTitle("Clients");
        window.setScene(scene);
        */
        System.out.println("Appointments Page");
    }
    public void btn_paymentsClicked() throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("clientforms.fxml"));
        scene = new Scene(root, 1074, 748 );
        Stage window = (Stage) btn_clients.getScene().getWindow();
        window.setTitle("Clients");
        window.setScene(scene);
        */
        System.out.println("Payments Page");
    }
    public void btn_logoutClicked() throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml", "Login", 600, 400);
    }

}
