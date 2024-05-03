package com.example.BinarFood.service;

import com.example.BinarFood.model.User;
import com.example.BinarFood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;

    @Override
    public boolean addUser() {

        System.out.print("Masukkan nama anda: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan email anda: ");
        String email = scanner.nextLine();

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUsername(name);
        user.setEmail_address(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        return savedUser != null;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User updateUser() {
        System.out.print("Masukkan username yang ingin diupdate: ");
        String oldUsername = scanner.nextLine();

        System.out.println("Pilih opsi: ");
        System.out.println("1. Ubah username");
        System.out.println("2. Ubah email");
        System.out.println("3. Ubah password");
        System.out.println("4. Kembali");
        System.out.print("Pilih opsi: ");
        String option = scanner.nextLine();

        User user = userRepository.findByUsername(oldUsername)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));

        String newUsername = user.getUsername();
        String newEmail = user.getEmail_address();
        String newPassword = user.getPassword();

        switch (option) {
            case "1":
                System.out.print("Masukkan username baru: ");
                newUsername = scanner.nextLine();
                break;
            case "2":
                System.out.print("Masukkan email baru: ");
                newEmail = scanner.nextLine();
                break;
            case "3":
                System.out.print("Masukkan password baru: ");
                newPassword = scanner.nextLine();
                break;
            case "4":
                return null;
            default:
                System.out.println("Opsi tidak valid. Silakan masukkan angka 1-4.");
                return null;
        }

        user.setUsername(newUsername);
        user.setEmail_address(newEmail);
        user.setPassword(newPassword);

        return userRepository.save(user);
    }

    @Override
    public User deleteUser() {
        System.out.print("Masukkan username yang ingin dihapus: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password untuk konfirmasi: ");
        String password = scanner.nextLine();

        if (confirmUser(username, password)) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
            userRepository.delete(user);
            return user;
        } else {
            throw new IllegalArgumentException("Username atau Password salah");
        }
    }

    @Override
    public boolean confirmUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));

        return user.getPassword().equals(password);
    }
}
