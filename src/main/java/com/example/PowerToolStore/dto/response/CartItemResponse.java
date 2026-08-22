package com.example.PowerToolStore.dto.response;

import com.example.PowerToolStore.entity.Cart;
import com.example.PowerToolStore.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CartItemResponse {
    private Long cartItemId;
    private ProductResponse product;
    private Integer itemQuantity;
    private BigDecimal cartItemAmount;
    private boolean available = true;
}
