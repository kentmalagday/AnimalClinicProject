package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class Home {

    @FXML
    private Button btn_clients;

    @FXML
    private Button btn_appointments;

    @FXML
    private Button btn_payments;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_signup;
    
    public void btn_clientsClicked() throws IOException {
        Main m = new Main();
        m.changeScene("clientforms.fxml", "Clients", 1392, 784);
    }
    public void btn_appointmentsClicked() throws IOException {
    	Main m = new Main();
    	m.changeScene("appointment.fxml", "Appointments", 1074, 748);
        System.out.println("Appointments Page");
    }
    public void btn_paymentsClicked() throws IOException {
    	Main m = new Main();
    	m.changeScene("payments.fxml", "Payments", 1074, 748);
        System.out.println("Payments Page");
    }
    public void btn_logoutClicked() throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml", "Login", 600, 400);
    }
    public void btn_signup_clicked() throws IOException {
        Main m = new Main();
        m.changeScene("signup.fxml", "Signup", 400, 400);
    }

}
