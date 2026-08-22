package com.example.PowerToolStore.dto.request.product;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
public class ProductCreateRequest {

    @NotNull
    private Long categoryId;

    @NotBlank
    private String brand;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private MultipartFile file;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal maxRetailPrice;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal discountPercent;

    @NotNull
    @Min(0)
    private Integer quantityInStock;

    @NotNull
    private Boolean isActive;
}
