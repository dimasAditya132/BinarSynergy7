package org.example.view;

import org.example.model.entity.MenuItem;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BinFoodView {
    public void displayProducts(Map<Long, MenuItem> menuItemMap){
        Set<Long> setId = Data.menuItemMap.keySet();
        Iterator<Long> iterator = setId.iterator();
        while (iterator.hasNext()){
            Long key = iterator.next();
            MenuItem menu = Data.menuItemMap.get(key);
            System.out.println(menu.getId()+" | " + menu.getNama() +" \\t | " + menu.getHarga());
        }
    }

    private void separatorLine(){
        System.out.println("==============================");
    }

    private void titleHeader(){
        System.out.println();
    }
}
