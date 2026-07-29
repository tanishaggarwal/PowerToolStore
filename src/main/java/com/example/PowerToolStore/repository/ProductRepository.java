package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
