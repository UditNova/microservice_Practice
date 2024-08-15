package com.ecommercestore.product_service.dao;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {
	private String name;
	private String description;
	private BigDecimal price;
}
