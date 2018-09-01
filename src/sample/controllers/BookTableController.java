package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * The Class BookTableController.
 *
 * @author Maksym Gilewski
 */
public class BookTableController {
	
	
    /** The load screen controller. */
    private LoadScreenController loadScreenController;

    /** The table info. */
    @FXML
    private Pane tableInfo;

    /** The book error info. */
    @FXML
    private Label chooseTableAlert,tableNumber,placesInTable,bookErrorInfo;

    /** The number. */
    @FXML
    private TextField name,surname,email,number;

    /** The choose term. */
    @FXML
    private ChoiceBox<Timestamp> chooseTerm;

    /** The statement for db connection */
    private Statement st = LoadScreenController.st;
    
    /** The second statement for update queries purposes */
    private Statement st2 = LoadScreenController.st2;
    
    /** The term list. */
    private ObservableList termList = FXCollections.observableArrayList(); //observableArrayList()
    
    /** The table number variable. */
    private int tableNb = 0;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        if(tableInfo.isVisible())
            tableInfo.setVisible(false);
    }

    /**
     * Find free table terms.
     *
     * @param tableNumber the table number
     * @throws SQLException the SQL exception
     */
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
    
    /**
     * Back to the welcome page.
     */
    @FXML
    private void backSite(){
        loadScreenController.loadStartScreen();
    }

    /**
     * Book table method.
     */
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

    
    /* 
     All methods for booking the table contain try catch phrase because of findTerms() method which throws SQLException
     */
    
    /**
     * First table book.
     */
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

    /**
     * Second table book.
     */
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

    /**
     * Third table book.
     */
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

    /**
     * Fourth table book.
     */
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

    /**
     * Fifth table book.
     */
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

    /**
     * Sixth table book.
     */
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



    /**
     * Sets the load screen controller.
     *
     * @param loadScreenController the new load screen controller
     */
    public void setLoadScreenController(LoadScreenController loadScreenController) {
        this.loadScreenController = loadScreenController;
    }
}
