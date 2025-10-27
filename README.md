Fast-Food Storage Manager

Project Overview

This is a simple Java console application that simulates managing a fast-food storage system. It uses a custom array-based Queue with a fixed capacity (FIFO - First-In, First-Out) to manage "food items".

The application demonstrates core object-oriented principles:

Inheritance: A base FoodType class is extended by specific food items like Burger, Pizza, etc.

Polymorphism: The queue stores all items as FoodType, allowing different food types to be managed in one data structure.

Encapsulation: The Queue class and FoodType class hide their internal data, providing public methods for interaction (e.g., enqueue(), dequeue(), getters/setters).

Key Classes

the_resto_comp/The_resto_comp.java: The main application class that runs the user menu and contains all program logic.

the_resto_comp/Queue.java: The custom array-based queue implementation. It manages adding, removing, and viewing items.

the_resto_comp/foodtype/FoodType.java: The abstract base class for all food items.

the_resto_comp/foodtype/AddNewFood.java: A special class that interactively prompts the user to create a new, custom food item.

the_resto_comp/foodtype/Burger.java (and other presets): Preset food items with hard-coded values.

Features

The main menu provides the following options:

Add a preset food item: Lets you choose from a list (Burger, Pizza, etc.).

Add new custom food item: Prompts you for a name, weight, and best-before date.

Remove the next food item: Dequeues the item from the front of the queue.

Display the food item at the front: Peeks at the next item without removing it.

Display all food items in order: Shows all items from front to back.

Display all food items grouped by name: Shows a summary of items and their quantities.

Search food by name: Performs a binary search on the items in the queue.

Exit: Terminates the application.

How to Run

Directory Structure: Ensure all files are in the correct package directories:

.
└── the_resto_comp/
    ├── The_resto_comp.java
    ├── Queue.java
    │
    └── foodtype/
        ├── FoodType.java
        ├── AddNewFood.java
        ├── Burger.java
        ├── Pizza.java
        ├── Fries.java
        ├── Hotdog.java
        └── Sandwich.java


Run the main class, The_resto_comp.java


