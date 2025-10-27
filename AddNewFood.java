/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp.foodtype;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Handles the interactive creation of a new, custom FoodType.
 * It prompts the user for all details and validates the input.
 * This class correctly accepts a shared Scanner from the main class
 * to avoid input stream conflicts.
 */
public class AddNewFood extends FoodType {

    /** Holds the shared scanner passed from the main application. */
    private Scanner sc;

    /**
     * Constructs a new custom food item by interactively prompting the user.
     * @param scanner The single, shared Scanner object from the main application.
     */
    public AddNewFood(Scanner scanner) {
        // Must call super() first. We use placeholder values, as we
        // will immediately overwrite them by calling the prompt methods.
        super(null, 0.0, null, LocalTime.now()); 
        
        this.sc = scanner; // Store the shared scanner

        // Now, call the prompt methods to get real values
        this.setName(promptName());
        this.setWeightGrams(promptWeight());
        this.setDateBBF(promptBestBefore());
        // timePlacedInStorage was already set by super()
    }

    /**
     * Private helper method to prompt for and validate the food's name.
     * @return A valid, non-blank food name.
     */
    private String promptName() {
        String name;
        boolean valid;
        do {
            valid = true;
            System.out.print("Enter food name: ");
            name = sc.nextLine().trim(); // Use the shared scanner
            try {
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be blank.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                valid = false;
            }
        } while (!valid);
        return name;
    }

    /**
     * Private helper method to prompt for and validate the food's weight.
     * @return A valid, positive weight.
     */
    private double promptWeight() {
        double weight = 0.0;
        boolean valid;
        do {
            valid = true;
            System.out.print("Enter weight in grams: ");
            String input = sc.nextLine().trim(); // Use the shared scanner
            try {
                weight = Double.parseDouble(input);
                if (weight <= 0) {
                    throw new IllegalArgumentException("Weight must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for weight.");
                valid = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                valid = false;
            }
        } while (!valid);
        return weight;
    }

    /**
     * Private helper method to prompt for and validate the best-before date.
     * Ensures the date is not in the past and is at least 14 days from now.
     * @return A valid LocalDate.
     */
    private LocalDate promptBestBefore() {
        LocalDate date = null;
        boolean valid;
        do {
            valid = true;
            System.out.print("Enter best-before (YYYY-MM-DD): ");
            String input = sc.nextLine().trim(); // Use the shared scanner
            try {
                date = LocalDate.parse(input);
                LocalDate today = LocalDate.now();
                
                if (date.isBefore(today)) {
                    throw new IllegalArgumentException("Best-before cannot be in the past.");
                }
                
                // Earliest allowed date is 14 days from today
                LocalDate minDate = today.plusDays(14);
                if (date.isBefore(minDate)) {
                    throw new IllegalArgumentException("Best-before must be at least 14 days from now (e.g., " + minDate + " or later).");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Please use format YYYY-MM-DD.");
                valid = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                valid = false;
            }
        } while (!valid);
        return date;
    }
}