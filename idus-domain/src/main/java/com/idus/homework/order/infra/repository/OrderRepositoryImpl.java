package com.idus.homework.order.infra.repository;


import static com.idus.homework.order.domain.QOrder.order;

import com.idus.homework.order.domain.Order;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderCustomRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public Optional<Order> findLastOrderByOrdererId(long ordererId) {
        return from(order)
            .where(order.ordererId.eq(ordererId))
            .orderBy(order.id.desc())
            .limit(1)
            .fetch().stream().findFirst();
    }

}
