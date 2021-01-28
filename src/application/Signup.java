package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signup {
    @FXML
    Button btn_back;
    @FXML
    Button btn_saveUser;
    @FXML
    TextField textField_username;
    @FXML
    PasswordField passField_password;
    @FXML
    PasswordField passField_confirmPass;

    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml", "Login", 600, 400);
    }
    public void btn_saveUserClicked() throws IOException {
        System.out.println("new signup");
        Main m = new Main();
        m.changeScene("sample.fxml", "Login", 600, 400);
    }
}
