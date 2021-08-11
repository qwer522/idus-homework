package com.idus.homework.order.representation;

import com.idus.homework.order.domain.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrderListResponse {

    @ApiModelProperty("주문목록")
    private List<OrderResponse> orders;

    public OrderListResponse(List<OrderResponse> orders) {
        this.orders = orders;
    }

    public static OrderListResponse empty() {
        return new OrderListResponse(Collections.EMPTY_LIST);
    }

    public static OrderListResponse from(List<Order> orders) {
        return orderToResponse(orders.stream().map(OrderResponse::from).collect(Collectors.toList()));
    }

    public static OrderListResponse orderToResponse(List<OrderResponse> orders) {
        return new OrderListResponse(orders);
    }

}
