package com.idus.homework.order.domain;

import com.idus.homework.common.data.GenerateKey;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "idus_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GenerateKey
    @Column
    private String orderKey;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "idus_order_order_detail"),
        nullable = false)
    private OrderDetail orderDetail;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private List<OrderProduct> orderProducts;

    @Column
    private Long ordererId;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime createdDate;

    @Column
    private String lastModifiedBy;

    @Column
    private LocalDateTime lastModifiedDate;
}
