package com.example.PowerToolStore.controller;

import com.example.PowerToolStore.constant.MdcConstant;
import com.example.PowerToolStore.dto.request.cart.AddToCartRequest;
import com.example.PowerToolStore.dto.request.cart.DropCartItemRequest;
import com.example.PowerToolStore.dto.request.cart.UpdateCartItemQuantityRequest;
import com.example.PowerToolStore.dto.request.cart.ViewCartRequest;
import com.example.PowerToolStore.dto.response.ApiResponse;
import com.example.PowerToolStore.dto.response.CartItemResponse;
import com.example.PowerToolStore.dto.response.CartResponse;
import com.example.PowerToolStore.dto.response.GenericResponse;
import com.example.PowerToolStore.service.CartService;
import jakarta.validation.Valid;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }

    @GetMapping("/view")
    public ResponseEntity<ApiResponse<CartResponse>> viewCart(@Valid @RequestBody ViewCartRequest request)
    {
        return new ResponseEntity<>(
                ApiResponse.<CartResponse>builder()
                        .data(cartService.viewCart((request)))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItemResponse>> addToCart(@Valid @RequestBody AddToCartRequest request)
    {
        return new ResponseEntity<>(
                ApiResponse.<CartItemResponse>builder()
                        .data(cartService.addToCart(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/drop-cart-item")
    public ResponseEntity<ApiResponse<GenericResponse>> dropCartItem(@Valid @RequestBody DropCartItemRequest request)
    {
        cartService.dropCartItem(request);
        return new ResponseEntity<>(
                ApiResponse.<GenericResponse>builder()
                        .data(GenericResponse.builder()
                                .message("Cart Item Deleted!!")
                                .build())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-cart-item")
    public ResponseEntity<ApiResponse<CartItemResponse>> updateCartItem(@Valid @RequestBody UpdateCartItemQuantityRequest request)
    {
        return new ResponseEntity<>(
                ApiResponse.<CartItemResponse>builder()
                        .data(cartService.updateCartItemQuantity(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    }


}
