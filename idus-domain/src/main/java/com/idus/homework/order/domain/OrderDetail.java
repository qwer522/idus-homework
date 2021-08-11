package com.idus.homework.order.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "idus_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orderId;

    @Column
    private BigDecimal amount;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime createdDate;

    @Column
    private String lastModifiedBy;

    @Column
    private LocalDateTime lastModifiedDate;
}
