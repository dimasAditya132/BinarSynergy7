package org.example;

import org.example.models.Beverage;
import org.example.models.Food;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static final List<Object> menu = new ArrayList<>();

    private Data() {

    }

    static void initializeMenu() {
        menu.add(new Food("Nasi Goreng", 15000));
        menu.add(new Food("Mie Goreng", 13000));
        menu.add(new Food("Nasi + Ayam", 18000));
        menu.add(new Beverage("Es Teh Manis", 5000, "Medium"));
        menu.add(new Beverage("Es Jeruk", 7000, "Medium"));
    }

    public static List<Object> getMenu() {
        return menu;
    }

    public static void displayMenu() {
        System.out.println("Menu : ");
        for (int i = 0; i < menu.size(); i++) {
            Object item = menu.get(i);
            if (item instanceof Food foodItem) {
                System.out.println((i + 1) + ". " + foodItem.getName() + " - Rp" + foodItem.getPrice());
            } else if (item instanceof Beverage beverageItem) {
                System.out.println((i + 1) + ". " + beverageItem.getName() + " - Rp" + beverageItem.getPrice());
            }
        }
    }
}
