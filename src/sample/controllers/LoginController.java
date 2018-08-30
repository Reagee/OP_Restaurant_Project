package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {

    private LoadScreenController loadScreenController;
    public static String username = "";

    @FXML
    private TextField login;
    @FXML
    private Label loginError;
    @FXML
    private PasswordField password;

    private Statement st = LoadScreenController.st;

    @FXML
    public void initialize(){
        loginError.setVisible(false);
    }

    @FXML
    private void logIn() throws SQLException {
        loginError.setText("");
        if(login.getText()==null || login.getText().isEmpty() || password.getText()==null || password.getText().isEmpty()) {
            loginError.setText("Podaj login oraz hasło");
            loginError.setVisible(true);
        }
        else{
            String sql = "SELECT id FROM user WHERE username='"+login.getText()+"' AND password='"+password.getText()+"'";
            ResultSet resultSet = LoadScreenController.executeQuery(st,sql);
            if(resultSet.next()){
                username = login.getText();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("/sample/resources/fxml/LoggedInScreen.fxml"));
                Pane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LoggedInController loggedInController = loader.getController();
                loggedInController.setLoadScreenController(loadScreenController);
                loadScreenController.setScreenPane(pane);
            } else{
                loginError.setVisible(true);
                loginError.setText("Nieprawidłowy login lub hasło");
            }
        }

    }

    @FXML
    private void createAccount(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/resources/fxml/RegistrationScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateAccountController createAccountController = loader.getController();
        createAccountController.setLoadScreenController(loadScreenController);
        loadScreenController.setScreenPane(pane);
    }

    @FXML
    private void backSite(){
        loadScreenController.loadStartScreen();
    }

    public void setLoadScreenController(LoadScreenController loadScreenController) {
        this.loadScreenController = loadScreenController;
    }
}
