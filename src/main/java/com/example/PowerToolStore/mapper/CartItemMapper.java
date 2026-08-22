package com.example.PowerToolStore.mapper;

import com.example.PowerToolStore.dto.request.cart.AddToCartRequest;
import com.example.PowerToolStore.dto.request.cart.UpdateCartItemQuantityRequest;
import com.example.PowerToolStore.dto.response.CartItemResponse;
import com.example.PowerToolStore.dto.response.ProductResponse;
import com.example.PowerToolStore.entity.Cart;
import com.example.PowerToolStore.entity.CartItem;
import com.example.PowerToolStore.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartItemMapper {

    private final ProductMapper productMapper;

    public CartItemMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    public CartItem createEntity(AddToCartRequest request, Product product, Cart cart)
    {
        return CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .build();
    }

    public void updateEntity(UpdateCartItemQuantityRequest request, CartItem cartItem)
    {
        cartItem.setQuantity(request.getQuantity());
    }

    public CartItemResponse toResponse(CartItem cartItem, BigDecimal payableAmount, boolean isAvailable)
    {
        return CartItemResponse.builder()
                .cartItemId(cartItem.getCartItemId())
                .product(productMapper.toResponse(cartItem.getProduct()))
                .itemQuantity(cartItem.getQuantity())
                .cartItemAmount(payableAmount)
                .available(isAvailable)
                .build();
    }
}
