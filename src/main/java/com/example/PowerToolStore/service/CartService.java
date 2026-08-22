package com.example.PowerToolStore.service;

import com.example.PowerToolStore.dto.request.cart.AddToCartRequest;
import com.example.PowerToolStore.dto.request.cart.DropCartItemRequest;
import com.example.PowerToolStore.dto.request.cart.UpdateCartItemQuantityRequest;
import com.example.PowerToolStore.dto.request.cart.ViewCartRequest;
import com.example.PowerToolStore.dto.response.CartItemResponse;
import com.example.PowerToolStore.dto.response.CartResponse;
import com.example.PowerToolStore.entity.Cart;
import com.example.PowerToolStore.entity.CartItem;
import com.example.PowerToolStore.entity.Product;
import com.example.PowerToolStore.entity.User;
import com.example.PowerToolStore.exception.*;
import com.example.PowerToolStore.mapper.CartItemMapper;
import com.example.PowerToolStore.mapper.CartMapper;
import com.example.PowerToolStore.repository.CartItemRepository;
import com.example.PowerToolStore.repository.CartRepository;
import com.example.PowerToolStore.repository.ProductRepository;
import com.example.PowerToolStore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TO DO - Implement the case in viewCart if cart does not exist

@Service
@Slf4j
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public CartService(UserRepository userRepository, CartRepository cartRepository,
                              CartItemRepository cartItemRepository, CartMapper cartMapper,
                       ProductRepository productRepository, CartItemMapper cartItemMapper)
    {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartMapper = cartMapper;
        this.productRepository = productRepository;
        this.cartItemMapper = cartItemMapper;
    }

    // TODO - remove userId from request & extract it from SecurityContextHolder

    @Transactional
    public CartItemResponse addToCart(AddToCartRequest request)
    {
        // TODO - Remove userId from request and extract it from securityContextHolder
        Cart cart = getOrCreateCart(request.getUserId());
        // check if requested cart-item already exists in cart
        Optional<CartItem> cartItem = cartItemRepository.findByCart_User_UserIdAndProduct_ProductId(
                request.getUserId(), request.getProductId()
        );

        if(cartItem.isPresent())
        {
            throw new ProductAlreadyInCartException(request.getProductId());
        }
        else {
            // create new cart item
            CartItem newCartItem = createCartItem(cart, request);
            cartItemRepository.save(newCartItem);
            return cartItemMapper.toResponse(newCartItem, getCartItemAmount(newCartItem), isCartItemAvailable(newCartItem));
        }
    }

    @Transactional
    private Cart getOrCreateCart(Long userId)
    {
        Optional<Cart> cart = cartRepository.findByUser_UserId(userId);
        if(cart.isPresent())
        {
            return cart.get();
        }
        else {
            // Create new cart
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new UserNotFoundException(userId)
            );
            Cart newCart = cartMapper.createEntity(user);
            cartRepository.save(newCart);
            return newCart;
        }
    }

    private CartItem createCartItem(Cart cart, AddToCartRequest request)
    {
        Optional<Product> product = productRepository.findById(request.getProductId());
        if(product.isPresent())
        {
            if(product.get().getQuantityInStock() >= request.getQuantity())
            {
                return cartItemMapper.createEntity(request, product.get(), cart);
            }
            else {
                throw new InsufficientStockException(request.getProductId(), "Can't add item to cart due to insufficient stock");
            }
        }
        else {
            throw new ProductNotFoundException(request.getProductId());
        }
    }

    // TODO - Extract userId from SecurityContextHolder & remove it from request

    @Transactional(readOnly = true)
    public CartResponse viewCart(ViewCartRequest request)
    {
        Optional<Cart> cart = cartRepository.findByUser_UserId(request.getUserId());
        if(cart.isPresent())
        {
            BigDecimal cartAmt = BigDecimal.valueOf(0);
            List<CartItemResponse> cartItemResponseList = new ArrayList<>();

            for(CartItem cartItem: cart.get().getCartItems())
            {
                BigDecimal cartItemAmt = getCartItemAmount(cartItem);
                boolean isAvailable = isCartItemAvailable(cartItem);

                cartItemResponseList.add(
                  cartItemMapper.toResponse(cartItem, cartItemAmt, isAvailable)
                );
                // evaluate cartAmt
                if(isAvailable){
                    cartAmt = cartAmt.add(cartItemAmt);
                }
            }
            return cartMapper.toResponse(cart.get().getCartId(),
                    cartAmt, cartItemResponseList);
        }
        else {
            return cartMapper.toResponse(null, BigDecimal.valueOf(0), new ArrayList<>());
        }
    }

    private BigDecimal getCartItemAmount(CartItem cartItem)
    {
        BigDecimal productMrp = cartItem.getProduct().getMaxRetailPrice();
        BigDecimal productDiscount = cartItem.getProduct().getDiscountPercent();
        BigDecimal cartItemAmount = productMrp.subtract(
                productMrp.multiply(productDiscount.divide(HUNDRED)))
                .multiply(new BigDecimal(cartItem.getQuantity()));
        return cartItemAmount;
    }

    private boolean isCartItemAvailable(CartItem cartItem)
    {
        return cartItem.getQuantity() <= cartItem.getProduct().getQuantityInStock();
    }

    // TODO - Extract userId from SecurityContextHolder & remove it from request
    @Transactional
    public void dropCartItem(DropCartItemRequest request)
    {
        // Validate user for deletion
        CartItem cartItem = cartItemRepository.findByCartItemIdAndCart_User_UserId(
                request.getCartItemId(), request.getUserId()
        ).orElseThrow(
                ()-> new CartItemNotFoundException(request.getCartItemId())
        );
        // delete cartItemId
        cartItemRepository.deleteById(request.getCartItemId());
    }

// TODO - secure the api with user check (to prevent a logged-in user from manipulating other's data) & remove userIf from request
    @Transactional
    public CartItemResponse updateCartItemQuantity(UpdateCartItemQuantityRequest request)
    {
        CartItem cartItem = cartItemRepository.findByCartItemIdAndCart_User_UserId(request.getCartItemId(), request.getUserId())
                .orElseThrow(() -> new CartItemNotFoundException(request.getCartItemId()));

        if(cartItem.getProduct().getQuantityInStock() >= request.getQuantity())
        {
            cartItemMapper.updateEntity(request, cartItem);
            cartItemRepository.save(cartItem);
            return cartItemMapper.toResponse(cartItem, getCartItemAmount(cartItem), isCartItemAvailable(cartItem));
        }
        else {
            throw new InsufficientStockException(cartItem.getProduct().getProductId());
        }
    }
}
