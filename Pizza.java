/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a preset Pizza item.
 */
public class Pizza extends FoodType {

    /**
     * Initializes a Pizza with default preset values.
     */
    public Pizza() {
        super("Pizza", 7658.33, LocalDate.of(2025, 12, 18), LocalTime.now());
    }
}