package org.example;

import lombok.Getter;
import org.example.model.Menu;

import java.util.ArrayList;
import java.util.List;
public class Data {
    @Getter
    private static final List<Menu> menu = new ArrayList<>();

    public static void initializeMenu() {
        getMenu().clear();
        try {
            menu.add(new Menu("Nasi Goreng", 15000));
            menu.add(new Menu("Mie Goreng", 13000));
            menu.add(new Menu("Nasi + Ayam", 18000));
            menu.add(new Menu("Es Teh Manis", 5000));
            menu.add(new Menu("Es Jeruk ", 7000));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayMenu(){
        for (int i = 0; i < menu.size(); i++){
            Menu item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + "\t |  Rp. " + item.getPrice());
        }
    }
}
