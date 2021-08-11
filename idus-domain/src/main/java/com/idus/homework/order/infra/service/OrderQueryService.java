package com.idus.homework.order.infra.service;

import com.idus.homework.order.domain.Order;
import com.idus.homework.order.infra.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public OrderQueryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAllByOrdererId(long ordererId) {
        return orderRepository.findAllByOrdererId(ordererId);
    }

    public Optional<Order> findLastOrderByOrdererId(long ordererId) {
        return orderRepository.findLastOrderByOrdererId(ordererId);
    }

}