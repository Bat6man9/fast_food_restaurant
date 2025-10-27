/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package the_resto_comp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import the_resto_comp.foodtype.AddNewFood;
import the_resto_comp.foodtype.Burger;
import the_resto_comp.foodtype.Fries;
import the_resto_comp.foodtype.Hotdog;
import the_resto_comp.foodtype.Pizza;
import the_resto_comp.foodtype.Sandwich;
import the_resto_comp.foodtype.FoodType;

/**
 * Console app for managing fast-food storage using an array-based queue (capacity 8).
 * Menu:
 * 1) Add preset (preview details first)
 * 2) Add custom item
 * 3) Remove next item (dequeue)
 * 4) Display item at the front (peek)
 * 5) Display all items in order (front → back)
 * 6) Display all items grouped by name
 * 7) Search food by name (binary search)
 * 8) Exit
 */

public class The_resto_comp {

    /**
     * Entry point. Repeatedly shows the menu and routes the user's choice.
     * Uses a single Scanner and a fixed-capacity Queue (8).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final Queue foodStorage = new Queue(8);
        int choice;

        do {
            printMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {
                case 1:
                    // Add a preset item: show details (and 14-day warning) before confirming add
                    selectAndViewThenAddFood(scanner, foodStorage);
                    printTopNameGlance(foodStorage);
                    break;

                case 2:
                    // Add a custom item interactively via AddNewFood (validates inputs)
                    if (foodStorage.isFull()) {
                        System.out.println("Cannot add item: Storage is already full (max 8).");
                    } else {
                        System.out.println("\n--- Add Custom Food ---");
                        FoodType created = new AddNewFood(scanner);
                        System.out.println("[Info] Attempting to add: " + created.getName());
                        foodStorage.enqueue(created);
                        System.out.println("[Done] Added at time: " + created.getTimePlacedInStorage());
                    }
                    printTopNameGlance(foodStorage);
                    break;

                case 3:
                    // Remove the next item (front of queue)
                    System.out.println("\n[Remove] Dequeue (front)...");
                    FoodType removed = foodStorage.dequeue();
                    if (removed != null) {
                        System.out.println("[Done] Removed: " + removed.getName());
                    }
                    printTopNameGlance(foodStorage);
                    break;

                case 4:
                    // Peek the item at the front and display only its name
                    FoodType topFood = foodStorage.peek();
                    if (topFood != null) {
                        System.out.println("Food at the top (front): " + topFood.getName());
                    } else {
                        System.out.println("Storage is empty.");
                    }
                    break;

                case 5:
                    // Show all items exactly in queue order (front → back)
                    System.out.println("\n[Display] All food:");
                    foodStorage.displayByOrder();
                    break;

                case 6:
                    // Show grouped summary (name + quantity), preserving first-seen order
                    System.out.println("\n[Display] Grouped by name:");
                    foodStorage.displayGrouped();
                    break;

                case 7:
                    // Case-insensitive binary search by name over a snapshot (order preserved)
                    searchFoodByNameBinary(scanner, foodStorage);
                    break;

                case 8:
                    System.out.println("Terminating the application. Goodbye!");
                    break;

                default:
                    if (choice != -1) {
                        System.out.println("Invalid choice. Please select an option from 1 to 8.");
                    }
            }

            // Pause after each action (except when exiting)
            if (choice != 8) {
                System.out.println("\nPress Enter to return to the menu...");
                scanner.nextLine();
            }

        } while (choice != 8);

        scanner.close();
        reportTimeComplexity();
    }

    /**
     * Prints the main menu (8 options) — labels match the switch cases.
     */
    public static void printMenu() {
        System.out.println("\n===== Fast-Food Storage Menu =====");
        System.out.println("1. Add a preset food item");
        System.out.println("2. Add new custom food item");
        System.out.println("3. Remove the next food item from storage");
        System.out.println("4. Display the food item at the front");
        System.out.println("5. Display all food items in order");
        System.out.println("6. Display all food items grouped by name");
        System.out.println("7. Search food by name (binary search)");
        System.out.println("8. Exit");
        System.out.println("==================================");
    }

    /**
     * helper: after add/remove, show which item is currently at the front.
     * Calls peek() and prints a short status line.
     */
    private static void printTopNameGlance(Queue foodStorage) {
        FoodType top = foodStorage.peek();
        if (top != null) {
            System.out.println("[Top Now] " + top.getName());
        } else {
            System.out.println("[Top Now] Storage is empty.");
        }
    }

    /**
     * Preset add flow with preview:
     * - Ask user to choose a preset type (Burger, Pizza, Fries, Sandwich, Hotdog)
     * - Instantiate and print details
     * - Show inclusive 14-day warning if best-before is within 14 days
     * - Confirm (y/n) before enqueuing
     */
    public static void selectAndViewThenAddFood(Scanner scanner, Queue foodStorage) {
        if (foodStorage.isFull()) {
            System.out.println("Cannot add item: Storage is already full (max 8).");
            return;
        }

        System.out.println("\nSelect a food type to see its details:");
        System.out.println("  1. Burger");
        System.out.println("  2. Pizza");
        System.out.println("  3. Fries");
        System.out.println("  4. Sandwich");
        System.out.println("  5. Hotdog");
        System.out.print("Enter your choice : ");

        String input = scanner.nextLine();
        FoodType tempItem = null;

        try {
            int foodChoice = Integer.parseInt(input);
            switch (foodChoice) {
                case 1: tempItem = new Burger(); break;
                case 2: tempItem = new Pizza(); break;
                case 3: tempItem = new Fries(); break;
                case 4: tempItem = new Sandwich(); break;
                case 5: tempItem = new Hotdog(); break;
                default: break; // invalid choice handled below
            }
        } catch (NumberFormatException e) {
            if (input.equalsIgnoreCase("Burger")) {
                tempItem = new Burger();
            } else if (input.equalsIgnoreCase("Pizza")) {
                tempItem = new Pizza();
            } else if (input.equalsIgnoreCase("Fries")) {
                tempItem = new Fries();
            } else if (input.equalsIgnoreCase("Sandwich")) {
                tempItem = new Sandwich();
            } else if (input.equalsIgnoreCase("Hotdog")) {
                tempItem = new Hotdog();
            }
        }

        if (tempItem == null) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }

        System.out.println("\n--- Default Details for " + tempItem.getName() + " ---");
        LocalDate currentDate = LocalDate.now();
        LocalDate twoWeeksFromNow = currentDate.plusWeeks(2);
        LocalDate bestBeforeDate = tempItem.getDateBBF();

        // Inclusive 14-day warning: trigger if best-before is on/before (today + 14 days)
        if (!bestBeforeDate.isAfter(twoWeeksFromNow)) {
            System.out.println("!!! WARNING: This item expires within the next 14 days (inclusive). !!!");
        }

        System.out.println(tempItem);
        System.out.println("---------------------------------");

        System.out.print("\nAdd this item to storage? (y/n): ");
        String confirmation = scanner.nextLine();

        while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
            System.out.println("That's not a valid input. Please choose either 'y' or 'n'.");
            System.out.print("Add this item to storage? (y/n): ");
            confirmation = scanner.nextLine();
        }

        if (confirmation.equalsIgnoreCase("y")) {
            foodStorage.enqueue(tempItem);
            System.out.println("[Done] Added preset " + tempItem.getName() + " at time: " + tempItem.getTimePlacedInStorage());
        } else {
            System.out.println("Item was not added to storage.");
        }
    }

    /**
     * Case-insensitive binary search over the names of items currently in storage.
     * Implementation:
     * - Snapshot by dequeuing all items (into a list) and re-enqueuing them to preserve order.
     * - Sort a parallel array of names and run iterative binary search.
     * - Print full details of all matches around the found index.
     */
    private static void searchFoodByNameBinary(Scanner scanner, Queue foodStorage) {
        System.out.print("\nEnter food name to search: ");
        String target = scanner.nextLine().trim();
        if (target.isEmpty()) {
            System.out.println("Search term cannot be blank.");
            return;
        }

        List<FoodType> snapshot = new ArrayList<>();
        Queue tempQueue = new Queue(8); // temporary holding queue

        // Drain original queue into snapshot and tempQueue
        // Note: This will print messages from your Queue's dequeue/enqueue methods.
        FoodType pulled;
        while (!foodStorage.isEmpty()) {
            pulled = foodStorage.dequeue();
            if (pulled != null) {
                snapshot.add(pulled);
                tempQueue.enqueue(pulled);
            }
        }

        // Refill original queue from tempQueue (restore original order)
        while (!tempQueue.isEmpty()) {
            pulled = tempQueue.dequeue();
            if (pulled != null) {
                foodStorage.enqueue(pulled);
            }
        }

        if (snapshot.isEmpty()) {
            System.out.println("[Result] Storage is empty. Nothing to search.");
            return;
        }

        // Build parallel arrays: names[] and items[]
        int n = snapshot.size();
        String[] names = new String[n];
        FoodType[] items = new FoodType[n];
        for (int i = 0; i < n; i++) {
            names[i] = snapshot.get(i).getName();
            items[i] = snapshot.get(i);
        }

        // Sort by name (case-insensitive) using index indirection
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (Integer a, Integer b) -> names[a].toLowerCase().compareTo(names[b].toLowerCase()));

        String[] namesSorted = new String[n];
        FoodType[] itemsSorted = new FoodType[n];
        for (int i = 0; i < n; i++) {
            namesSorted[i] = names[idx[i]];
            itemsSorted[i] = items[idx[i]];
        }

        // Iterative binary search (case-insensitive)
        int low = 0, high = n - 1, foundAt = -1;
        String key = target.toLowerCase();
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = namesSorted[mid].toLowerCase().compareTo(key);
            if (cmp == 0) { foundAt = mid; break; }
            if (cmp < 0)   low = mid + 1;
            else           high = mid - 1;
        }

        if (foundAt == -1) {
            System.out.println("[Result] \"" + target + "\" was NOT found in storage.");
            return;
        }

        // Print full details of all matches (scan left & right of the found index)
        System.out.println("[Result] Matches for \"" + target + "\":");
        int count = 0;

        int i = foundAt;
        while (i >= 0 && namesSorted[i].equalsIgnoreCase(target)) {
            System.out.println(" - " + itemsSorted[i].toString());
            count++;
            i--;
        }
        i = foundAt + 1;
        while (i < n && namesSorted[i].equalsIgnoreCase(target)) {
            System.out.println(" - " + itemsSorted[i].toString());
            count++;
            i++;
        }

        System.out.println("Total matches: " + count);
        System.out.println("[Hint] Use option 5 for order view, or option 6 for grouped view.");
    }

    /**
     * Prints the big-O time complexity summary for the current array-based queue design.
     *
     */
    public static void reportTimeComplexity() {
        System.out.println("\n--- Time Complexity Analysis (Array-based Queue) ---");
        System.out.println("1. Enqueue at BACK: O(1) (Amortized O(n) if compactLeft() is triggered)");
        System.out.println("2. Dequeue from FRONT: O(1)");
        System.out.println("3. Peek front: O(1)");
        System.out.println("4. Display all (order or grouped): O(n)");
        // Note: The search itself is O(log n), but creating the sorted snapshot is O(n log n)
        System.out.println("5. Search by name (binary over copied array): O(n log n)  (+ O(n) snapshot/restore)");
        System.out.println("---------------------------------------------------------");
    }
}