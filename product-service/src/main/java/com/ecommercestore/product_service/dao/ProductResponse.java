package com.ecommercestore.product_service.dao;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {
	private Long id;

	private String name;
	private String description;
	private BigDecimal price;

}
