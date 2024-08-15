package com.ecommercestore.product_service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ecommercestore.product_service.dao.ProductRequest;
import com.ecommercestore.product_service.entity.Product;
import com.ecommercestore.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Container
	private static final MySQLContainer<?> mySqlContainer = new MySQLContainer<>("mysql:latest")
			.withDatabaseName("productdb").withUsername("testuser").withPassword("testpass");

	@BeforeEach
	void setup() {
		productRepository.deleteAll(); // Clean up before each test
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest("iphone 15", "The unique featured set.",
				BigDecimal.valueOf(75000));

		String productRequestAsString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON)
				.content(productRequestAsString))
		.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@Test
	void shouldGetProducts() throws Exception {
		ProductRequest product1 = getProductRequest("iphone 15", "The unique featured set.", BigDecimal.valueOf(75000));
		ProductRequest product2 = getProductRequest("Samsung Galaxy S22", "The powerful smartphone.", BigDecimal.valueOf(65000));
		
		productRepository.saveAll(List.of(
		        toEntity(product1), 
		        toEntity(product2)
		    ));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				 .andExpect(jsonPath("$", hasSize(2)))
				 .andExpect(jsonPath("$[0].name", is("iphone 15")))
				 .andExpect(jsonPath("$[1].name", is("Samsung Galaxy S22")));				
	}

	private ProductRequest getProductRequest(String name, String description, BigDecimal price) {
		return ProductRequest.builder().name(name).description(description).price(price).build();
	}

	// Add the toEntity() method
	public Product toEntity(ProductRequest productRequest) {
		return Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
	}

}
