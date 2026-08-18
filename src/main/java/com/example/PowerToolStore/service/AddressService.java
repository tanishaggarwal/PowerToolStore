package com.example.PowerToolStore.service;

import com.example.PowerToolStore.dto.request.AddressCreateRequest;
import com.example.PowerToolStore.dto.request.AddressUpdateRequest;
import com.example.PowerToolStore.dto.response.AddressResponse;
import com.example.PowerToolStore.dto.response.UserResponse;
import com.example.PowerToolStore.entity.Address;
import com.example.PowerToolStore.entity.User;
import com.example.PowerToolStore.exception.AddressNotFoundException;
import com.example.PowerToolStore.exception.UserNotFoundException;
import com.example.PowerToolStore.mapper.AddressMapper;
import com.example.PowerToolStore.repository.AddressRepository;
import com.example.PowerToolStore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//TO DO:- 1. Implement validation mechanisms for address entity's fields

// ASSUMPTIONS - ONLY ONE ADDRESS PER USER

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper,
                          UserRepository userRepository)
    {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public AddressResponse findById(Long id)
    {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent())
        {
            return addressMapper.toResponse(address.get());
        }
        else {
            throw new AddressNotFoundException("addressId", id);
        }
    }

    @Transactional
    public AddressResponse findByUserId(Long userId)
    {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            Optional<Address> address = addressRepository.findByUser(user.get());
            if (address.isPresent()) {
                return addressMapper.toResponse(address.get());
            } else {
                throw new AddressNotFoundException("userId", userId);
            }
        }
        else
        {
            throw new UserNotFoundException(userId);
        }
    }

    @Transactional
    public AddressResponse createAddress(AddressCreateRequest request)
    {
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isPresent())
        {
            Address address = addressMapper.createEntity(request, user.get());
            // save entity in db
            addressRepository.save(address);
            return addressMapper.toResponse(address);
        }
        else {
            throw new UserNotFoundException(request.getUserId());
        }
    }

    @Transactional
    public AddressResponse updateAddress(AddressUpdateRequest request)
    {
     Optional<Address> address = addressRepository.findById(request.getAddressId());
     if(address.isPresent())
     {
         addressMapper.updateEntity(address.get(), request);
         // save updated address in db
         addressRepository.save(address.get());
         return addressMapper.toResponse(address.get());
     }
     else {
         throw new AddressNotFoundException("addressId", request.getAddressId());
     }
    }
}
