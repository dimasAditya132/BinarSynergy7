//package com.example.BinFood.service;
//
//import com.example.BinFood.model.Order;
//import com.example.BinFood.model.Product;
//import com.example.BinFood.model.User;
//import com.example.BinFood.repository.OrderRepository;
//import com.example.BinFood.repository.ProductRepository;
//import com.example.BinFood.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Scanner;
//import java.util.UUID;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    UserRepository userRepository;
//
//    private Scanner scanner = new Scanner(System.in);
//
//    @Override
//    public Order createOrder(User user, Product product, int quantity) {
//        System.out.print("Masukkan id user: ");
//        UUID userId = UUID.fromString(scanner.nextLine());
//
//        System.out.print("Masukkan id product: ");
//        UUID productId = UUID.fromString(scanner.nextLine());
//
//        System.out.print("Masukkan quantity: ");
//        int quantity = scanner.nextInt();
//
//        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product tidak ditemukan"));
//
//        Order order = new Order();
//        order.setUser(user);
//        order.setProduct(product);
//        order.setQuantity(quantity);
//
//        return orderRepository.save(order);
//    }
//
//
//    @Override
//    public List<Order> getAllOrders() {
//        return null;
//    }
//
//    @Override
//    public Order getOrderById(UUID id) {
//        return null;
//    }
//}
