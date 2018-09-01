package sample.controllers;

import javafx.scene.control.Button;

/**
 * The Class MakeOrder.
 *
 * @author Maksym Gilewski
 */

public abstract class MakeOrder {
    
    /**
     * Adds the to cart.
     *
     * @param Button b 
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
