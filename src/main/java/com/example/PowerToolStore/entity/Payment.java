package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="payments")
public class Payment {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @NotNull
//    @Column(unique = true, nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false, unique = true)
    private Order order;

    @NotNull
    @Column(nullable = false)
    private String paymentMode;

    @NotNull
    @Column(nullable = false)
    private String status;

    private String paymentPartner;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime receivedAt;

    protected Payment(){}
}
