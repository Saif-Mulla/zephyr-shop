package com.zephyr.common.events;

import java.util.UUID;

import lombok.Data;


public class StockReservedEvent {

	UUID orderId;
	boolean success;
	String reason; // empty if success
	
	
	public StockReservedEvent(UUID orderId, boolean success, String reason) {
		this.orderId = orderId;
		this.success = success;
		this.reason = reason;
	}
	
	public StockReservedEvent(UUID orderId, boolean success) {
		this.orderId = orderId;
		this.success = success;
	}
}
