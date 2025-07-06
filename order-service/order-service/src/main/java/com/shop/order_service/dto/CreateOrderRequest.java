package com.shop.order_service.dto;

import java.util.Map;

import jakarta.validation.constraints.Positive;

public class CreateOrderRequest {

	private Map<String,@Positive Integer> items;
	private double totalAmount;	
	
	public Map<String, Integer> getItems() {
		return items;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
