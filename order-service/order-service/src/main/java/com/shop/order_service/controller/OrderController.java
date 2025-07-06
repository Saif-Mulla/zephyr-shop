package com.shop.order_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.order_service.Service.OrderSaga;
import com.shop.order_service.dto.CreateOrderRequest;
import com.shop.order_service.model.Order;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderSaga service;
	
	public OrderController(OrderSaga service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public Mono<Order> createOrder(@RequestBody CreateOrderRequest req){
		return service.createOrder(req);
	}
	
}
