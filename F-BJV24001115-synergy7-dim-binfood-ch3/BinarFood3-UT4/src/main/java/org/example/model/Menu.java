package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Setter
@AllArgsConstructor
@Getter
public class Menu {
    private static int counter = 0;
    private int id;
    @NonNull
    private String name;
    private int price;
    public Menu(String name, int price) {
        if (counter >= Integer.MAX_VALUE) {
            throw new IllegalStateException("Maximum number of Menu items reached, cannot create more.");
        }
        this.id = ++counter;
        this.name = name;
        this.price = price;
    }

}
