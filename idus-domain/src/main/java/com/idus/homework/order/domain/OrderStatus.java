package com.idus.homework.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDERED("주문생성"),
    PAYMENTED("결제완료"),
    ORDERED_EXPIRATION("주문만료"),
    PAYMENT_FAILED("결제실패"),
    COMPLETED("주문완료"),
    CANCELLED("주문취소");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
