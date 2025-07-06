package com.shop.product_catalog.stream;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.product_catalog.service.StockService;
import com.zephyr.common.events.OrderCreatedEvent;
import com.zephyr.common.events.StockReservedEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class StreamConfig {

	private StockService service;
	
	@Bean
    public Sinks.Many<StockReservedEvent> stockSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }
	
	@Bean
	public Consumer<OrderCreatedEvent> onOrder(){
		return evt -> service.reserveStock(evt.getOrderId(), evt.getItems())
				.subscribe();
	}
	
	@Bean
    public Supplier<Flux<StockReservedEvent>> stockReserved(Sinks.Many<StockReservedEvent> stockSink) {
        return stockSink::asFlux;
    }
	
}
