package sample.meals;

import java.util.List;

/**
 * @author Maksym Gilewski
 *
 */

/**
 * The Interface ListIngredients.
 */
public interface ListIngredients {

    /**
     * List ingredients.
     *
     * @param mainCourse the main course
     * @param ingredients list the ingredients
     * @return the main course
     */
    default MainCourse listIngredients(MainCourse mainCourse,List<String> ingredients){
        return new MainCourse(mainCourse.getName(),mainCourse.getType(),mainCourse.getPrice(),ingredients);
    }

    /**
     * Ingredients.
     *
     * @return the string
     */
    public String ingredients();
}
