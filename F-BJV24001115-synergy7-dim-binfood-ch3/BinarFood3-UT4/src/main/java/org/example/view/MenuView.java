package org.example.view;

import org.example.Data;

public class MenuView {

    public static void displayMenu() {
        Data.displayMenu();
        Data.initializeMenu();
    }

    public static void displayControlMenu(){
        System.out.println("""
                99. Konfirmasi dan Bayar
                0. Keluar
                """);
    }

    public static String displayTitle(){
        return "Selamat Datang di BinFood";
    }

    public static String separatorLine(){
        return "===============================";
    }
}
