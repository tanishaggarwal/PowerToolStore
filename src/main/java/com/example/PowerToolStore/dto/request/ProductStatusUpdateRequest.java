package com.example.PowerToolStore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStatusUpdateRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Boolean isActive;
}
