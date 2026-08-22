package com.example.PowerToolStore.dto.request.cart;

import com.example.PowerToolStore.entity.Cart;
import com.example.PowerToolStore.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddToCartRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotNull
    @Min(0)
    private Integer quantity;
}
