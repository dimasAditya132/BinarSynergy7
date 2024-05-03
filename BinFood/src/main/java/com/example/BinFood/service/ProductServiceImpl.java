package com.example.BinFood.service;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.model.Product;
import com.example.BinFood.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantService merchantService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean addProduct() {
        System.out.print("Masukkan nama produk: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan harga produk: ");
        double price = scanner.nextDouble();

        System.out.print("Masukkan id merchant: ");
        UUID merchantId = UUID.fromString(scanner.next());

        Merchant merchant = merchantService.getMerchantById(merchantId);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setMerchant(merchant);

        Product savedProduct = productRepository.save(product);

        return savedProduct != null;
    }


    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct() {
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String name = scanner.nextLine();

        Product product = productRepository.findByName(name);

        if (product != null) {
            productRepository.delete(product);
            return "Product berhasil dihapus";
        } else {
            return "Product tidak ditemukan";
        }
    }

    @Override
    public Product updateProduct() {
        System.out.print("Masukkan ID produk: ");
        UUID id = UUID.fromString(scanner.next());

        System.out.println("Pilih Opsi :");
        System.out.println("1. Ubah nama produk");
        System.out.println("2. Ubah harga produk");
        String option = scanner.next();

        Product product = productRepository.findById(id).orElse(null);

        String newProductName = product.getName();
        double newProductPrice = product.getPrice();
        UUID newMerchantId = product.getMerchant().getId();

        switch (option) {
            case "1":
                System.out.print("Masukkan nama produk: ");
                newProductName = scanner.nextLine();
                break;
            case "2":
                System.out.print("Masukkan harga produk: ");
                newProductPrice = scanner.nextDouble();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 1 and 3.");
        }
        product.setName(newProductName);
        product.setPrice(newProductPrice);

        return productRepository.save(product);
    }
}
