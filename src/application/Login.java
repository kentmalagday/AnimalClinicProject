package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        /*
         * 
         * if(textField_username.getText().toString().equals("admin") &&
         * passField_password.getText().toString().equals("1234")) {
         * wrongLogIn.setText("Success!");
         * 
         * m.changeScene("home.fxml", "Home", 1074, 748); }
         * 
         * else if(textField_username.getText().isEmpty() &&
         * passField_password.getText().isEmpty()) {
         * wrongLogIn.setText("Please enter your data."); }
         * 
         * 
         * else { wrongLogIn.setText("Wrong username or password!"); }
         */
        if (textFieldsEmpty()) {
            wrongLogIn.setText("Enter you data");
        } else {
            Users user = new Users();
            user.setUsername(textField_username.getText());
            user.setPassword(passField_password.getText());
            try {
                if (Database.verifyLogin(user)) {
                    System.out.println("Login success");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Login");
                    alert.setHeaderText("Login succesfully!");
                    alert.showAndWait();
                    m.changeScene("home.fxml", "Home", 1056, 668);
                } else {
                    wrongLogIn.setText("Incorrect username or password");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public boolean textFieldsEmpty(){
        int i = 0;
        if(textField_username.getText().isEmpty() || passField_password.getText().isEmpty()){
            i++;
        }
        if(i>0)
            return true;
        else
            return false;
    }


}