package com.example.BinFood;

import com.example.BinFood.controller.HomeController;
import com.example.BinFood.controller.ProductController;
import com.example.BinFood.controller.UserController;
import com.example.BinFood.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BinFoodApplication {

	public static void main(String[] args) {

			ConfigurableApplicationContext context = SpringApplication.run(BinFoodApplication.class, args);
			HomeController homeController = context.getBean(HomeController.class);
			homeController.mainMenu();
		}

}
