package com.ecommercestore.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommercestore.product_service.dao.ProductRequest;
import com.ecommercestore.product_service.dao.ProductResponse;
import com.ecommercestore.product_service.entity.Product;
import com.ecommercestore.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		
		Product product= Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		Product savedProduct = productRepository.save(product);
		log.info("New Product with id= {} added successfully!", savedProduct.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts.stream().map(this::mapToProductResponse).toList();
	}
	
	public ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
