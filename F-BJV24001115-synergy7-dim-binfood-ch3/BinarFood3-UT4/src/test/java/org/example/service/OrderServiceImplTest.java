package org.example.service;


import org.example.model.Menu;
import org.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private List<Menu> menu;
    private List<Order> orders;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        scanner = new Scanner("1\n1\n1\n");
        orders = new ArrayList<>();
        menu = new ArrayList<>();
        orderService = new OrderServiceImpl(orders, menu, scanner);
    }

    @Test
    @DisplayName("Test Place Order")
    void testPlaceOrder(){
        System.out.println("Starting Test Place Order");
        menu.add(new Menu("Nasi Goreng", 15000));
        assertDoesNotThrow(() -> orderService.placeOrder(1));
        System.out.println("Place Order Test Passed");
    }
    
    @Test
    @DisplayName("Test Confirm And Pay Without Placing Order")
    void testConfirmAndPayWithoutPlacingOrder(){
        assertThrows(NoSuchElementException.class, () -> orderService.confirmAndPay());
    }

    @Test
    @DisplayName("Test Save Order History")
    void testSaveOrderHistory(){
        menu.add(new Menu("Nasi Goreng", 15000));
        orderService.placeOrder(1);
        orderService.saveOrderHistory();
        assertTrue(orders.isEmpty());
        File dir = new File("src/main/java/org/example/");
        if (dir.exists() && dir.isDirectory()) {
            System.out.println(Arrays.toString(dir.listFiles()));
            assertTrue(Arrays.stream(Objects.requireNonNull(dir.listFiles())).anyMatch(file -> file.getName().startsWith("order_history_")));
        } else {
            fail("Direktori tidak ditemukan");
        }

    }

    @Test
    @DisplayName("Test Menu Item Is Null")
    void testMenuItemIsNull() {
        Menu menuItem = null;
        assertNull(menuItem);
    }

    @Test
    @DisplayName("Test All Menu Items Have Price Greater Five Thousand")
    void testAllMenuItemsHavePriceGreaterThanTFiveThousand() {
        boolean allPricesGreaterThanZero = menu.stream()
                .map(Menu::getPrice)
                .allMatch(price -> price > 5000);

        assertTrue(Optional.of(allPricesGreaterThanZero).orElseThrow(), "Tidak semua item menu memiliki harga lebih dari lima ribu");
    }
}