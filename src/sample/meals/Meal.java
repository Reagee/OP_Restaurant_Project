package sample.meals;

/**
 * @author Maksym Gilewski
 *
 */

/**
 * The Class Meal.
 */
public class Meal {
    
    /** The name. */
    private String name;
    
    /** The type. */
    private String type;
    
    /** The price. */
    private double price;

    /**
     * Instantiates a new meal.
     *
     * @param name
     * @param type
     * @param price
     */
    public Meal(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }
}
