package com.ecommercestore.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecommercestore.inventory_service.entity.Inventory;
import com.ecommercestore.inventory_service.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory=new Inventory();
			inventory.setSkuCode("Iphone_13");
			inventory.setQuantity(100);
			
			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("Iphone_15");
			inventory1.setQuantity(0);
			
			// Saving the dummy data
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
