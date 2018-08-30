package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccountController {
    private LoadScreenController loadScreenController;

    @FXML
    private TextField username,email,address;
    @FXML
    private PasswordField password,passwordConfirm;
    @FXML
    private Label registryError;

    private Statement st = LoadScreenController.st;
    private Statement st2 = LoadScreenController.st2;

    @FXML
    public void initialize(){
        registryError.setVisible(false);
    }

    @FXML
    private void registry() throws SQLException {
        String login = username.getText();
        String pass = password.getText();
        String passConf = passwordConfirm.getText();
        String mail = email.getText();
        String add = address.getText();

        String checkUser = "SELECT id FROM user WHERE username='"+login+"'";
        String checkMail = "SELECT id FROM user WHERE email='"+mail+"'";
        ResultSet resultUser = LoadScreenController.executeQuery(st,checkUser);
       ResultSet resultMail = LoadScreenController.executeQuery(st2,checkMail);

        if(login==null || login.isEmpty() || pass==null || pass.isEmpty() || passConf==null || passConf.isEmpty() || mail==null || mail.isEmpty() || add==null||add.isEmpty()){
            registryError.setVisible(true);
            registryError.setText("Wypełnij poprawnie wszystkie pola");
        }else if(!pass.equals(passConf)){
            registryError.setVisible(true);
            registryError.setText("Podane hasła muszą być identyczne");
        }else if(!mail.contains("@")){
            registryError.setVisible(true);
            registryError.setText("Podaj poprawny adres email");
        }else if(resultUser.next()){
            registryError.setVisible(true);
            registryError.setText("Podana nazwa użytkownika jest już zajęta");
        }
        else if(resultMail.next()){
            registryError.setVisible(true);
            registryError.setText("Istnieje już konto o takim adresie e-mail");
        }else{
            String addUser = "INSERT INTO `user` (`id`, `username`, `password`, `email`, `address`, `bonus`)" +
                    "VALUES (NULL, '"+login+"', '"+pass+"', '"+mail+"', '"+add+"', '0')";
            if(LoadScreenController.executeUpdate(st,addUser)==1){
                registryError.setVisible(true);
                registryError.setText("Rejestracja przebiegła pomyślnie, możesz się zalogować.");
                username.setText("");
                password.setText("");
                passwordConfirm.setText("");
                email.setText("");
                address.setText("");
                registryError.setTextFill(Color.GREEN);
            }else{
                registryError.setVisible(true);
                registryError.setText("Błąd podczas rejestracji, spróbuj ponownie.");
            }
        }

    }

    @FXML
    private void backSite(){
        WelcomeController wc = new WelcomeController();
        wc.orderOnline();
    }

    public void setLoadScreenController(LoadScreenController loadScreenController) {
        this.loadScreenController = loadScreenController;
    }
}
