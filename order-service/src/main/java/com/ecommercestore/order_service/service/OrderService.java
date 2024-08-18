package com.ecommercestore.order_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ecommercestore.order_service.dto.OrderLineItemsDto;
import com.ecommercestore.order_service.dto.OrderRequest;
import com.ecommercestore.order_service.model.Order;
import com.ecommercestore.order_service.model.OrderLineItems;
import com.ecommercestore.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	// method to place an order
	public void placeOrder (OrderRequest orderRequest){
		Order order= new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(this:: mapToDto)
			.toList();
		order.setOrderLineItemList(orderLineItems);
		Order savedOder = orderRepository.save(order);
		log.info("Oder with order id {} is saved successfully!", savedOder.getId());
	}
	
	// Helpful methods
	
	// Setting the odered items into OrderLineItems object from OrderLineItemsDto
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems=new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		return orderLineItems;
	}
	
}
