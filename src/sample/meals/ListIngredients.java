package sample.meals;

import java.util.List;

public interface ListIngredients {

    default MainCourse listIngredients(MainCourse mainCourse,List<String> ingredients){
        return new MainCourse(mainCourse.getName(),mainCourse.getType(),mainCourse.getPrice(),ingredients);
    }

    public String ingredients();
}
