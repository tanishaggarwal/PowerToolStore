package com.example.PowerToolStore.dto.request.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCartItemQuantityRequest {
    @NotNull
    Long userId;
    @NotNull
    Long cartItemId;
    @NotNull
    @Min(0)
    Integer quantity;
}
