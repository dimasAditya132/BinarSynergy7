package org.example.service;
public interface OrderService {

    void placeOrder(int itemIndex);

    void placeOrder();

    void confirmAndPay();

    void saveOrderHistory();

    int calculateTotalPrice();
}
