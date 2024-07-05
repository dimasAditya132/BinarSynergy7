package com.ch.binarfud.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${firebase.credentials.path}")
    private String credentialsPath;

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            FileInputStream serviceAccount = new FileInputStream(new ClassPathResource(credentialsPath).getFile());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException("Error reading firebase credentials file", e);
        }
    }
}
