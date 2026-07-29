package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="carts")
public class Cart {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @NotNull
//    @Column(nullable = false, unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false, unique = true)
    private User user;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.REMOVE)
    private List<CartItem> cartItems;

    protected Cart(){}
}
