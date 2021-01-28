package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;
public class Login {

    public Login() {

    }

    @FXML
    private Button btn_login;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField textField_username;
    @FXML
    private PasswordField passField_password;
    @FXML
    private Button btn_signup;



    public void btn_loginClicked(ActionEvent event) throws IOException {
        checkLogin();

    }

    public void btn_signup_clicked() throws IOException {
        Main m = new Main();
        m.changeScene("signup.fxml", "Signup", 400, 400);
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        if(textField_username.getText().toString().equals("admin") && passField_password.getText().toString().equals("1234")) {
            wrongLogIn.setText("Success!");

            m.changeScene("home.fxml", "Home", 1074, 748);
        }

        else if(textField_username.getText().isEmpty() && passField_password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");
        }


        else {
            wrongLogIn.setText("Wrong username or password!");
        }
    }


}