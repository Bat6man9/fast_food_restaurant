/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * Represents a preset Burger item.
 */
public class Burger extends FoodType {
    
    /**
     * Initializes a Burger with default preset values.
     * Date is set to 2025-10-30, which will trigger the 14-day warning
     * if the app is run near the current date (Oct 2025).
     */
    public Burger(){
        super("Burger", 500.50 ,LocalDate.of(2025, Month.OCTOBER, 30), LocalTime.now() );
    }
}

