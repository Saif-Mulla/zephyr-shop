package com.shop.product_catalog.service;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.product_catalog.model.Product;
import com.shop.product_catalog.repository.ProductRepository;
import com.zephyr.common.events.StockReservedEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class StockService {

	private final ProductRepository productRepo;
	private final Sinks.Many<StockReservedEvent> stockSink;
	
	public StockService(ProductRepository productRepo, Sinks.Many<StockReservedEvent> stockSink) {
		this.productRepo = productRepo;
		this.stockSink = stockSink;
	}
	
	public Mono<StockReservedEvent> reserveStock(UUID orderId, Map<String, Integer> items){
		
		Flux<Product> products = Flux.fromIterable(items.entrySet())
				.flatMap(item -> reserveOneStock(item.getKey(), item.getValue()));
		
		return products
				.then(Mono.fromCallable(
						() -> {
							StockReservedEvent success = new StockReservedEvent(orderId, true);
							stockSink.tryEmitNext(success);
							return success;
						}))
				.onErrorResume(error -> {
					StockReservedEvent fail = new StockReservedEvent(orderId, false, error.getMessage());
					stockSink.tryEmitNext(fail);
					return Mono.just(fail);
				});
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

	public Mono<Product> getProductbySKU(String sku) {
		return productRepo.findBySku(sku);
	}

}
