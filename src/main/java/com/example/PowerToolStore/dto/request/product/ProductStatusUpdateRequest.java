package com.example.PowerToolStore.dto.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductStatusUpdateRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Boolean isActive;
}
