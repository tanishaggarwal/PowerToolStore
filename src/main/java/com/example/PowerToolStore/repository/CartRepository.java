package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Optional<Cart> findByUser_UserId(Long user);
}
