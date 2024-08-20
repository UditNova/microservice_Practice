package com.ecommercestore.inventory_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommercestore.inventory_service.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	@Transactional(readOnly = true)
	Optional<Inventory> findBySkuCode(String skuCode);

}
