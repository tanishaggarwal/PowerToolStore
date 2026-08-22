package com.example.PowerToolStore.dto.request.product;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductUpdateRequest {
    @NotNull
    private Long productId;

    @NotNull
    private Long categoryId;

    @NotBlank
    private String brand;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal maxRetailPrice;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal discountPercent;
}
