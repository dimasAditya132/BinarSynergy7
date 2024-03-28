package org.example.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class MenuItem {
    private int id;
    private String nama;
    private int harga;
    private int qty;

    public MenuItem(int id, String nama, int harga){
        this.id= id;
        this.nama= nama;
        this.harga = harga;
    }
}
