package com.example.PowerToolStore.repository;

import com.example.PowerToolStore.entity.Address;
import com.example.PowerToolStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AddressRepository extends JpaRepository<Address, Long> {

    public Optional<Address> findByUser(User user);
}
