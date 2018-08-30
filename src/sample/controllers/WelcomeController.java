package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeController {

    LoadScreenController loadScreenController;

    @FXML
    public void orderOnline(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/resources/fxml/LoginScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginController loginController = loader.getController();
        loginController.setLoadScreenController(loadScreenController);
        loadScreenController.setScreenPane(pane);
    }

    @FXML
    private void bookTable(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/resources/fxml/BookTableScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BookTableController bookTableController = loader.getController();
        bookTableController.setLoadScreenController(loadScreenController);
        loadScreenController.setScreenPane(pane);
    }

    @FXML
    private void exitApp(){
        try {
            LoadScreenController.st.close();
            LoadScreenController.st2.close();
            System.out.println("Aplikacja została wyłączona poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }

    public void setLoadScreenController(LoadScreenController loadScreenController) {
        this.loadScreenController = loadScreenController;
    }

}
