package com.idus.homework.order.app;

import com.idus.homework.order.domain.Order;
import com.idus.homework.order.infra.service.OrderQueryService;
import com.idus.homework.order.representation.OrderListResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class OrderAppService {

    private final OrderQueryService orderQueryService;

    public OrderAppService(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    public OrderListResponse findAllByOrdererId(long ordererId) {
        List<Order> orders = orderQueryService.findAllByOrdererId(ordererId);
        if (ObjectUtils.isEmpty(orders)) {
            return OrderListResponse.empty();
        }
        return OrderListResponse.from(orders);
    }

}