package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Order {
    private Object item;
    private int quantity;

    public int calculateTotalPrice() {
        if (item instanceof Food foodItem) {
            return foodItem.getPrice() * quantity;
        } else if (item instanceof Beverage beverageItem) {
            return beverageItem.getPrice() * quantity;
        }
        return 0;
    }

    public String getItemName() {
        if (item instanceof Food foodItem) {
            return foodItem.getName();
        } else if (item instanceof Beverage beverageItem) {
            return beverageItem.getName();
        }
        return "";
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        if (item instanceof Food foodItem) {
            return String.valueOf(foodItem.getPrice());
        } else if (item instanceof Beverage beverageItem) {
            return String.valueOf(beverageItem.getPrice());
        }
        return "";
    }

    public int getTotal(){
        return calculateTotalPrice();
    }
}
