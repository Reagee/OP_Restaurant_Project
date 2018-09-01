package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Class WelcomeController.
 *
 * @author Maksym Gilewski
 */

public class WelcomeController {

    /** The load screen controller. */
    LoadScreenController loadScreenController;

    /**
     * Order online.
     * Method which show login screen if users wants to order the food online
     */
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

    /**
     * Book table.
     * Method which show booking table screen for user, who wants to book place in restaurant
     */
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

    /**
     * Exit app.
     * Function which closes DB connection and shut the application down
     */
    @FXML
    private void exitApp(){
        try {
            LoadScreenController.st.close();
            LoadScreenController.st2.close();
            LoadScreenController.con.close();
            System.out.println("Aplikacja została wyłączona poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
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
