package com.idus.homework.order.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "idus_ordered_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orderId;

    @Column
    private Long productId;

    @Column
    private String productName;

    @Column
    private BigDecimal salePrice;

    @Column
    private Integer quantity;

}
