/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_resto_comp;

import the_resto_comp.foodtype.FoodType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements an array-based, non-circular queue.
 * It uses 'front' and 'rear' pointers.
 * It automatically compacts (shifts items left) if the rear hits the end
 * but there is still space at the front.
 */
public class Queue {
    /** The underlying array holding the food items. */
    private final FoodType[] queue;
    /** The index of the front item. -1 if empty. */
    private int front;
    /** The index of the rear item. -1 if empty. */
    private int rear;
    /** The maximum capacity of the queue. */
    private final int size;

    /**
     * Constructs a new queue with a fixed capacity.
     * @param capacity The maximum number of items the queue can hold.
     */
    public Queue(int capacity) {
        this.size = capacity;
        this.queue = new FoodType[capacity];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * Checks if the queue is currently empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return front == -1;
    }

    /**
     * Checks if the queue is currently full.
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {
        if (isEmpty()) return false;
        // Full if the number of items (rear - front + 1) equals the capacity
        return (rear - front + 1) == size;
    }

    /**
     * Internal helper method to shift all items to the start of the array.
     * This is called by enqueue() when the rear pointer is at the end
     * but the front pointer has moved, leaving space.
     */
    private void compactLeft() {
        if (isEmpty()) return;
        
        int j = 0;
        // Copy all items from front to rear to the start of the array
        for (int i = front; i <= rear; i++) {
            queue[j++] = queue[i];
        }
        
        // Clear the remaining (now duplicate) data from the tail
        for (int k = j; k < size; k++) {
            queue[k] = null;
        }
        
        // Reset pointers
        front = 0;
        rear = j - 1;
    }

    /**
     * Adds a new item to the REAR (back) of the queue.
     * @param item The FoodType item to add.
     */
    public void enqueue(FoodType item) {
        if (isEmpty()) {
            front = rear = 0;
            queue[rear] = item;
            System.out.println(item.getName() + " has been added to storage (back).");
            return;
        }

        // If array end is reached, but there is space at the front, compact
        if (rear == size - 1 && front > 0) {
            compactLeft();
        }

        if (isFull()) {
            System.out.println("Storage is full! Cannot add " + item.getName());
            return;
        }

        // Add item to the new rear position
        rear++;
        queue[rear] = item;
        System.out.println(item.getName() + " has been added to storage (back).");
    }

    /**
     * Removes and returns the item from the FRONT of the queue.
     * @return The FoodType item removed, or null if the queue was empty.
     */
    public FoodType dequeue() {
        if (isEmpty()) {
            System.out.println("Storage is empty!");
            return null;
        }
        
        FoodType item = queue[front];
        queue[front] = null; // Clean up the array slot
        
        if (front == rear) {
            // The queue is now empty
            front = rear = -1;
        } else {
            // Move front pointer forward
            front++;
        }
        
        System.out.println(item.getName() + " has been removed from storage (front).");
        return item;
    }

    /**
     * Returns the item at the FRONT of the queue without removing it.
     * @return The FoodType item at the front, or null if the queue is empty.
     */
    public FoodType peek() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }

    /**
     * Displays all items in the queue in their exact order, from front to back.
     * (Corresponds to menu option 5)
     */
    public void displayByOrder() {
        if (isEmpty()) {
            System.out.println("Storage is empty!");
            return;
        }
        
        System.out.println("--- Food in Storage (front \u2192 back) ---");
        for (int i = front; i <= rear; i++) {
            System.out.println(queue[i]);
        }
        System.out.println("-----------------------------------------------");
    }
    
    /**
     * Displays items grouped by name, showing a count for each group.
     * Preserves the insertion order of the *first* item of each group.
     * (Corresponds to menu option 6)
     */
    public void displayGrouped() {
        if (isEmpty()) {
            System.out.println("Storage is empty!");
            return;
        }
        
        // Use LinkedHashMap to preserve the insertion order of keys
        Map<String, List<FoodType>> foodGroups = new LinkedHashMap<>();
        
        // Iterate through the queue and group items by name
        for (int i = front; i <= rear; i++) {
            FoodType item = queue[i];
            if (item == null) continue;
            
            // Add item to its corresponding list in the map
            foodGroups.computeIfAbsent(item.getName(), k -> new ArrayList<>()).add(item);
        }

        System.out.println("--- Food in Storage (grouped by name) ---");
        for (Map.Entry<String, List<FoodType>> entry : foodGroups.entrySet()) {
            FoodType firstItem = entry.getValue().get(0); // Get first item for details
            int count = entry.getValue().size();
            System.out.println(firstItem + " | Quantity: " + count);
        }
        System.out.println("-----------------------------------------------");
    }
}