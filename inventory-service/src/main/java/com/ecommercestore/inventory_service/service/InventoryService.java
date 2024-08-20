package com.ecommercestore.inventory_service.service;

import org.springframework.stereotype.Service;

import com.ecommercestore.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	public Boolean isInStock(String skucode) {
		return inventoryRepository.findBySkuCode(skucode).isPresent();
	}
	
}
