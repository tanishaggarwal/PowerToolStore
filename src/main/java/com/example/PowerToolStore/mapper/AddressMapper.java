package com.example.PowerToolStore.mapper;

import com.example.PowerToolStore.dto.request.AddressCreateRequest;
import com.example.PowerToolStore.dto.request.AddressUpdateRequest;
import com.example.PowerToolStore.dto.response.AddressResponse;
import com.example.PowerToolStore.entity.Address;
import com.example.PowerToolStore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponse toResponse(Address address){
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .userId(address.getUser().getUserId())
                .address(address.getAddress())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .state(address.getState())
                .city(address.getCity())
                .houseNumber(address.getHouseNumber())
                .build();
    }

    public Address createEntity(AddressCreateRequest request, User user)
    {
        return Address.builder()
                .user(user)
                .address(request.getAddress())
                .state(request.getState())
                .city(request.getCity())
                .country(request.getCountry())
                .pincode(request.getPincode())
                .houseNumber(request.getHouseNumber())
                .build();
    }

    public void updateEntity(Address address, AddressUpdateRequest request)
    {
        address.setAddress(request.getAddress());
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setHouseNumber(request.getHouseNumber());
    }
}
