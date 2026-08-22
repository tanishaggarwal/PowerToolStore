package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findByCart_CartId(Long cartId);
    public Optional<CartItem> findByCartItemIdAndCart_User_UserId(Long cartItemId, Long userId);
    public Optional<CartItem> findByCart_User_UserIdAndProduct_ProductId(Long userId, Long productId);
}
