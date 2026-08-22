package com.example.PowerToolStore.mapper;

import com.example.PowerToolStore.dto.response.CartItemResponse;
import com.example.PowerToolStore.dto.response.CartResponse;
import com.example.PowerToolStore.entity.Cart;
import com.example.PowerToolStore.entity.CartItem;
import com.example.PowerToolStore.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper)
    {
        this.cartItemMapper =  cartItemMapper;
    }

    public Cart createEntity(User user)
    {
        return Cart.builder()
                .user(user)
                .build();
    }

    public CartResponse toResponse(Long cartId, BigDecimal cartAmount, List<CartItemResponse> cartItemResponseList)
    {
        return CartResponse.builder()
                .cartItems(cartItemResponseList)
                .cartAmount(cartAmount)
                .cartId(cartId)
                .build();
    }
}
