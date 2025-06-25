package com.shop.product_catalog.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.product_catalog.model.Product;

import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, UUID> {
	
	Mono<Product> findBySku(String sku);
	
}
