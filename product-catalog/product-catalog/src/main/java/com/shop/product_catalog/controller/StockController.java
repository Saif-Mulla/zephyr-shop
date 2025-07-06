package com.shop.product_catalog.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.product_catalog.model.Product;
import com.shop.product_catalog.service.StockService;
import com.zephyr.common.events.OrderCreatedEvent;
import com.zephyr.common.events.StockReservedEvent;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class StockController {

	private StockService service;
	
	public StockController(StockService service) {
		this.service = service;
	}
	
	@GetMapping("/{sku}")
	public Mono<Product> getProductbySKU(@PathVariable String sku){
		return service.getProductbySKU(sku);
		
	}
	
	@PostMapping("/reserve")
	public Mono<StockReservedEvent> reserveStock(@RequestBody OrderCreatedEvent request ){
		return service.reserveStock(request.getOrderId(), request.getItems());
	}

}
