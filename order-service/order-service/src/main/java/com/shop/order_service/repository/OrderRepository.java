package com.shop.order_service.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.shop.order_service.model.Order;

@Repository
public interface OrderRepository extends R2dbcRepository<Order, UUID> {

}
