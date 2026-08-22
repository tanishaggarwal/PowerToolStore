package com.example.PowerToolStore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class CartResponse {
    private Long cartId;
    private List<CartItemResponse> cartItems;
    private BigDecimal cartAmount;
}
