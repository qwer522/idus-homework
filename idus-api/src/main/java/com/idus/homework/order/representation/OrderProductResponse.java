package com.idus.homework.order.representation;

import com.idus.homework.order.domain.OrderProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class OrderProductResponse {
    @ApiModelProperty("주문상품ID")
    private Long orderProductId;
    @ApiModelProperty("상품ID")
    private Long productId;
    @ApiModelProperty("상품이름")
    private String productName;
    @ApiModelProperty("상품가격")
    private BigDecimal salePrice;
    @ApiModelProperty("상품수량")
    private Integer quantity;

    @Builder
    public OrderProductResponse(Long orderProductId, Long productId, String productName,
                                BigDecimal salePrice, Integer quantity) {
        this.orderProductId = orderProductId;
        this.productId = productId;
        this.productName = productName;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }

    public static OrderProductResponse from(OrderProduct orderProduct) {
        return OrderProductResponse.builder()
            .orderProductId(orderProduct.getId())
            .productId(orderProduct.getProductId())
            .productName(orderProduct.getProductName())
            .salePrice(orderProduct.getSalePrice())
            .quantity(orderProduct.getQuantity())
            .build();
    }

}