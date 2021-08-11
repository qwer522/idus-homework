package com.idus.homework.order.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.idus.homework.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 주문아이디로_주문정보_조회에_성공한다() {
        //given
        Long orderId = Long.valueOf(1);

        //when
        List<Order> orders = orderRepository.findAllByOrdererId(orderId);

        //then
        assertFalse(orders.isEmpty());
    }

    @Test
    public void 회원아이디로_마지막_주문정보_주문아이디_2의_조회를_성공한다() {
        //given
        Long ordererId = Long.valueOf(1);
        Long lastOrderId = Long.valueOf(2);

        //when
        Optional<Order> order = orderRepository.findLastOrderByOrdererId(ordererId);

        //then
        assertEquals(order.get().getId(), lastOrderId);
    }
}