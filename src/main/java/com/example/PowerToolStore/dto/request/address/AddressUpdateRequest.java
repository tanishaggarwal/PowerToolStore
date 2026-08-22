package com.example.PowerToolStore.dto.request.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AddressUpdateRequest {
    @NotNull
    private Long addressId;

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Enter a valid 6 digit pin-code")
    private String pincode;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "Enter a valid city name")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "Enter a valid state name")
    private String state;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "Enter a valid country name")
    private String country;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String address;
}
