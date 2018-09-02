package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.sql.*;

/**
 * The Class LoadScreenController.
 *
 * @author Maksym Gilewski
 */

public class LoadScreenController {
    
    /** The main stack pane. */
    @FXML
    private StackPane mainStackPane;

    /** The statement for db connection */
    protected static Statement st;
    
    /** The second statement for db connection (add for multi db queries purposes) */
    protected static Statement st2;

    /** The connection for the db */
    protected static Connection con;

    /** The user id. */
    private int user_id;

    /**
     * Initialize.
     *
     * @throws SQLException the SQL exception
     */
    
    /* 
     Connecting to database 
     */
    @FXML
    public void initialize() throws SQLException{
        if(checkDriver("com.mysql.jdbc.Driver"))
            System.out.println("mysqlDriver ok !");
        else	
            System.exit(1);
        String DB_URL="jdbc:mysql://localhost:3306/restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        con = DriverManager.getConnection(DB_URL, "root", "");
        st = createStatement(con);
        st2 = createStatement(con);
        if(executeUpdate(st,"USE restaurant;")==0)
            System.out.println("Baza restaurant wybrana.");
        else
            System.out.println("Baza nie istnieje");
        loadStartScreen();
    }

    /**
     * Load start screen.
     */
    public void loadStartScreen(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/resources/fxml/WelcomeSite.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WelcomeController welcomeController = loader.getController();
        welcomeController.setLoadScreenController(this);
        setScreenPane(pane);
    }

    /**
     * Sets the screen pane.
     *
     * @param Pane the new screen pane
     */
    protected void setScreenPane(Pane Pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(Pane);
    }

    /**
     * Check driver.
     *
     * @param driver the driver
     * @return true, if successful
     */
    private static boolean checkDriver(String driver) {
        try {
            Class.forName(driver).newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad przy ladowaniu sterownika bazy!");
            return false;
        }
    }

    /**
     * Creates the statement.
     *
     * @param connection the connection
     * @return the statement
     */
    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Błąd createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }

    /**
     * Execute query.
     *
     * @param s the s
     * @param sql the sql
     * @return the result set
     */
    protected static ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return null;
    }

    /**
     * Execute update.
     *
     * @param s the s
     * @param sql the sql
     * @return the int
     */
    protected static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return -1;
    }

}
