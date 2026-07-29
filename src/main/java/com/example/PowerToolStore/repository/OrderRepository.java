package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
