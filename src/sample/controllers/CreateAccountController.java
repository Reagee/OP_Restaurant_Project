package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateAccountController.
 *
 * @author Maksym Gilewski
 */

public class CreateAccountController {
    
    /** The load screen controller. */
    private LoadScreenController loadScreenController;

    /** The address. */
    @FXML
    private TextField username,email,address;
    
    /** The password confirm. */
    @FXML
    private PasswordField password,passwordConfirm;
    
    /** The registry error. */
    @FXML
    private Label registryError;

    /** The statement for db connection */
    private Statement st = LoadScreenController.st;
    
    /** The secon statement for db connection for update queries purposes. */
    private Statement st2 = LoadScreenController.st2;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        registryError.setVisible(false);
    }

    /**
     * New user registration method
     *
     * @throws SQLException the SQL exception
     */
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
        }else if(resultUser != null && resultUser.next()){ //short nested if
            registryError.setVisible(true);
            registryError.setText("Podana nazwa użytkownika jest już zajęta");
        }
        else if(resultMail != null && resultMail.next()){
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

    /**
     * Back to the welcome page.
     */
    @FXML
    private void backSite(){
        WelcomeController wc = new WelcomeController();
        wc.orderOnline();
    }

    /**
     * Sets the load screen controller.
     *
     * @param loadScreenController the new load screen controller
     */
    public void setLoadScreenController(LoadScreenController loadScreenController) {
        this.loadScreenController = loadScreenController;
    }
}
