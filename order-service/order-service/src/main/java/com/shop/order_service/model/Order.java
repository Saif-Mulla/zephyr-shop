package com.shop.order_service.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.zephyr.common.enums.OrderStatus;

@Table("orders_table")
public class Order {

	@Id
	private UUID id;
	
	private OrderStatus status;
	private double total;
	private Instant createdAt;
	
	public Order(UUID id, OrderStatus status, double total, Instant createdAt) {
		this.id = id;
		this.status = status;
		this.total = total;
		this.createdAt = createdAt;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
	
	public UUID getId() {
		return id;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
}
