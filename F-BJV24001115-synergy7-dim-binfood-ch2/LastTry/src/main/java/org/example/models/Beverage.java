package org.example.models;

public class Beverage {
    private String name;
    private int price;
    private String size;

    public Beverage(String name, int price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
