package sample.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.ResultSetException;
import sample.meals.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * The Class LoggedInController.
 *
 * @author Maksym Gilewski
 */

public class LoggedInController extends MakeOrder{
    
    /** The load screen controller. */
    private LoadScreenController loadScreenController;
    
    /** The button list. */
    private List<Button> buttonList = new ArrayList<>(); //ArrayList for buttons
    
    /** The statement for db connection. */
    private Statement st = LoadScreenController.st,st2 = LoadScreenController.st2;

    /** The rectangles array. */
    private Rectangle[] rectangles;
    
    /** The meal array. */
    private String[] mealArray;
    
    /** The meal buttons array. */
    private Button[] mealButtons;

    /** The order. */
    private HashMap<String,Double> order = new HashMap<>(); //using hashmap for the orded

    /** The bucket flag. */
    private boolean orderFlag = false, bucketFlag = false;
    
    /** The counter. */
    private static int counter = 0;

    /** The order button. */
    @FXML
    private Button returnButton, snackButton, mainButton, soupButton, drinksButton,orderButton;

    /** The main pane. */
    @FXML
    private ScrollPane mainPane;

    /** The order box. */
    @FXML
    private VBox vBox, orderBox;


    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        mainPane.setPannable(true);
        mainPane.setFitToWidth(true);
        vBox.setPadding(new Insets(50,0,0,150));
        orderBox.setAlignment(Pos.TOP_CENTER);
        orderBox.setSpacing(15);
        mainPane.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        buttonList.add(returnButton);
        buttonList.add(snackButton);
        buttonList.add(mainButton);
        buttonList.add(soupButton);
        buttonList.add(drinksButton);
        buttonList.add(orderButton);
        
        /* 
         For each loop with lambda expression for handling buttons 
         */
        
        for(Button b:buttonList){
            b.setOnMouseEntered(event -> {
                if(!b.equals(returnButton)) {
                    b.setUnderline(true);
                }
                b.setStyle("-fx-background-color: rgba(0,0,0,0.35)");
            });
            b.setOnMouseExited(event -> {
                if(!b.equals(returnButton)) {
                    b.setUnderline(false);
                }
                b.setStyle("-fx-background-color: rgba(0,0,0,0.25)");
            });
        }
        Label infoLabel = new Label("Wybierz kategorię z menu powyżej");
        infoLabel.setAlignment(Pos.TOP_CENTER);
        infoLabel.setFont(Font.font("Yu Gothic Light",56));
        infoLabel.setPadding(new Insets(0,0,25,0));
        Label stepLabel = new Label("Wybrane elementy widoczne są w koszyku po prawej stronie ekranu");
        stepLabel.setAlignment(Pos.TOP_CENTER);
        stepLabel.setFont(Font.font("Yu Gothic Light",36));
        stepLabel.setPadding(new Insets(0,0,25,0));
        Label endLabel = new Label("Smacznego !");
        endLabel.setAlignment(Pos.TOP_CENTER);
        endLabel.setFont(Font.font("Yu Gothic Light",42));
        endLabel.setPadding(new Insets(0,0,25,0));

        vBox.getChildren().addAll(infoLabel,stepLabel,endLabel);

    }

    /**
     * Gets the menu.
     *
     * @param table name in DB
     * @return dynamic loading menu on screen 
     * @throws SQLException the SQL exception
     */
    
    /* 
     Method get number of record from table to have the knowledge how many rectangles it should prepare 
     */
    
    public void getMenu(String table) throws SQLException {
        vBox.getChildren().clear();
        String sql = "SELECT count(*) as ile FROM " + table;
        ResultSet resultSet = LoadScreenController.executeQuery(st, sql);

        if (resultSet.next()) {
            counter = resultSet.getInt("ile");
        }

        if (counter > 0) {
            rectangles = new Rectangle[counter];
            mealArray = new String[counter];
            mealButtons = new Button[counter];

            for (int i = 0; i < rectangles.length; i++) {
                rectangles[i] = new Rectangle(1250, 180);
                rectangles[i].setFill(new Color(0.9,0.9,0.9,0.95));
            }
        }
    }

    /**
     * Adds the to screen.
     *
     * @param meals the meals
     * 
     * Method adds meals from DB to screen
     */
    public void addToScreen(Meal[] meals){

        for(int i=0;i<mealButtons.length;i++){
            mealButtons[i] = new Button("Dodaj\n do koszyka");
            mealButtons[i].setPrefHeight(180);
            mealButtons[i].setPrefWidth(160);
            mealButtons[i].setTranslateX(545);
            mealButtons[i].setFont(Font.font("Yu Gothic Light",22));
            mealButtons[i].setTextFill(Color.WHITE);
            mealButtons[i].setTextAlignment(TextAlignment.CENTER);
            mealButtons[i].setCursor(Cursor.HAND);
            mealButtons[i].setStyle("-fx-background-color: rgba(0,0,0,0.8)");
            mealButtons[i].setId(i+"");
        }

        for(Button b:mealButtons){
            b.setOnMouseClicked(event -> {
                addToCart(b);
            });
            b.setOnMouseEntered(event -> {
                b.setStyle("-fx-background-color: rgba(0,0,0,0.9)");
            });
            b.setOnMouseExited(event -> {
                b.setStyle("-fx-background-color: rgba(0,0,0,0.8)");
            });
        }

        for(int i=0;i<rectangles.length;i++) {
            Label[] labelArr = new Label[meals.length];

            labelArr[0] = new Label((i+1) + ". ");
            labelArr[1] = new Label(meals[i].getName());
            labelArr[2] = new Label(meals[i].getType());
            labelArr[3] = new Label(meals[i].getPrice() + "zł");

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER_LEFT);
            grid.setMaxWidth(1200);
            grid.setMaxHeight(180);
            grid.add(labelArr[0], 1, 1);
            grid.add(labelArr[1], 2, 1);
            grid.add(labelArr[2], 2, 2);
            grid.add(new Label("    "), 3, 2);
            grid.add(labelArr[3], 4, 2);
            labelArr[0].setFont(Font.font("Yu Gothic Light", 24));
            labelArr[1].setFont(Font.font("Yu Gothic Light", 24));
            labelArr[2].setFont(Font.font("Yu Gothic Light", 20));
            labelArr[3].setFont(Font.font("Yu Gothic Light", 26));
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(rectangles[i], grid,mealButtons[i]);
            stackPane.setPadding(new Insets(25, 0, 25, 0));
            vBox.getChildren().add(stackPane);
        }
    }

    /**
     * Back to welcome page.
     */
    @FXML
    private void backToSite(){
        loadScreenController.loadStartScreen();
    }

    /**
     * Show snacks.
     *
     * @throws ResultSetException the result set exception
     * @throws SQLException the SQL exception
     */
    @FXML
    private void showSnacks() throws ResultSetException, SQLException {
        try {
            getMenu("snacks");
        } catch (SQLException e) {
            throw new ResultSetException("Wyjątek podczas pobierania danych z bazy");
        }

        String sql2 = "SELECT * FROM snacks";
        ResultSet resultSet = LoadScreenController.executeQuery(st2,sql2);
          Snack[] meals = new Snack[counter];
        int i =0;
        while(resultSet.next()) {
              String meal = resultSet.getString("name");
              String description = resultSet.getString("description");
              double price = resultSet.getDouble("price");
              Snack snack = new Snack(meal,description,price);
              if(meal !=null) {
                  meals[i] = snack;
                  mealArray[i] = meal + "," + price;
                  i++;
              }
        }
        addToScreen(meals);
    }

    /**
     * Show main courses.
     *
     * @throws ResultSetException the result set exception
     * @throws SQLException the SQL exception
     */
    @FXML
    private void showMainCourses() throws ResultSetException, SQLException {
        try {
            getMenu("main_courses");
        } catch (SQLException e) {
            throw new ResultSetException("Wyjątek podczas pobierania danych z bazy");
        }

        String sql2 = "SELECT * FROM main_courses";
        ResultSet resultSet = LoadScreenController.executeQuery(st2,sql2);
        MainCourse[] meals = new MainCourse[counter];
        int i =0;
        while(resultSet.next()) {
            String meal = resultSet.getString("name");
            String description = resultSet.getString("description");
            String ingredients = resultSet.getString("ingredients");
            String[] ingredientsArr = ingredients.split(",");
            List<String> ingredientsList = new ArrayList<>();
            for(String s:ingredientsArr)
                ingredientsList.add(s);

            double price = resultSet.getDouble("price");
            MainCourse mainCourse = new MainCourse(meal,description,price,ingredientsList);
            if(meal !=null) {
                meals[i] = mainCourse;
                mealArray[i] = meal + "," + price;
                i++;
            }
        }
        addToScreen(meals);
    }

    /**
     * Show soups.
     *
     * @throws ResultSetException the result set exception
     * @throws SQLException the SQL exception
     */
    @FXML
    private void showSoups() throws ResultSetException, SQLException {
        try {
            getMenu("soups");
        } catch (SQLException e) {
            throw new ResultSetException("Wyjątek podczas pobierania danych z bazy");
        }

        String sql2 = "SELECT * FROM soups";
        ResultSet resultSet = LoadScreenController.executeQuery(st2,sql2);
        Soup[] meals = new Soup[counter];
        int i =0;
        while(resultSet.next()) {
            String meal = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            Soup soup = new Soup(meal,"",price);
            if(meal !=null) {
                meals[i] = soup;
                mealArray[i] = meal + "," + price;
                i++;
            }
        }
        addToScreen(meals);
    }

    /**
     * Show drinks.
     *
     * @throws ResultSetException the result set exception
     * @throws SQLException the SQL exception
     */
    @FXML
    private void showDrinks() throws ResultSetException, SQLException {
        try {
            getMenu("drinks");
        } catch (SQLException e) {
            throw new ResultSetException("Wyjątek podczas pobierania danych z bazy");
        }

        String sql2 = "SELECT * FROM drinks";
        ResultSet resultSet = LoadScreenController.executeQuery(st2,sql2);
        Drinks[] drink = new Drinks[counter];
        int i =0;
        while(resultSet.next()) {
            String meal = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            Drinks drinks = new Drinks(meal,"",price);
            if(meal !=null) {
                drink[i] = drinks;
                mealArray[i] = meal + "," + price;
                i++;
            }
        }
        addToScreen(drink);
    }

    /* 
     Overriding method from abstract class MakeOrder
     */
    @Override
    public void addToCart(Button b){
        if(orderBox.getChildren().size()==8){
            Label warning = new Label("Maksymalna ilość rzeczy w koszyku wynosi 7");
            warning.setFont(Font.font("Yu Gothic Light", 18));
            warning.setWrapText(true);
            warning.setAlignment(Pos.CENTER);
            warning.setTextAlignment(TextAlignment.CENTER);
            warning.setTextFill(Color.RED);
            orderBox.getChildren().add(warning);
        }
        if(orderBox.getChildren().size()<8) {
            if(bucketFlag)
                cleanOrder();
            if (b != null) {
                bucketFlag = false;
                int id = Integer.parseInt(b.getId());
                Label meal = new Label(mealArray[id]);
                meal.setFont(Font.font("Yu Gothic Light", 18));
                meal.setWrapText(true);
                meal.setAlignment(Pos.CENTER);
                meal.setTextAlignment(TextAlignment.CENTER);
                meal.setStyle("-fx-underline: true");
                orderBox.getChildren().add(meal);
            }
        }
    }

    /* 
    Overriding method from abstract class MakeOrder
    */
    @Override
    @FXML
    public void makeOrder(){
        if(orderFlag) {
            cleanOrder();
        }
        order.clear();

        if(orderBox.getChildren().size()==1){
            bucketFlag = true;
            Label warning = new Label("Koszyk jest pusty !");
            warning.setFont(Font.font("Yu Gothic Light", 18));
            warning.setWrapText(true);
            warning.setAlignment(Pos.CENTER);
            warning.setTextAlignment(TextAlignment.CENTER);
            warning.setTextFill(Color.RED);
            orderBox.getChildren().add(warning);
        }
        else if(orderBox.getChildren().size()>1 && !bucketFlag){
            for (int i = 1; i < orderBox.getChildren().size(); i++) {
                if (i == 8)
                    break;
                StringBuilder meal = new StringBuilder(orderBox.getChildren().get(i).toString());
                int startIndex = meal.indexOf("'");
                meal = meal.delete(0, startIndex + 1);
                int coma = meal.indexOf(",");
                meal = meal.deleteCharAt(meal.length() - 1);
                String mealName = meal.substring(0, coma);
                Double mealPrice = Double.parseDouble(meal.substring(coma + 1, meal.length()));

                if (order.containsKey(mealName)) {
                    order.put(mealName, order.get(mealName) + mealPrice);
                    continue;
                }
                order.put(mealName, mealPrice);
            }

            double orderSum = 0;
            for (String key : order.keySet()) {
                orderSum += order.get(key);
            }

            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setHeaderText("Potwierdzenie zamówienia");
            textInputDialog.setTitle("Potwierdzenie zamówienia w 'EatAndGo'");
            textInputDialog.getEditor().setPrefHeight(250);
            textInputDialog.getEditor().setAlignment(Pos.TOP_LEFT);
            textInputDialog.setHeight(650);
            textInputDialog.setWidth(700);
            textInputDialog.getDialogPane().setStyle("-fx-font-size: 20");
            textInputDialog.getEditor().setStyle("-fx-font-size: 14");

            String mealList = "Czy jesteś pewien, że chcesz złożyć zamówienie ? \n";
            for (String key : order.keySet()) {
                mealList += key + "  " + order.get(key) + "zł\n";
            }
            mealList += "---------------------------------------------------------------------\n";
            mealList += "Suma do zapłaty: " + orderSum + "zł \n Transakcja nie może zostać anulowana po wybraniu opcji Zamawiam \n";
            mealList += "Jeżeli chcesz zrezygnować z niektórych składników, napisz nam o tym w formularzu obok.";
            textInputDialog.setContentText(mealList);
            Optional<String> result = textInputDialog.showAndWait();

            if (result.isPresent()) {
                String dbMeal = "";
                for (String key : order.keySet())
                    dbMeal += key + ",";

                String username = LoginController.username;
                String sql = "Insert into orders values (null,'" + username + "','" + dbMeal + "','" + result.get() + "','" + orderSum + "')";
                if (LoadScreenController.executeUpdate(st, sql) == 1) {
                    cleanOrder();
                    String sql2 = "select address from user where username='" + username + "'";
                    ResultSet resultSet = LoadScreenController.executeQuery(st2, sql2);
                    int orderTime = new Random().nextInt(30) + 30;
                    try {
                        resultSet.next();
                        Label warning = new Label("Zamówienie zostało zrealizowane.\n Adres dostawy: " + resultSet.getString("address") + " \n " +
                                "Przewidywany czas dostawy: " + orderTime + "min.\n Smacznego!");
                        warning.setFont(Font.font("Yu Gothic Light", 18));
                        warning.setWrapText(true);
                        warning.setAlignment(Pos.CENTER);
                        warning.setTextAlignment(TextAlignment.CENTER);
                        warning.setTextFill(Color.DARKGREEN);
                        orderBox.getChildren().add(warning);
                        orderFlag = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* 
    Overriding method from abstract class MakeOrder
    */
    @Override
    @FXML
    public void cleanOrder(){
        if(orderBox.getChildren().size()>1)
            orderBox.getChildren().remove(orderBox.getChildren().size()-1);
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
