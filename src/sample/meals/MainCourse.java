package sample.meals;

import java.util.List;

/**
 * @author Maksym Gilewski
 *
 */

/**
 * The Class MainCourse.
 */
public class MainCourse extends Meal implements ListIngredients {  //inheriting from Meal and implements interface ListIngredients

    /** The ingredients. */
    private List<String> ingredients;

    /**
     * Instantiates a new main course.
     *
     * @param name
     * @param type
     * @param price
     * @param ingredients list of the ingredients
     */
    public MainCourse(String name, String type, double price,List<String> ingredients) {
        super(name, type, price);
        this.ingredients = ingredients;
    }

    /**
     * Gets the ingredients.
     *
     * @return the ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /* (non-Javadoc)
     * @see sample.meals.ListIngredients#ingredients()
     */
    @Override
    public String ingredients() {
        for (String ing : ingredients){
            return ing;
        }
        return null;
    }
}
