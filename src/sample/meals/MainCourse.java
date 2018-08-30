package sample.meals;

import java.util.List;

public class MainCourse extends Meal implements EditIngredients{

    private List<String> ingredients;

    public MainCourse(String name, String type, double price,List<String> ingredients) {
        super(name, type, price);
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
