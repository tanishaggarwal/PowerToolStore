package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="shipments")
public class Shipment {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipmentId;

//    @Column(nullable = false, unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @DecimalMin("0.0")
    @Column(nullable = false, scale = 2)
    private BigDecimal shippingCharge;

    @Column(nullable = false)
    private String shippingPartner;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deliveredAt;

    private LocalDateTime estimatedAt;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String trackingId;

    @Column(nullable = false)
    private Integer shippingAddressPincode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String address;

    protected Shipment(){};
}
