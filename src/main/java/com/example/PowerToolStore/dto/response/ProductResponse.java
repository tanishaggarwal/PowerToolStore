package com.example.PowerToolStore.dto.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductResponse {

    @NotNull
    private Long productId;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String brand;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    @URL
    private String imageUrl;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal maxRetailPrice;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal discountPercent;

    private LocalDateTime createdAt;

//    private LocalDateTime updatedAt;

    @Min(0)
    private Integer quantityInStock;

    @NotNull
    private Boolean isActive;
}
