package com.zephyr.common.events;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderCreatedEvent {
	UUID orderId;
	Map<String, Integer> items; // sku + qty
	
	public Map<String, Integer> getItems() {
		return items;
	}
	
	public UUID getOrderId() {
		return orderId;
	}
	
	public OrderCreatedEvent(UUID orderId, Map<String, Integer> items) {
		this.items = items;
		this.orderId = orderId;
	}
	
}
