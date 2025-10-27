/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author yoyos
 */
public class FoodType {
    private String name;//presetting the data type to not be able accsesable outside the class
    private double weightGrams;
    private LocalDate dateBBF;
    private LocalTime timePlacedInStorage;

     public FoodType(String name, double weightGrams, LocalDate dateBBF, LocalTime timePlacedInStorage) {
         this.name = name;
         this.weightGrams = weightGrams;
         this.dateBBF = dateBBF;
         this.timePlacedInStorage = (timePlacedInStorage != null) ? timePlacedInStorage : LocalTime.now();
         //constructor
     }
     //GETTERS = methods make a field readable for other classes;
     //SETTERs = methods make a filed writable for other classes

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public double getWeightGrams() {
         return weightGrams;
     }

     public void setWeightGrams(double weightGrams) {
         this.weightGrams = weightGrams;
     }

     public LocalDate getDateBBF() {
         return dateBBF;
     }

     public void setDateBBF(LocalDate dateBBF) {
         this.dateBBF = dateBBF;
     }

     public LocalTime getTimePlacedInStorage() {
         return timePlacedInStorage;
     }

     public void setTimePlacedInStorage(LocalTime timePlacedInStorage) {
         this.timePlacedInStorage = timePlacedInStorage;
     }
    

     @Override
     public String toString() {
         // Using the exact format from your class
         return "" + "name= " + name + ", weight in Grams= " + weightGrams +"g"+ ", dateBBF= " + dateBBF + ", time Placed In Storage= " + timePlacedInStorage;
     }
}