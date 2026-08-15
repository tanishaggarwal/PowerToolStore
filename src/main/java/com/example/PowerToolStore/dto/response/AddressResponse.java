package com.example.PowerToolStore.dto.response;

import com.example.PowerToolStore.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressResponse {
    private Long addressId;
    private Long userId;
    private String pincode;
    private String city;
    private String state;
    private String country;
    private String houseNumber;
    private String address;
}
