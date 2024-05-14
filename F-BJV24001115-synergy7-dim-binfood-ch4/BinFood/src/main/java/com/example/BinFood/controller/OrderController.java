package com.example.BinFood.controller;

import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    MerchantController merchantController;
    @Autowired
    OrderDetailService orderDetailService;
    private String usernameFromUser = null;
    private int merchantFromUser = -1;
    private int productFromUser = -1;
    private String chosenMerchant = null;
    private int quantityFromUser = 0;
    private List<OrderDetail> OrderDetailOfUserOrder = new ArrayList<>();

    public void orderMenu() {
        System.out.println();
        System.out.println("Selamat Datang di Binar Food");
        System.out.println("Silahkan Masukkan Username");
        System.out.print("Username: ");
        this.usernameFromUser = scanner.nextLine();
        try {
            userService.getUserByName(usernameFromUser);
        } catch (Exception e) {
            System.out.println("Silahkan Ulang Pengisian");
            this.orderMenu();
        }
        System.out.print("Destinasi: ");
        String destinasi = scanner.nextLine();
        orderService.addOrderToDB(orderService.orderBuilder(usernameFromUser, new Date(), destinasi, true));
        System.out.println();
        System.out.println("Silahkan pilih merchant: ");
        System.out.println("Berikut ini ada list merchant yang sedang aktif: ");
        System.out.println("Nama merchant \t|\t Lokasi Merchant");
        merchantService.openNowMerchant();
        System.out.println();
        System.out.println("Pilih merchant yang diinginkan: ");
        System.out.print("=> ");
        this.merchantFromUser = scanner.nextInt();
        scanner.nextLine();
        this.chosenMerchant = merchantService.listOfMerchant().get(merchantFromUser).getMerchantName();

        try {
            System.out.println("Berikut ini adalah produk-produk dari " + chosenMerchant);
            productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant, 0));
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan silahkan ulangi");
            merchantController.merchantMenu();
        }
        this.addOrder();
    }

    public void addOrder() {
        System.out.println();
        System.out.println("Berikut ini adalah produk-produk dari " + chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant, 0));
        System.out.println("Pilih produk yang mana?");
        System.out.println("(-1 untuk kembali)");
        System.out.print("=>");
        this.productFromUser = scanner.nextInt();
        scanner.nextLine();
        if (productFromUser != -1) {
            System.out.print("Kuantitasnya: ");
            quantityFromUser = scanner.nextInt();
            scanner.nextLine();
            double fp = orderDetailService.finalPrice(productService.ListOfAvailableProduct(chosenMerchant, 0).get(productFromUser), quantityFromUser);
            this.OrderDetailOfUserOrder.add(orderDetailService.buildOrderDetail(quantityFromUser
                    , fp
                    , usernameFromUser
                    , productService.ListOfAvailableProduct(chosenMerchant, 0).get(productFromUser).getProductName()
                    , chosenMerchant));
            System.out.println("Berikut ini ordernya: ");
            Double total = OrderDetailOfUserOrder.stream()
                    .map(val -> {
                        System.out.println(val.getProductId() + "\t|\t" + val.getQuantity() + "\t|\t" + val.getTotal_price());
                        return val;
                    })
                    .reduce(0.0, (aDouble, detailResponse) -> aDouble + detailResponse.getTotal_price(), Double::sum);
            System.out.println("Total: " + total);
            System.out.println();
            System.out.println("Konfirmasi? ");
            System.out.println("1. Ya");
            System.out.println("2. Batal / Mau Pilih lagi");
            int confirmChoice = scanner.nextInt();
            scanner.nextLine();
            if (confirmChoice != 2) {
                OrderDetailOfUserOrder
                        .stream()
                        .map(val -> {
                            orderDetailService.addOrderDetailToDB(val);
                            return val;
                        })
                        .collect(Collectors.toList());
                this.OrderDetailOfUserOrder = new ArrayList<>();
            } else {
                this.addOrder();
            }
        } else {
            this.orderMenu();
        }
    }
}
