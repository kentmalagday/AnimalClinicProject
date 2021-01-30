package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    Label errorText;

    public void btn_backClicked() throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml", "Login", 600, 400);
    }
    public void btn_saveUserClicked() throws Exception {
        if(textFieldsEmpty()){
            errorText.setText("There are empty textfields");
        }else{
            if(checkIfValid()){
            return;
            }
            Users user = new Users();
            user.setUsername(textField_username.getText());
            user.setPassword(passField_password.getText());
            Database.addUser(user);
        }
    }
    public boolean textFieldsEmpty(){
        int i = 0;
        if (textField_username.getText().isEmpty() || passField_password.getText().isEmpty() || passField_confirmPass.getText().isEmpty()){
            i++;
        }
        if(i>0){    
            return true;
        }
        return false;
        
    }
    public boolean checkIfValid(){
        if(passField_password.getText().equals(passField_confirmPass.getText())){
            return false;
        }else{
            errorText.setText("Password and Confirm Password does not match");
            return true;
        }
    }
}
