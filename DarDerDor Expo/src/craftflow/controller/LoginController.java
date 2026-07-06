package craftflow.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
   
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    public void login() {
        String username = usernameField.getText(); 
        String password = passwordField.getText();
        
        System.out.println(username);
        System.out.println(password);
    }

    public void validateUser() {}

    public void switchToDashboard() {}
}
