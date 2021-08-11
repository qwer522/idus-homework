package com.idus.homework.order.representation;

import com.idus.homework.order.domain.Order;
import com.idus.homework.order.domain.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrderResponse {
    @ApiModelProperty("주문ID")
    private Long id;
    @ApiModelProperty("주문번호")
    private String orderKey;
    @ApiModelProperty("총 가격")
    private BigDecimal amount;
    @ApiModelProperty("주문 상품 목록")
    private List<OrderProductResponse> products;
    @ApiModelProperty("상품상태")
    private OrderStatus status;
    @ApiModelProperty("최종 주문 일시")
    private LocalDateTime lastModifiedDate;

    @Builder
    public OrderResponse(Long id, String orderKey, BigDecimal amount,
                         List<OrderProductResponse> products, OrderStatus status, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.orderKey = orderKey;
        this.amount = amount;
        this.products = products;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static OrderResponse empty() {
        return new OrderResponse();
    }

    public static OrderResponse from(Order order) {
        return OrderResponse.builder()
            .id(order.getId())
            .orderKey(order.getOrderKey())
            .amount(order.getOrderDetail().getAmount())
            .products(order.getOrderProducts().stream().map(OrderProductResponse::from).collect(Collectors.toList()))
            .status(order.getStatus())
            .lastModifiedDate(order.getLastModifiedDate())
            .build();
    }

}
