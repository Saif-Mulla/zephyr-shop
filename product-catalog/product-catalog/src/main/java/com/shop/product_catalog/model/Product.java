package com.shop.product_catalog.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	private UUID id;
	
	private String sku;
	private String name;
	private double price;
	private int stock;
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String getSku() {
		return sku;
	}
}
