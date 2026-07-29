package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="products")
public class Product {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotNull
//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @NotNull
    @Column(nullable = false)
    private String brand;

    @NotNull
    @Column(nullable = false)
    private String title;

    private String description;

    @NotNull
    @URL
    @Column(nullable = false)
    private String imageUrl;

    @NotNull
    @Column(scale = 2, nullable = false)
    @DecimalMin("0.0")
    private BigDecimal maxRetailPrice;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    @Column(scale = 2, nullable = false)
    private BigDecimal discountPercent;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    private Integer quantityInStock;

    @NotNull
    @Column(nullable = false)
    private Boolean isActive;

    protected Product(){
        this.discountPercent = new BigDecimal("0.0");
        this.isActive = true;
    };

}
