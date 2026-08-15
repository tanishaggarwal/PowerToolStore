package com.example.PowerToolStore.entity;

import com.example.PowerToolStore.exception.InsufficientStockException;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name="products")
@AllArgsConstructor
public class Product {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @DecimalMin("0.0")
    private BigDecimal maxRetailPrice;

    @Column(scale = 2, nullable = false)
    private BigDecimal discountPercent;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer quantityInStock;

    @Column(nullable = false)
    private Boolean isActive;

    protected Product(){};

    public void increaseStock( int quantity)
    {
        this.quantityInStock+= quantity;
    }

    public void decreaseStock( int quantity)
    {
        if (this.quantityInStock < quantity)
        {
            throw new InsufficientStockException(this.productId);
        }
        this.quantityInStock-=quantity;
    }
}
