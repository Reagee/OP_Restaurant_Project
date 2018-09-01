package sample.controllers;

import javafx.scene.control.Button;

/**
 * The Class MakeOrder.
 *
 * @author Maksym Gilewski
 */

public abstract class MakeOrder { //abstract class
    
    /**
     * Adds the to cart.
     *
     * @param b
     * Function get Button as a parameter to have the knowledge of which button has been pressed
     */
    public void addToCart(Button b) {};
    
    /**
     * Make order method
     */
    public void makeOrder() {};
    
    /**
     * Clean order method
     */
    public void cleanOrder() {};
}
