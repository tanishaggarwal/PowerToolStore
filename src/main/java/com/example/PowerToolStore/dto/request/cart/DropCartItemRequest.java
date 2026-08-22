package com.example.PowerToolStore.dto.request.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DropCartItemRequest {
    @NotNull
    Long userId;
    @NotNull
    Long cartItemId;
}
