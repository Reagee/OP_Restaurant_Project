package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class LoadScreenController {
    @FXML
    private StackPane mainStackPane;

    protected static Statement st;
    protected static Statement st2;

    private int user_id;

    @FXML
    public void initialize(){
        if(checkDriver("com.mysql.jdbc.Driver"))
            System.out.println("mysqlDriver ok !");
        else
            System.exit(1);
        Connection con = getConnection("jdbc:mysql://","localhost",3306,"root","");
        st = createStatement(con);
        st2 = createStatement(con);
        if(executeUpdate(st,"USE restaurant;")==0)
            System.out.println("Baza restaurant wybrana.");
        else
            System.out.println("Baza nie istnieje");
        loadStartScreen();
    }

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

    public void setScreen(StackPane stackPane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(stackPane);
    }

    public void setScreenPane(Pane Pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(Pane);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public static boolean checkDriver(String driver) {
        try {
            Class.forName(driver).newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad przy ladowaniu sterownika bazy!");
            return false;
        }
    }

    public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try {
            conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(2);
        }
        return conn;
    }


    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("B��d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }


    @SuppressWarnings("unused")
    private static void closeConnection(Connection connection, Statement s) {
        System.out.print("\nZamykanie polaczenia z bazą:");
        try {
            s.close();
            connection.close();
        } catch (SQLException e) {
            System.out
                    .println("Błąd przy zamykaniu polłczenia z bazą! " + e.getMessage() + ": " + e.getErrorCode());;
            System.exit(4);
        }
        System.out.print(" zamknięcie OK");
    }


    protected static ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return null;
    }

    protected static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return -1;
    }


    @SuppressWarnings("unused")
    private static void printDataFromQuery(ResultSet r) {
        ResultSetMetaData rsmd;
        try {
            rsmd = r.getMetaData();
            int numcols = rsmd.getColumnCount(); // pobieranie liczby kolumn
            // wyswietlanie nazw kolumn:
            for (int i = 1; i <= numcols; i++) {
                System.out.print("\t" + rsmd.getColumnLabel(i) + "\t|");
            }
            System.out
                    .print("\n____________________________________________________________________________\n");

            // wyswietlanie kolejnych rekordow:
            while (r.next()) {
                for (int i = 1; i <= numcols; i++) {
                    Object obj = r.getObject(i);
                    if (obj != null)
                        System.out.print("\t" + obj.toString() + "\t|");
                    else
                        System.out.print("\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Bl�d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
        }
    }

    public static void sqlGetDataByName(ResultSet r) {
        System.out.println("Pobieranie danych z wykorzystaniem nazw kolumn");
        try {
            ResultSetMetaData rsmd = r.getMetaData();
            int numcols = rsmd.getColumnCount();
            // Tytul tabeli z etykietami kolumn zestawow wynikow
            for (int i = 1; i <= numcols; i++) {
                System.out.print(rsmd.getColumnLabel(i) + "\t|\t");
            }
            System.out
                    .print("\n____________________________________________________________________________\n");
            while (r.next()) {
                int size = r.getMetaData().getColumnCount();
                for(int i = 1; i <= size; i++){
                    switch(r.getMetaData().getColumnTypeName(i)){
                        case "INT":
                            System.out.print(r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t");
                            break;
                        case "DATE":
                            System.out.print(r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t");
                            break;
                        case "VARCHAR":
                            System.out.print(r.getString(r.getMetaData().getColumnName(i)) + "\t|\t");
                            break;
                        default:
                            System.out.print(r.getMetaData().getColumnTypeName(i));
                    }
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Bl�d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
        }
    }

}
