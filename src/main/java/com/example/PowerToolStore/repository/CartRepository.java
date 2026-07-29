package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
