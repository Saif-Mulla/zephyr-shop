package com.shop.order_service.Service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shop.order_service.dto.CreateOrderRequest;
import com.shop.order_service.model.Order;
import com.shop.order_service.repository.OrderRepository;
import com.zephyr.common.enums.OrderStatus;
import com.zephyr.common.events.OrderCreatedEvent;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class OrderSaga {

	private final OrderRepository repo;
	private final Sinks.Many<OrderCreatedEvent> orderSink;
	
	public OrderSaga(OrderRepository repo, Sinks.Many<OrderCreatedEvent> orderSink) {
		this.repo = repo;
		this.orderSink = orderSink;
	}
	
	public Mono<Order> createOrder(CreateOrderRequest req){
		Order placedOrder = new Order(null, OrderStatus.ORDERED, req.getTotalAmount(), Instant.now());
		return repo.save(placedOrder)
				.doOnSuccess(
						savedOrder -> orderSink.tryEmitNext(new OrderCreatedEvent(savedOrder.getId(), req.getItems()))
						);
	}
	
}
