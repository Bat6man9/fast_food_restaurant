/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a preset Fries item.
 */
public class Fries extends FoodType {

    /**
     * Initializes Fries with default preset values.
     */
    public Fries() {
        super("Fries", 750.35, LocalDate.of(2025, 11, 25), LocalTime.now());
    }
}