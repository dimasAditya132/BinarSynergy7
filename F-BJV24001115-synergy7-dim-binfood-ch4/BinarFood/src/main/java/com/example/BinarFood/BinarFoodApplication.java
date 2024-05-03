package com.example.BinarFood;

import com.example.BinarFood.controller.MerchantController;
import com.example.BinarFood.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BinarFoodApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BinarFoodApplication.class, args);


		UserController userController = context.getBean(UserController.class);
		userController.userMenu();

//		MerchantController merchantController = context.getBean(MerchantController.class);
//		merchantController.updateMerchantStatus();
//		merchantController.addMerchant();
//		merchantController.mainMenuMerchant();
	}

}
