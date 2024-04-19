package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Order {
    private Menu item;
    private int quantity;

    public int calculateOrder(){
        return item.getPrice() * quantity;
    }
}
