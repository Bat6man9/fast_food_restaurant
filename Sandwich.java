/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a preset Sandwich item.
 */
public class Sandwich extends FoodType {

    /**
     * Initializes a Sandwich with default preset values.
     */
    public Sandwich() {
        super("Sandwich", 350.0, LocalDate.of(2025, 11, 30), LocalTime.now());
    }
}






