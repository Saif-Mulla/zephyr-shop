package com.shop.order_service.stream;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zephyr.common.events.OrderCreatedEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class StreamConfig {

	@Bean
	public Sinks.Many<OrderCreatedEvent> orderSink(){
		return Sinks.many().multicast().onBackpressureBuffer();
	}
	
	@Bean
	public Supplier<Flux<OrderCreatedEvent>> orderCreated(Sinks.Many<OrderCreatedEvent> orderSink){
		return orderSink::asFlux;
	}
	
}
