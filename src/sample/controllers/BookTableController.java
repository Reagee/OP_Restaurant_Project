package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class BookTableController {
    private LoadScreenController loadScreenController;

    @FXML
    private Pane firstTable,secondTable,thirdTable,fourthTable,fifthTable,sixthTable,tableInfo;

    @FXML
    private Button bookButton, firstTableButton,secondTableButton,thirdTableButton,fourthTableButton,fifthTableButton,sixthTableButton,returnButton;

    @FXML
    private Label chooseTableAlert,tableNumber,placesInTable,bookErrorInfo;

    @FXML
    private TextField name,surname,email,number;

    @FXML
    private ChoiceBox<Timestamp> chooseTerm;

    private Statement st = LoadScreenController.st;
    private Statement st2 = LoadScreenController.st2;
    private ObservableList termList = FXCollections.observableArrayList();
    private int tableNb = 0;

    @FXML
    public void initialize(){
        if(tableInfo.isVisible())
            tableInfo.setVisible(false);
    }

    private void findTerms(int tableNumber) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String sql = "SELECT * FROM free_terms WHERE date >= '"+timestamp+"' and id="+tableNumber+"";
        ResultSet res = LoadScreenController.executeQuery(st,sql);
        if(!res.isBeforeFirst()){
            chooseTableAlert.setText("Brak miejsc.");
        }else {
            termList.removeAll(termList);
            while(res.next()){
                termList.addAll(res.getTimestamp("date"));
            }
            chooseTerm.setItems(termList);
        }
    }
    @FXML
    private void backSite(){
        loadScreenController.loadStartScreen();
    }

    @FXML
    private void bookTable(){
        Timestamp choosedTime = chooseTerm.getValue();
        String fname = name.getText();
        String sname = surname.getText();
        String mail = email.getText();
        String phone = number.getText();

        if(fname==null || fname.isEmpty() || sname==null ||sname.isEmpty()||mail==null||mail.isEmpty()||phone==null||phone.isEmpty()){
            bookErrorInfo.setText("Wypełnij poprawnie wszystkie pola");
        }else if(fname!=null && !fname.isEmpty() && sname!=null &&!sname.isEmpty()&&mail!=null&&!mail.isEmpty()&&phone!=null&&!phone.isEmpty()&&(choosedTime==null || choosedTime.equals(null))){
            bookErrorInfo.setText("Wybierz termin");
        }else if(phone.length()!=9){
            bookErrorInfo.setText("Podaj prawidłowy numer telefonu");
        }else if(!mail.contains("@") && !mail.contains(".")){
            bookErrorInfo.setText("Podaj prawidłowy adres email");
        }else{
            String book = "INSERT INTO `reserved_tables` (`id`, `table_number`, `date`, `first_name`, `last_name`, `email`, `number`) " +
                    "VALUES (NULL, '"+tableNb+"', '"+choosedTime+"', '"+fname+"', '"+sname+"', '"+mail+"', '"+Long.parseLong(phone)+"')";
            if(LoadScreenController.executeUpdate(st,book)==1){
                String remove = "DELETE FROM free_terms WHERE id="+tableNb+" and date='"+choosedTime+"'";
                if(LoadScreenController.executeUpdate(st2,remove) == 1) {
                    tableInfo.setVisible(false);
                    name.setText("");
                    surname.setText("");
                    email.setText("");
                    number.setText("");
                    bookErrorInfo.setText("Stolik nr." + tableNb + " został zarezerwowany na termin:" + choosedTime);
                    bookErrorInfo.setTextFill(Color.BLACK);
                    tableNb = 0;
                }
            }else{
                bookErrorInfo.setText("Błąd podczas rezerwacji stolika");
            }
        }
    }

    @FXML
    private void firstTableBook(){
        chooseTableAlert.setText("");
        tableInfo.setVisible(true);
        placesInTable.setText("4");
        tableNumber.setText("1");
        tableNb = 1;
        try {
            findTerms(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void secondTableBook(){
        chooseTableAlert.setText("");
        tableInfo.setVisible(true);
        placesInTable.setText("4");
        tableNumber.setText("2");
        tableNb = 2;
        try {
            findTerms(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void thirdTableBook(){
        chooseTableAlert.setText("");
        tableInfo.setVisible(true);
        placesInTable.setText("4");
        tableNumber.setText("3");
        tableNb = 3;
        try {
            findTerms(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fourthTableBook(){
        chooseTableAlert.setText("");
        tableInfo.setVisible(true);
        placesInTable.setText("8");
        tableNumber.setText("4");
        tableNb = 4;
        try {
            findTerms(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fifthTableBook(){
        chooseTableAlert.setText("");
        tableInfo.setVisible(true);
        placesInTable.setText("8");
        tableNumber.setText("5");
        tableNb = 5;
        try {
            findTerms(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sixthTableBook(){
        chooseTableAlert.setText("");
        tableInfo.setVisible(true);
        placesInTable.setText("12");
        tableNumber.setText("6");
        tableNb = 6;
        try {
            findTerms(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void setLoadScreenController(LoadScreenController loadScreenController) {
        this.loadScreenController = loadScreenController;
    }
}
