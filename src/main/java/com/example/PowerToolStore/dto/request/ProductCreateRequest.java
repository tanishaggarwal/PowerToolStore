package com.example.PowerToolStore.dto.request;

import com.example.PowerToolStore.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
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
//    @URL
//    private String imageUrl;

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
