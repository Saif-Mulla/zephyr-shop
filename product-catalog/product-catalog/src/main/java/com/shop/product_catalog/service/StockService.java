package com.shop.product_catalog.service;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.product_catalog.model.Product;
import com.shop.product_catalog.repository.ProductRepository;
import com.zephyr.common.events.StockReservedEvent;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {

	private final ProductRepository productRepo;
	
	public StockService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	public Mono<StockReservedEvent> reserveStock(UUID orderId, Map<String, Integer> items){
		
		Flux<Product> products = Flux.fromIterable(items.entrySet())
				.flatMap(item -> reserveOneStock(item.getKey(), item.getValue()));
		
		return products
				.then(Mono.fromCallable( () -> new StockReservedEvent(orderId, true)))
				.onErrorResume(error -> Mono.just(new StockReservedEvent(orderId, false, error.getMessage())));
		
	}

	private Mono<Product> reserveOneStock(String sku, int qty){
		return productRepo.findBySku(sku)
				.filter(p -> p.getStock() >= qty)
				.switchIfEmpty(Mono.error(new IllegalStateException("Out of Stock: "+sku)))
				.flatMap(
						p -> {
								p.setStock(p.getStock()-qty);
								return productRepo.save(p);
							}
						);
	}

}
