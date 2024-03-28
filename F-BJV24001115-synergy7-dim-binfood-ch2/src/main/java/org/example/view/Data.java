package org.example.view;

import lombok.Getter;
import lombok.Setter;

import org.example.model.entity.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter

public class Data {
    public static List<MenuItem> menuItemList = new ArrayList<>();

    public static Map<Long, MenuItem> menuItemMap = new HashMap<>();
    public static void initiateMenu(){
        MenuItem nasgor = new MenuItem();
        nasgor.setId(1);
        nasgor.setNama("Nasi Goreng");
        nasgor.setHarga(15000);

        MenuItem miegor = new MenuItem();
        miegor.setId(2);
        miegor.setNama("Mie Goreng");
        miegor.setHarga(13000);

        MenuItem nasyam = new MenuItem();
        nasyam.setId(3);
        nasyam.setNama("Nasi + Ayam");
        nasyam.setHarga(18000);

        MenuItem esteh = new MenuItem();
        esteh.setId(4);
        esteh.setNama("Es Teh Manis");
        esteh.setHarga(3000);

        MenuItem esjeruk = new MenuItem();
        esjeruk.setId(5);
        esjeruk.setNama("Es Jeruk");
        esjeruk.setHarga(5000);

        menuItemList.add(nasgor);
        menuItemList.add(miegor);
        menuItemList.add(nasyam);
        menuItemList.add(esteh);
        menuItemList.add(esjeruk);

        menuItemMap.put((long) nasgor.getId(), nasgor);
        menuItemMap.put((long) miegor.getId(), miegor);
        menuItemMap.put((long) nasyam.getId(), nasyam);
        menuItemMap.put((long) esteh.getId(), esteh);
        menuItemMap.put((long) esjeruk.getId(), esjeruk);


    }

}
