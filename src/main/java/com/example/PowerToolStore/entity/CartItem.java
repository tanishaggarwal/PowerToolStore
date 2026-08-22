package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name="cartItems")
public class CartItem {
    @Setter(AccessLevel.NONE)
//    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

//    @NotNull
//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

//    @NotNull
    @Column(nullable = false)
//    @Min(0)
    private Integer quantity;

    protected CartItem(){}
}
