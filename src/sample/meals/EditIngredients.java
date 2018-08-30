package sample.meals;

import java.util.List;

public interface EditIngredients {

    default MainCourse changeIngredients(MainCourse mainCourse,List<String> ingredients){
        return new MainCourse(mainCourse.getName(),mainCourse.getType(),mainCourse.getPrice(),ingredients);
    }
}
