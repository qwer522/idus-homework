package com.idus.homework.order.infra.repository;

import com.idus.homework.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
    List<Order> findAllByOrdererId(long ordererId);
}
