package com.example.PowerToolStore.dto.request.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ViewCartRequest {
    @NotNull
    Long userId;
}
