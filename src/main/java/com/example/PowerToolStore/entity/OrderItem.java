package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="order_items")
public class OrderItem {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    @NotNull
//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @NotNull
//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    private Integer quantity;

    @NotNull
    @DecimalMin("0.0")
    @Column(scale = 2, nullable = false)
    private BigDecimal lineTotal;

    @NotNull
    @DecimalMin("0.0")
    @Column(scale = 2, nullable = false)
    private BigDecimal priceAtPurchase;

    protected OrderItem(){}
}
