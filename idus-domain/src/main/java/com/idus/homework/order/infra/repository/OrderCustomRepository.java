package com.idus.homework.order.infra.repository;


import com.idus.homework.order.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderCustomRepository {
    Optional<Order> findLastOrderByOrdererId(long ordererId);
}
