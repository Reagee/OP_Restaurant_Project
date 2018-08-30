package sample.meals;

public class Meal {
    private String name;
    private String type;
    private double price;

    public Meal(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
