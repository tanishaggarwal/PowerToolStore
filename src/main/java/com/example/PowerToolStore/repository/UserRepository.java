package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
