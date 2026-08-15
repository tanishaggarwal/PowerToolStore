package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public Optional<Address> findByUserId(Long userId);
}
