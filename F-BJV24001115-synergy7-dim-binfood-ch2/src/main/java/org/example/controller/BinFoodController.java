package org.example.controller;

import org.example.view.BinFoodView;
import org.example.view.MenuView;

public class BinFoodController {
    public void mainMenu(){
        displayProducts();

        MenuView mv = new MenuView();
        mv.displayMainMenu();
    }

    private void displayProducts() {

    }
}
