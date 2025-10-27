/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a preset Hotdog item.
 */
public class Hotdog extends FoodType {

    /**
     * Initializes a Hotdog with default preset values.
     */
    public Hotdog() {
        super("Hotdog", 2050.33, LocalDate.of(2025, 11, 27), LocalTime.now());
    }
}
