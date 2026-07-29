package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
