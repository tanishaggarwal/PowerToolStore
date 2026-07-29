package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
